package com.csit321.ctfbackend.room.controller;

import com.csit321.ctfbackend.core.api.APIResponse;
import com.csit321.ctfbackend.room.dto.internal.ChallengeDTO;
import com.csit321.ctfbackend.room.service.ChallengeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("/api/challenge")
@RequiredArgsConstructor
public class ChallengeController {

    private final ChallengeService challengeService;

    // Endpoint to get all challenges
    @GetMapping("/all")
    public List<ChallengeDTO> getAllChallenges() {
        return challengeService.getAllChallenges();
    }

    @GetMapping("/open")
    public List<ChallengeDTO> getAllOpenChallenges() {
        return challengeService.getAllOpenChallenges();
    }

    @GetMapping("/closed")
    public List<ChallengeDTO> getAllClosedChallenges() {
        return challengeService.getAllClosedChallenges();
    }

    // Endpoint to get a specific challenge by its ID
    @GetMapping("/{challengeId}")
    public ResponseEntity<?> getChallenge(@PathVariable Long challengeId) {
        ChallengeDTO challengeDTO = challengeService.getChallengeById(challengeId);

        return ResponseEntity.ok(challengeDTO);
    }

    // Endpoint to create a new challenge
    @PostMapping("/new")
    public ResponseEntity<?> createChallenge(@RequestBody ChallengeDTO challengeDTO, WebRequest request) {
        ChallengeDTO newChallenge = challengeService.createChallenge(challengeDTO);

        return APIResponse.build(newChallenge, "Challenge created successfully", HttpStatus.CREATED, request);
    }
}
