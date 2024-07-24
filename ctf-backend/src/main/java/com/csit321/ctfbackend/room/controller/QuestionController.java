package com.csit321.ctfbackend.room.controller;

import com.csit321.ctfbackend.core.api.APIResponse;
import com.csit321.ctfbackend.room.dto.internal.QuestionDTO;
import com.csit321.ctfbackend.room.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> createQuestion(@RequestParam Long roomId, @RequestBody QuestionDTO questionDTO, WebRequest request) {
        QuestionDTO newQuestion = questionService.createQuestion(questionDTO, roomId);

        return APIResponse.build(newQuestion, "Question created", HttpStatus.CREATED, request);
    }
}
