package com.csit321.ctfbackend.user.service;

import com.csit321.ctfbackend.core.api.exceptions.CustomNotFoundException;
import com.csit321.ctfbackend.room.dto.internal.ScoreUpdateDTO;
import com.csit321.ctfbackend.room.service.ScoreUpdateService;
import com.csit321.ctfbackend.user.dto.external.PublicStudentDTO;
import com.csit321.ctfbackend.user.dto.internal.StudentDTO;
import com.csit321.ctfbackend.user.dto.internal.StudentUpdateDTO;
import com.csit321.ctfbackend.user.model.BaseUser;
import com.csit321.ctfbackend.user.model.Student;
import com.csit321.ctfbackend.user.repository.BaseUserRepository;
import com.csit321.ctfbackend.user.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final BaseUserRepository baseUserRepository;
    private final ScoreUpdateService scoreUpdateService;
    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;

    // This saves the student's score.
    public void saveStudentScore(String username, double score) {
        BaseUser baseUser = findUserByEmailOrUsername(null, username);

        // Check whether the base user is a Student subclass, otherwise throw an error.
        if (baseUser instanceof Student student) {
            DecimalFormat df = new DecimalFormat("0.00");
            df.format(score);
            student.setScore(student.getScore() + score);
            baseUserRepository.save(student);
            var scoreUpdateDTO = ScoreUpdateDTO.scoreUpdateDTOBuilder()
                    .username(student.getUsername())
                    .newScore(student.getScore())
                    .build();

            // Sends the score update to RabbitMQ, which is responsible
            // for sending real-time updates to the score at the frontend.
            scoreUpdateService.sendScoreUpdate(scoreUpdateDTO);
        } else {
            throw new RuntimeException("User is not a student.");
        }
    }

    public StudentUpdateDTO updateStudentInfo(String username, StudentUpdateDTO studentUpdateDTO) {
        Student targetStudent = (Student) baseUserRepository.findByUsername(username).orElseThrow(() -> new CustomNotFoundException("Student not found."));

        targetStudent.setUsername(studentUpdateDTO.getUsername());
        targetStudent.setEmail(studentUpdateDTO.getEmail());
        targetStudent.setPassword(passwordEncoder.encode(studentUpdateDTO.getPassword()));
        targetStudent.setYearLevel(studentUpdateDTO.getYearLevel());

        baseUserRepository.save(targetStudent);

        return StudentUpdateDTO.studentUpdateDTOBuilder()
                .userId(targetStudent.getUserId())
                .username(targetStudent.getUsername())
                .email(targetStudent.getEmail())
                .password(targetStudent.getPassword())
                .yearLevel(targetStudent.getYearLevel())
                .build();
    }

    public Student getStudentByUsername(String username) {
        return (Student) baseUserRepository.findByUsername(username).orElseThrow(() -> new CustomNotFoundException("Student not found."));
    }

    // Gets a user either by their email or username.
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

    public List<PublicStudentDTO> getStudentsNotInTeam() {
        List<Student> studentsNotInTeam = studentRepository.getAllStudentsNotInTeam();
        List<PublicStudentDTO> studentDTOS = new ArrayList<>();
        studentsNotInTeam.forEach(student -> studentDTOS.add(convertToStudentDTO(student)));

        return studentDTOS;
    }

    private PublicStudentDTO convertToStudentDTO(Student student) {
        return PublicStudentDTO.publicStudentDTOBuilder()
                .userId(student.getUserId())
                .username(student.getUsername())
                .email(student.getEmail())
                .yearLevel(student.getYearLevel())
                .role(student.getRole().getValue())
                .token("REDACTED")
                .score(student.getScore())
                .build();
    }

    public void save(Student student) {
        studentRepository.save(student);
    }
}
