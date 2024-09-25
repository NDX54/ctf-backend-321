package com.csit321.ctfbackend.room.service;

import com.csit321.ctfbackend.room.dto.internal.CompetitionDTO;
import com.csit321.ctfbackend.room.model.Competition;
import com.csit321.ctfbackend.room.repository.CompetitionRepository;
import com.csit321.ctfbackend.user.dto.external.MemberDTO;
import com.csit321.ctfbackend.user.dto.external.TeamDTO;
import com.csit321.ctfbackend.user.model.Student;
import com.csit321.ctfbackend.user.model.Team;
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

    public List<CompetitionDTO> getAllCompetitions() {
        List<Competition> competitions = competitionRepository.findAll();
        List<CompetitionDTO> competitionDTOS = new ArrayList<>();

        for (Competition competition : competitions) {

            var competitionDTO = CompetitionDTO.builder()
                    .competitionId(competition.getCompetitionId())
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

}
