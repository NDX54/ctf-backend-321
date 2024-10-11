package com.csit321.ctfbackend.room.controller;

import com.csit321.ctfbackend.room.dto.internal.CompetitionDTO;
import com.csit321.ctfbackend.room.service.CompetitionService;
import com.csit321.ctfbackend.user.dto.external.TeamDTO;
import com.csit321.ctfbackend.user.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/competition")
@RequiredArgsConstructor
public class CompetitionController {

    private final CompetitionService competitionService;
    private final TeamService teamService;

    @GetMapping("/all")
    public List<CompetitionDTO> getAllCompetitions() {
        return competitionService.getAllCompetitions();
    }

    @GetMapping("/{competitionId}/teams")
    public List<TeamDTO> getTeamsForCompetition(@PathVariable Long competitionId) {
        return teamService.getTeamsByCompetitionId(competitionId);
    }

    @GetMapping("/{competitionId}/rankedTeams")
    public List<TeamDTO> getRankedTeamsForCompetition(@PathVariable Long competitionId) {
        return teamService.getRankedTeams(competitionId);
    }

    @PostMapping("/create")
    public String createCompetition(@RequestParam int maxTeams, @RequestParam int maxTeamSize) {


        return competitionService.createCompetition(maxTeams, maxTeamSize);
    }

}

