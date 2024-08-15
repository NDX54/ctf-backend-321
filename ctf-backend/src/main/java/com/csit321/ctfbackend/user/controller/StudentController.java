package com.csit321.ctfbackend.user.controller;

import com.csit321.ctfbackend.core.api.APIResponse;
import com.csit321.ctfbackend.user.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    // Endpoint to update the score of a student
    @PatchMapping("/score")
    public ResponseEntity<?> saveStudentScore(@RequestParam String username, @RequestParam double score, WebRequest request) {
        studentService.saveStudentScore(username, score);
        return APIResponse.build(score, "Student score updated", HttpStatus.OK, request);
    }

}
