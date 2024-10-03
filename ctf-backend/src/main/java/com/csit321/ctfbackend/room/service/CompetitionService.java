package com.csit321.ctfbackend.room.service;

import com.csit321.ctfbackend.core.api.exceptions.AlreadyMemberException;
import com.csit321.ctfbackend.core.api.exceptions.CustomNotFoundException;
import com.csit321.ctfbackend.room.dto.internal.CompetitionDTO;
import com.csit321.ctfbackend.room.model.Competition;
import com.csit321.ctfbackend.room.repository.CompetitionRepository;
import com.csit321.ctfbackend.user.dto.external.MemberDTO;
import com.csit321.ctfbackend.user.dto.external.TeamDTO;
import com.csit321.ctfbackend.user.model.Student;
import com.csit321.ctfbackend.user.model.Team;
import com.csit321.ctfbackend.user.service.StudentService;
import com.csit321.ctfbackend.user.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final TeamService teamService;
    private final StudentService studentService;

    public List<CompetitionDTO> getAllCompetitions() {
        List<Competition> competitions = competitionRepository.findAll();
        List<CompetitionDTO> competitionDTOS = new ArrayList<>();

        for (Competition competition : competitions) {

            var competitionDTO = CompetitionDTO.builder()
                    .competitionId(competition.getCompetitionId())
                    .competitionName(competition.getCompetitionName())
                    .competitionCode(competition.getCompetitionCode())
                    .maxTeams(competition.getMaxTeams())
                    .maxTeamSize(competition.getMaxTeamSize())
                    .teamsList(teamService.getTeamsByCompetitionId(competition.getCompetitionId()))
                    .build();
            competitionDTOS.add(competitionDTO);
        }

        return competitionDTOS;
    }

    public String createCompetition(int maxTeams, int maxTeamSize) {

        Competition newCompetition = Competition.builder()
                .maxTeams(maxTeams)
                .maxTeamSize(maxTeamSize)
                .build();

        competitionRepository.save(newCompetition);

        return newCompetition.getCompetitionCode();
    }

    public void addTeamToCompetition(String competitionCode, String teamName) {
        Competition competition = competitionRepository.findCompetitionByCompetitionCode(competitionCode).orElseThrow(() -> new CustomNotFoundException("Competition not found."));
        Team team = teamService.getTeamByTeamName(teamName);

        // If the competition already has the team as a member, throw an error.
        if (competition.hasTeam(team)) {
            throw new AlreadyMemberException();
        }

        competition.addTeam(team);
        competitionRepository.save(competition);
    }

    public void removeTeamFromCompetition(String competitionCode, String teamName) {
        Competition competition = competitionRepository.findCompetitionByCompetitionCode(competitionCode).orElseThrow(() -> new CustomNotFoundException("Competition not found."));
        Team team = teamService.getTeamByTeamName(teamName);

        // If the competition does not have the team as a member, throw an error.
        if (!competition.hasTeam(team)) {
            throw new CustomNotFoundException("Team not found.");
        }

        competition.removeTeam(team);
        competitionRepository.save(competition);
    }


    private TeamDTO convertToTeamDTO(Team team) {
        List<MemberDTO> memberDTOS = new ArrayList<>();
        for (Student member : team.getMembers()) {
            var memberDTO = MemberDTO.builder()
                    .userId(member.getUserId())
                    .username(member.getUsername())
                    .email(member.getEmail())
                    .teamId(member.getTeam().getTeamId())
                    .build();
            memberDTOS.add(memberDTO);
        }

        return TeamDTO.builder()
                .teamId(team.getTeamId())
                .teamName(team.getTeamName())
                .members(memberDTOS)
                .competitionId(team.getCompetition().getCompetitionId())
                .build();
    }

    private Team convertToTeam(TeamDTO teamDTO) {

        return Team.builder()
                .teamId(teamDTO.getTeamId())
                .teamPassword(teamDTO.getTeamPassword())
                .teamName(teamDTO.getTeamName())
                .score(teamDTO.getScore())
                .rank(teamDTO.getRank())
                .numMembers(teamDTO.getNumMembers())
                .competition(competitionRepository.findById(teamDTO.getCompetitionId()).orElseThrow(() -> new CustomNotFoundException("Competition not found.")))
                .members(teamService.getMembersByTeamName(teamDTO.getTeamName()))
                .build();
    }

}
