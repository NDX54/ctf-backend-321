package com.csit321.ctfbackend.room.controller;

import com.csit321.ctfbackend.core.api.APIResponse;
import com.csit321.ctfbackend.room.dto.internal.QuestionDTO;
import com.csit321.ctfbackend.room.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    // Endpoint to get all questions
    @GetMapping("/")
    public List<QuestionDTO> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    // Endpoint to get questions for a specific challenge by its ID
    @GetMapping("/{challengeId}")
    public ResponseEntity<?> getQuestionsByRoomId(@PathVariable Long challengeId) {

        return ResponseEntity.ok(questionService.getQuestionsForChallenge(challengeId));
    }

    // Endpoint to create a new question for a specific challenge
    @PostMapping("/new")
    public ResponseEntity<?> createQuestion(@RequestParam Long challengeId, @RequestBody QuestionDTO questionDTO, WebRequest request) {
        QuestionDTO newQuestion = questionService.createQuestion(questionDTO, challengeId);

        return APIResponse.build(newQuestion, "Question created", HttpStatus.CREATED, request);
    }
}
