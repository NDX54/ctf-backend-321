package com.csit321.ctfbackend.room.controller;

import com.csit321.ctfbackend.room.dto.internal.CompetitionDTO;
import com.csit321.ctfbackend.room.service.CompetitionService;
import com.csit321.ctfbackend.user.dto.external.TeamDTO;
import com.csit321.ctfbackend.user.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
        return teamService.getRankedTeamsByCompetitionCode(competitionId);
    }

    @GetMapping("/{competitionCode}/rankedTeamsCode")
    public List<TeamDTO> getRankedTeamsForCompetition(@PathVariable String competitionCode) {
        return teamService.getRankedTeamsByCompetitionCode(competitionCode);
    }

    @GetMapping("/{competitionCode}/status")
    public String getCompetitionStatus(@PathVariable String competitionCode) {
        return competitionService.getCompetitionStatus(competitionCode);
    }

    @PostMapping("/create")
    public String createCompetition(@RequestParam String name, @RequestParam int maxTeams, @RequestParam int maxTeamSize) {
        return competitionService.createCompetition(name, maxTeams, maxTeamSize);
    }

    @PostMapping("/{competitionCode}/teams/create")
    public TeamDTO createTeam(@PathVariable String competitionCode, @RequestParam String teamName) {
        return competitionService.addTeamToCompetition(competitionCode, teamName);
    }

    @PostMapping("/{competitionCode}/teams/{teamName}/add-student")
    public ResponseEntity<String> addStudentToTeam(
            @PathVariable String competitionCode,
            @PathVariable String teamName,
            @RequestParam String username) {
        competitionService.addStudentToTeam(competitionCode, teamName, username);
        return ResponseEntity.ok("Student added to team successfully.");
    }


    @PostMapping("/{competitionCode}/setStatus")
    public String setCompetitionStatus(@PathVariable String competitionCode, @RequestParam String status) {
        return competitionService.setCompetitionStatus(competitionCode, status);
    }

    @PostMapping("/{competitionCode}/start")
    public String startCompetition(@PathVariable String competitionCode) {
        return competitionService.startCompetition(competitionCode);
    }

    @PostMapping("/{competitionCode}/end")
    public String endCompetition(@PathVariable String competitionCode) {
        return competitionService.endCompetition(competitionCode);
    }

}

