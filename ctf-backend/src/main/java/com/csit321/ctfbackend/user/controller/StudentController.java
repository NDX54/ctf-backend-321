package com.csit321.ctfbackend.user.controller;

import com.csit321.ctfbackend.core.api.APIResponse;
import com.csit321.ctfbackend.user.dto.internal.StudentDTO;
import com.csit321.ctfbackend.user.dto.internal.StudentUpdateDTO;
import com.csit321.ctfbackend.user.service.StudentService;
import jakarta.validation.Valid;
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

    @PatchMapping("/edit")
    public ResponseEntity<?> editStudent(@RequestParam String username, @RequestBody @Valid StudentUpdateDTO studentUpdateDTO, WebRequest request) {
        StudentUpdateDTO updatedStudent = studentService.updateStudentInfo(username, studentUpdateDTO);
        return APIResponse.build(updatedStudent, "Student info updated", HttpStatus.OK, request);
    }

}
