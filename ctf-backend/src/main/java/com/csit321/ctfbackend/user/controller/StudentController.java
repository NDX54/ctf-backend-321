package com.csit321.ctfbackend.user.controller;

import com.csit321.ctfbackend.core.api.APIResponse;
import com.csit321.ctfbackend.user.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/api/student")
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

//    @PutMapping("/update")
//    public ResponseEntity<?> updateStudentInfo(@RequestParam String username) {
//
//    }
}
