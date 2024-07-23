package com.csit321.ctfbackend.user.service;

import com.csit321.ctfbackend.core.api.CustomNotFoundException;
import com.csit321.ctfbackend.core.api.WrongPasswordException;
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
import com.csit321.ctfbackend.user.repository.BaseUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BaseUserServiceImpl implements BaseUserService {

    private static final Logger LOG = LoggerFactory.getLogger(BaseUserServiceImpl.class);
    private final BaseUserRepository baseUserRepository;
    private final PasswordEncoder passwordEncoder;

    public BaseUserServiceImpl(BaseUserRepository baseUserRepository, PasswordEncoder passwordEncoder) {
        this.baseUserRepository = baseUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Student saveStudent(StudentDTO studentDTO) {
        Student savedStudent = new Student(
                studentDTO.getUsername(),
                studentDTO.getEmail(),
                passwordEncoder.encode(studentDTO.getPassword()),
                UserType.STUDENT,
                "ROLE_STUDENT",
                studentDTO.getYearLevel(),
                0.0
        );
        return baseUserRepository.save(savedStudent);
    }

    @Override
    public Teacher saveTeacher(TeacherDTO teacherDTO) {
        Teacher savedTeacher = new Teacher(
                teacherDTO.getUsername(),
                teacherDTO.getEmail(),
                passwordEncoder.encode(teacherDTO.getPassword()),
                UserType.TEACHER,
                "ROLE_TEACHER",
                teacherDTO.getSchool()
        );
        return baseUserRepository.save(savedTeacher);
    }

    @Override
    public BaseUserDTO convertToSpecificDTO(BaseUser baseUser) {
        if (baseUser instanceof Student student) {
            return new StudentDTO(
                    student.getUserId(),
                    student.getUsername(),
                    student.getEmail(),
                    student.getPassword(),
                    student.getUserType().getValue(),
                    student.getRole(),
                    student.getYearLevel(),
                    student.getScore());
        } else if (baseUser instanceof Teacher teacher) {
            return new TeacherDTO(
                    teacher.getUserId(),
                    teacher.getUsername(),
                    teacher.getEmail(),
                    teacher.getPassword(),
                    teacher.getUserType().getValue(),
                    teacher.getRole(),
                    teacher.getSchool());
        } else {
            return new BaseUserDTO(
                    baseUser.getUserId(),
                    baseUser.getUsername(),
                    baseUser.getEmail(),
                    baseUser.getPassword(),
                    baseUser.getUserType().getValue(),
                    baseUser.getRole());
        }
    }

    @Override
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

    @Override
    public StudentDTO convertToStudentDTO(Student student) {
        return new StudentDTO(
                student.getUserId(),
                student.getUsername(),
                student.getEmail(),
                student.getPassword(),
                student.getUserType().getValue(),
                student.getRole(),
                student.getYearLevel(),
                student.getScore()
        );
    }

    @Override
    public TeacherDTO convertToTeacherDTO(Teacher teacher) {
        return new TeacherDTO(
                teacher.getUserId(),
                teacher.getUsername(),
                teacher.getEmail(),
                teacher.getPassword(),
                teacher.getUserType().getValue(),
                teacher.getRole(),
                teacher.getSchool()
        );
    }

    @Override
    public BaseUserDTO getUser(String email, String username) {

        BaseUser baseUser = findUserByEmailOrUsername(email, username);

        return convertToSpecificDTO(baseUser);
    }

    @Override
    public PublicBaseUserDTO getPublicUser(String email, String username) {

        BaseUser baseUser = findUserByEmailOrUsername(email, username);

        return convertToPublicDTO(baseUser);
    }

    @Override
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

    @Override
    public void deleteUser(String email, String username) {
        BaseUser baseUserToDelete = findUserByEmailOrUsername(email, username);

        baseUserRepository.delete(baseUserToDelete);
    }

    @Override
    public BaseUserDTO authenticateUser(String email, String username, String password) {

        BaseUser baseUser = findUserByEmailOrUsername(email, username);

        if (!passwordEncoder.matches(password, baseUser.getPassword())) {
            throw new WrongPasswordException();
        }

        return convertToSpecificDTO(baseUser);
    }

    @Override
    public List<BaseUserDTO> getAllUsers() {
        List<BaseUser> baseUsers = baseUserRepository.findAll();
        List<BaseUserDTO> userDTOList = new ArrayList<>();
        for (BaseUser baseUser : baseUsers) {
            userDTOList.add(convertToSpecificDTO(baseUser));
        }
        return userDTOList;
    }

    @Override
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
