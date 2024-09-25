package com.csit321.ctfbackend.room.controller;

import com.csit321.ctfbackend.room.dto.internal.CompetitionDTO;
import com.csit321.ctfbackend.room.service.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/competition")
@RequiredArgsConstructor
public class CompetitionController {

    private final CompetitionService competitionService;

    @GetMapping("/all")
    public List<CompetitionDTO> getAllCompetitions() {
        return competitionService.getAllCompetitions();
    }

    @PostMapping("/create")
    public String createCompetition(@RequestParam int maxTeams, @RequestParam int maxTeamSize) {


        return competitionService.createCompetition(maxTeams, maxTeamSize);
    }

}

