package com.csit321.ctfbackend.user.service;

import com.csit321.ctfbackend.core.api.CustomNotFoundException;
import com.csit321.ctfbackend.core.api.WrongPasswordException;
import com.csit321.ctfbackend.core.auth.AuthenticationResponse;
import com.csit321.ctfbackend.core.config.JwtService;
import com.csit321.ctfbackend.user.dto.external.PublicBaseUserDTO;
import com.csit321.ctfbackend.user.dto.external.PublicStudentDTO;
import com.csit321.ctfbackend.user.dto.external.PublicTeacherDTO;
import com.csit321.ctfbackend.user.dto.internal.BaseUserDTO;
import com.csit321.ctfbackend.user.dto.internal.StudentDTO;
import com.csit321.ctfbackend.user.dto.internal.TeacherDTO;
import com.csit321.ctfbackend.user.enums.UserType;
import com.csit321.ctfbackend.user.model.BaseUser;
import com.csit321.ctfbackend.user.model.Student;
import com.csit321.ctfbackend.user.model.Teacher;
import com.csit321.ctfbackend.user.model.enums.Role;
import com.csit321.ctfbackend.user.repository.BaseUserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseUserService {

    private static final Logger LOG = LoggerFactory.getLogger(BaseUserService.class);
    private final BaseUserRepository baseUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse saveStudent(StudentDTO studentDTO) {
        var savedStudent = Student.studentBuilder()
                .username(studentDTO.getUsername())
                .email(studentDTO.getEmail())
                .password(passwordEncoder.encode(studentDTO.getPassword()))
                .userType(UserType.STUDENT)
                .role(Role.STUDENT)
                .yearLevel(studentDTO.getYearLevel())
                .score(0.0)
                .build();

        baseUserRepository.save(savedStudent);
        var jwtToken = jwtService.generateToken(savedStudent);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse saveTeacher(TeacherDTO teacherDTO) {
        var savedTeacher = Teacher.teacherBuilder()
                .username(teacherDTO.getUsername())
                .email(teacherDTO.getEmail())
                .password(passwordEncoder.encode(teacherDTO.getPassword()))
                .userType(UserType.TEACHER)
                .role(Role.TEACHER)
                .username(teacherDTO.getUsername())
                .school(teacherDTO.getSchool())
                .build();

        baseUserRepository.save(savedTeacher);
        var jwtToken = jwtService.generateToken(savedTeacher);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public BaseUserDTO convertToSpecificDTO(BaseUser baseUser) {
        if (baseUser instanceof Student student) {
            return new StudentDTO(
                    student.getUserId(),
                    student.getUsername(),
                    student.getEmail(),
                    student.getPassword(),
                    student.getUserType().getValue(),
                    student.getRole().getValue(),
                    student.getYearLevel(),
                    student.getScore());
        } else if (baseUser instanceof Teacher teacher) {
            return new TeacherDTO(
                    teacher.getUserId(),
                    teacher.getUsername(),
                    teacher.getEmail(),
                    teacher.getPassword(),
                    teacher.getUserType().getValue(),
                    teacher.getRole().getValue(),
                    teacher.getSchool());
        } else {
            return new BaseUserDTO(
                    baseUser.getUserId(),
                    baseUser.getUsername(),
                    baseUser.getEmail(),
                    baseUser.getPassword(),
                    baseUser.getUserType().getValue(),
                    baseUser.getRole().getValue());
        }
    }

    public PublicBaseUserDTO convertToPublicDTO(BaseUser baseUser) {
        if (baseUser instanceof Student student) {
            return new PublicStudentDTO(
                    student.getUserId(),
                    student.getUsername(),
                    student.getEmail(),
                    student.getUserType().getValue(),
                    student.getYearLevel(),
                    student.getScore());
        } else if (baseUser instanceof Teacher teacher) {
            return new PublicTeacherDTO(
                    teacher.getUserId(),
                    teacher.getUsername(),
                    teacher.getEmail(),
                    teacher.getUserType().getValue(),
                    teacher.getSchool());
        } else {
            return new PublicBaseUserDTO(
                    baseUser.getUserId(),
                    baseUser.getUsername(),
                    baseUser.getEmail(),
                    baseUser.getUserType().getValue());
        }
    }

//    public StudentDTO convertToStudentDTO(Student student) {
//        return new StudentDTO(
//                student.getUserId(),
//                student.getUsername(),
//                student.getEmail(),
//                student.getPassword(),
//                student.getUserType().getValue(),
//                student.getRole(),
//                student.getYearLevel(),
//                student.getScore()
//        );
//    }
//
//    public TeacherDTO convertToTeacherDTO(Teacher teacher) {
//        return new TeacherDTO(
//                teacher.getUserId(),
//                teacher.getUsername(),
//                teacher.getEmail(),
//                teacher.getPassword(),
//                teacher.getUserType().getValue(),
//                teacher.getRole(),
//                teacher.getSchool()
//        );
//    }

    public BaseUserDTO getUser(String email, String username) {

        BaseUser baseUser = findUserByEmailOrUsername(email, username);

        return convertToSpecificDTO(baseUser);
    }

    public PublicBaseUserDTO getPublicUser(String email, String username) {

        BaseUser baseUser = findUserByEmailOrUsername(email, username);

        return convertToPublicDTO(baseUser);
    }

    public BaseUserDTO updateUser(String email, String username, BaseUserDTO userDTO) {
        BaseUser baseUserToUpdate = findUserByEmailOrUsername(email, username);

        baseUserToUpdate.setEmail(userDTO.getEmail());
        baseUserToUpdate.setUsername(userDTO.getUsername());

        if (userDTO.getPassword() != null && userDTO.getPassword().length() >= 8) {
            baseUserToUpdate.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        } else if (passwordEncoder.matches(userDTO.getPassword(), baseUserToUpdate.getPassword())) {
            passwordEncoder.upgradeEncoding(baseUserToUpdate.getPassword());
        } else {
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }

        baseUserRepository.save(baseUserToUpdate);

        return convertToSpecificDTO(baseUserToUpdate);
    }

    public void deleteUser(String email, String username) {
        BaseUser baseUserToDelete = findUserByEmailOrUsername(email, username);

        baseUserRepository.delete(baseUserToDelete);
    }

    public AuthenticationResponse authenticateUser(BaseUserDTO userDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.getUsername(),
                        userDTO.getPassword()
                )
        );

        var user = baseUserRepository.findByUsername(userDTO.getUsername()).orElseThrow(() -> new CustomNotFoundException("User not found"));
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

//    public BaseUserDTO authenticateUser(String email, String username, String password) {
//
//        BaseUser baseUser = findUserByEmailOrUsername(email, username);
//
//
//        if (!passwordEncoder.matches(password, baseUser.getPassword())) {
//            throw new WrongPasswordException();
//        }
//
//        return convertToSpecificDTO(baseUser);
//    }

    public List<BaseUserDTO> getAllUsers() {
        List<BaseUser> baseUsers = baseUserRepository.findAll();
        List<BaseUserDTO> userDTOList = new ArrayList<>();
        for (BaseUser baseUser : baseUsers) {
            userDTOList.add(convertToSpecificDTO(baseUser));
        }
        return userDTOList;
    }

    public List<PublicBaseUserDTO> getAllPublicUsers() {
        List<BaseUser> baseUsers = baseUserRepository.findAll();
        List<PublicBaseUserDTO> userDTOList = new ArrayList<>();
        for (BaseUser baseUser : baseUsers) {
            userDTOList.add(convertToPublicDTO(baseUser));
        }

        return userDTOList;
    }

    private BaseUser findUserByEmailOrUsername(String email, String username) {

        if (email != null && username != null) {
            return baseUserRepository.findByEmailIgnoreCaseOrUsername(email, username).orElseThrow(() -> new CustomNotFoundException("User not found"));
        } else if (email != null) {
            return baseUserRepository.findByEmailIgnoreCase(email).orElseThrow(() -> new CustomNotFoundException("User not found"));
        } else if (username != null) {
            return baseUserRepository.findByUsername(username).orElseThrow(() -> new CustomNotFoundException("User not found"));
        } else {
            throw new CustomNotFoundException("User not found");
        }

    }
}
