package com.csit321.ctfbackend.user.controller;

import com.csit321.ctfbackend.user.dto.external.TeamDTO;
import com.csit321.ctfbackend.user.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/team")
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/all")
    public List<TeamDTO> getAllTeams() {
        return teamService.getAllTeams();
    }

    @PostMapping("/create")
    public String createTeam(@RequestParam String teamName) {
        return teamService.createTeam(teamName);
    }

    @PostMapping("/addMember")
    public void addMemberToTeam(@RequestParam String teamPassword, @RequestParam String studentUsername) {
        teamService.addMemberToTeam(teamPassword, studentUsername);
    }

    @DeleteMapping("/removeMember")
    public void removeMemberFromTeam(@RequestParam String teamName, @RequestParam String studentUsername) {
        teamService.removeMemberFromTeam(teamName, studentUsername);
    }

}
