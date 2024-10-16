package com.csit321.ctfbackend.user.service;

import com.csit321.ctfbackend.core.api.exceptions.CustomNotFoundException;
import com.csit321.ctfbackend.user.dto.internal.StudentUpdateDTO;
import com.csit321.ctfbackend.user.dto.internal.TeacherUpdateDTO;
import com.csit321.ctfbackend.user.model.Student;
import com.csit321.ctfbackend.user.model.Teacher;
import com.csit321.ctfbackend.user.repository.BaseUserRepository;
import com.csit321.ctfbackend.user.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final BaseUserRepository baseUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final TeacherRepository teacherRepository;

    public TeacherUpdateDTO updateTeacherInfo(String username, TeacherUpdateDTO teacherUpdateDTO) {
        Teacher targetTeacher = (Teacher) baseUserRepository.findByUsername(username).orElseThrow(() -> new CustomNotFoundException("Teacher not found."));

        targetTeacher.setUsername(teacherUpdateDTO.getUsername());
        targetTeacher.setEmail(teacherUpdateDTO.getEmail());
        targetTeacher.setPassword(passwordEncoder.encode(teacherUpdateDTO.getPassword()));
        targetTeacher.setSchool(teacherUpdateDTO.getSchool());

        baseUserRepository.save(targetTeacher);

        return TeacherUpdateDTO.teacherUpdateDTOBuilder()
                .userId(targetTeacher.getUserId())
                .username(targetTeacher.getUsername())
                .email(targetTeacher.getEmail())
                .password(targetTeacher.getPassword())
                .school(targetTeacher.getSchool())
                .build();
    }

    public void save(Teacher teacher) {
        teacherRepository.save(teacher);
    }
}
