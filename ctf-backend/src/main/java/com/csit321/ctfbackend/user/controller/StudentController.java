package com.csit321.ctfbackend.user.controller;

import com.csit321.ctfbackend.core.api.APIResponse;
import com.csit321.ctfbackend.user.service.BaseUserService;
import com.csit321.ctfbackend.user.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PatchMapping("/score")
    public ResponseEntity<?> saveStudentScore(@RequestParam String username, @RequestParam double score, WebRequest request) {
        studentService.saveStudentScore(username, score);

        return APIResponse.build(score, "Student score updated", HttpStatus.OK, request);
    }
}