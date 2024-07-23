package com.csit321.ctfbackend.user.service;

import com.csit321.ctfbackend.core.api.CustomNotFoundException;
import com.csit321.ctfbackend.user.dto.internal.StudentDTO;
import com.csit321.ctfbackend.user.model.BaseUser;
import com.csit321.ctfbackend.user.model.Student;
import com.csit321.ctfbackend.user.repository.BaseUserRepository;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class StudentServiceImpl implements StudentService {

    private final BaseUserRepository baseUserRepository;

    public StudentServiceImpl(BaseUserRepository baseUserRepository) {
        this.baseUserRepository = baseUserRepository;
    }

    @Override
    public void saveStudentScore(String username, double score) {
        BaseUser baseUser = findUserByEmailOrUsername(null, username);
        StudentDTO studentDTO = new StudentDTO();

        try {
            if (baseUser instanceof Student student) {
                DecimalFormat df = new DecimalFormat("0.00");
                df.format(score);
                student.setScore(student.getScore() + score);
                baseUserRepository.save(student);
            }
        } catch (RuntimeException e) {
            e.getMessage();
        }
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
