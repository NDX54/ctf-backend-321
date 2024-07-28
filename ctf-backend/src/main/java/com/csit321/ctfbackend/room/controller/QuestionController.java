package com.csit321.ctfbackend.room.controller;

import com.csit321.ctfbackend.core.api.APIResponse;
import com.csit321.ctfbackend.room.dto.internal.QuestionDTO;
import com.csit321.ctfbackend.room.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/{roomId}")
    public ResponseEntity<?> getQuestionsByRoomId(@PathVariable Long roomId) {

        return ResponseEntity.ok(questionService.getQuestionsForRoom(roomId));
    }

    @PostMapping("/new")
    public ResponseEntity<?> createQuestion(@RequestParam Long roomId, @RequestBody QuestionDTO questionDTO, WebRequest request) {
        QuestionDTO newQuestion = questionService.createQuestion(questionDTO, roomId);

        return APIResponse.build(newQuestion, "Question created", HttpStatus.CREATED, request);
    }
}
