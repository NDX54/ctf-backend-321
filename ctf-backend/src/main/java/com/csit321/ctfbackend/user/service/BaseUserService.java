package com.csit321.ctfbackend.user.service;

import com.csit321.ctfbackend.core.api.exceptions.CustomNotFoundException;
import com.csit321.ctfbackend.core.auth.AuthenticationRequest;
import com.csit321.ctfbackend.core.auth.AuthenticationResponse;
import com.csit321.ctfbackend.core.config.jwt.JwtService;
import com.csit321.ctfbackend.core.token.Token;
import com.csit321.ctfbackend.core.token.TokenRepository;
import com.csit321.ctfbackend.core.token.TokenType;
import com.csit321.ctfbackend.user.dto.external.PublicBaseUserDTO;
import com.csit321.ctfbackend.user.dto.external.PublicStudentDTO;
import com.csit321.ctfbackend.user.dto.external.PublicTeacherDTO;
import com.csit321.ctfbackend.user.dto.internal.BaseUserDTO;
import com.csit321.ctfbackend.user.dto.internal.StudentDTO;
import com.csit321.ctfbackend.user.dto.internal.TeacherDTO;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseUserService {

    private static final Logger LOG = LoggerFactory.getLogger(BaseUserService.class);
    private final BaseUserRepository baseUserRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final StudentService studentService;

    public AuthenticationResponse saveStudent(StudentDTO studentDTO) {
        var savedStudent = Student.studentBuilderEntity()
                .username(studentDTO.getUsername())
                .email(studentDTO.getEmail())
                .password(passwordEncoder.encode(studentDTO.getPassword()))
                .role(Role.STUDENT)
                .yearLevel(studentDTO.getYearLevel())
                .score(0.0)
                .build();

        var savedUser = baseUserRepository.save(savedStudent);
        var jwtToken = jwtService.generateToken(savedStudent);
        saveUserToken(savedUser, jwtToken);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse saveTeacher(TeacherDTO teacherDTO) {
        var savedTeacher = Teacher.teacherBuilderEntity()
                .username(teacherDTO.getUsername())
                .email(teacherDTO.getEmail())
                .password(passwordEncoder.encode(teacherDTO.getPassword()))
                .role(Role.TEACHER)
                .username(teacherDTO.getUsername())
                .school(teacherDTO.getSchool())
                .build();

        var savedUser = baseUserRepository.save(savedTeacher);
        var jwtToken = jwtService.generateToken(savedTeacher);
        saveUserToken(savedUser, jwtToken);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public BaseUserDTO convertToSpecificDTO(BaseUser baseUser) {
        if (baseUser instanceof Student student) {

            return StudentDTO.studentDTOBuilder()
                    .userId(student.getUserId())
                    .username(student.getUsername())
                    .email(student.getEmail())
                    .password(student.getPassword())
                    .role(student.getRole().getValue())
                    .yearLevel(student.getYearLevel())
                    .score(student.getScore())
                    .build();

        } else if (baseUser instanceof Teacher teacher) {

            return TeacherDTO.teacherDTOBuilder()
                    .userId(teacher.getUserId())
                    .username(teacher.getUsername())
                    .email(teacher.getEmail())
                    .password(teacher.getPassword())
                    .role(teacher.getRole().getValue())
                    .school(teacher.getSchool())
                    .build();

        } else {

            return BaseUserDTO.baseUserDTOBuilder()
                    .userId(baseUser.getUserId())
                    .username(baseUser.getUsername())
                    .email(baseUser.getEmail())
                    .password(baseUser.getPassword())
                    .role(baseUser.getRole().getValue())
                    .build();

        }
    }

    public PublicBaseUserDTO convertToPublicDTO(BaseUser baseUser) {
        if (baseUser instanceof Student student) {

            return PublicStudentDTO.publicStudentDTOBuilder()
                    .userId(student.getUserId())
                    .username(student.getUsername())
                    .email(student.getEmail())
                    .role(student.getRole().getValue())
                    .yearLevel(student.getYearLevel())
                    .score(student.getScore())
                    .build();

        } else if (baseUser instanceof Teacher teacher) {

            return PublicTeacherDTO.publicTeacherDTOBuilder()
                    .userId(teacher.getUserId())
                    .username(teacher.getUsername())
                    .email(teacher.getEmail())
                    .role(teacher.getRole().getValue())
                    .school(teacher.getSchool())
                    .build();

        } else {

            return PublicBaseUserDTO.publicBaseUserDTOBuilder()
                    .userId(baseUser.getUserId())
                    .username(baseUser.getUsername())
                    .email(baseUser.getEmail())
                    .role(baseUser.getRole().getValue())
                    .build();
        }
    }

    public StudentDTO convertToStudentDTO(Student student) {

        return StudentDTO.studentDTOBuilder()
                .userId(student.getUserId())
                .username(student.getUsername())
                .email(student.getEmail())
                .password(student.getPassword())
                .role(student.getRole().getValue())
                .yearLevel(student.getYearLevel())
                .score(student.getScore())
                .build();
    }

    public TeacherDTO convertToTeacherDTO(Teacher teacher) {

        return TeacherDTO.teacherDTOBuilder()
                .userId(teacher.getUserId())
                .username(teacher.getUsername())
                .email(teacher.getEmail())
                .password(teacher.getPassword())
                .role(teacher.getRole().getValue())
                .school(teacher.getSchool())
                .build();
    }

    public BaseUserDTO getUser(AuthenticationRequest authRequest) {

        BaseUser baseUser = baseUserRepository.findByUsername(authRequest.getUsername()).orElseThrow(() -> new CustomNotFoundException("User not found."));

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

    public PublicBaseUserDTO getUser(String username) {

        BaseUser user = baseUserRepository.findByUsername(username).orElseThrow(() -> new CustomNotFoundException("User not found."));

        return PublicBaseUserDTO.publicBaseUserDTOBuilder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
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

    public PublicBaseUserDTO authenticateUser(AuthenticationRequest authRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );

        var user = baseUserRepository.findByUsername(authRequest.getUsername()).orElseThrow(() -> new CustomNotFoundException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        return PublicBaseUserDTO.publicBaseUserDTOBuilder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().getValue())
                .token(jwtToken)
                .build();
    }

    public String generateNewToken(String username) {
        var user = baseUserRepository.findByUsername(username).orElseThrow(() -> new CustomNotFoundException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        return jwtToken;
    }

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

    // Invalidate all the active tokens, if there are any.
    private void revokeAllUserTokens(BaseUser user) {
        var validUserTokens = tokenRepository.findAllValidTokensByUser(user.getUserId());
        if (validUserTokens.isEmpty()) {
            return;
        }

        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });

        tokenRepository.saveAll(validUserTokens);
    }

    private void saveUserToken(BaseUser user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();

        tokenRepository.save(token);
    }

    public void save(BaseUser baseUser) {
        baseUserRepository.save(baseUser);
    }
}
