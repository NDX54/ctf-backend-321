package com.csit321.ctfbackend.user.service;

import com.csit321.ctfbackend.core.api.exceptions.AlreadyMemberException;
import com.csit321.ctfbackend.core.api.exceptions.CustomNotFoundException;
import com.csit321.ctfbackend.core.api.exceptions.MemberNotInTeamException;
import com.csit321.ctfbackend.user.dto.external.MemberDTO;
import com.csit321.ctfbackend.user.dto.external.PublicStudentDTO;
import com.csit321.ctfbackend.user.dto.external.TeamDTO;
import com.csit321.ctfbackend.user.model.Student;
import com.csit321.ctfbackend.user.model.Team;
import com.csit321.ctfbackend.user.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final StudentService studentService;

    public String createTeam(String teamName) {
        var newTeam = Team.builder()
                .teamName(teamName)
                .build();
        teamRepository.save(newTeam);

        return newTeam.getTeamPassword();
    }

    public List<TeamDTO> getTeamsByCompetitionId(Long competitionId) {
        List<Team> teams = teamRepository.findByCompetition_CompetitionId(competitionId);
        List<TeamDTO> teamDTOS = new ArrayList<>();
        for (Team team : teams) {
            teamDTOS.add(convertToTeamDTO(team));
        }
        return teamDTOS;
    }

    public List<TeamDTO> getRankedTeams(Long competitionId) {
        List<Team> teams = teamRepository.findByCompetition_CompetitionId(competitionId);
        List<TeamDTO> teamDTOS = new ArrayList<>();

        teams.sort(Comparator.comparingDouble(Team::getScore).reversed());

        int rank = 1;
        for (Team team : teams) {
            TeamDTO teamDTO = convertToTeamDTO(team);
            teamDTO.setRank(rank++);
            teamDTOS.add(teamDTO);
        }
        return teamDTOS;
    }

    public Team getTeamByTeamName(String teamName) {
        return teamRepository.findByTeamName(teamName).orElseThrow(() -> new CustomNotFoundException("Team not found."));
    }

    public List<Student> getMembersByTeamName(String teamName) {
        Team team = teamRepository.findByTeamName(teamName).orElseThrow(() -> new CustomNotFoundException("Team not found."));

        return new ArrayList<>(team.getMembers());
    }

    public void addMemberToTeam(String teamPassword, String studentUsername) {
        Team team = teamRepository.findByTeamPassword(teamPassword).orElseThrow(() -> new CustomNotFoundException("Team not found."));
        Student student = studentService.getStudentByUsername(studentUsername);

        // If the team already has the student as a member, throw an error.
        if (team.hasMember(student)) {
            throw new AlreadyMemberException();
        }

        team.addMember(student);
        teamRepository.save(team);
    }

    public void removeMemberFromTeam(String teamName, String studentUsername) {
        Team team = teamRepository.findByTeamName(teamName).orElseThrow(() -> new CustomNotFoundException("Team not found."));
        Student student = studentService.getStudentByUsername(studentUsername);

        // If the team does not have the student as a member, throw an error.
        if (!team.hasMember(student)) {
            throw new MemberNotInTeamException();
        }

        team.removeMember(student);
        teamRepository.save(team);
    }

    public List<TeamDTO> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        List<TeamDTO> teamDTOS = new ArrayList<>();
        for (Team team : teams) {
            teamDTOS.add(convertToTeamDTO(team));
        }
        return teamDTOS;
    }



    private TeamDTO convertToTeamDTO(Team team) {
        List<MemberDTO> memberDTOS = new ArrayList<>();
        for (Student student : team.getMembers()) {
            memberDTOS.add(MemberDTO.builder()
                    .userId(student.getUserId())
                    .username(student.getUsername())
                    .email(student.getEmail())
                    .teamId(team.getTeamId())
                    .build());
        }

        return TeamDTO.builder()
                .teamId(team.getTeamId())
                .teamName(team.getTeamName())
                .teamPassword(team.getTeamPassword())
                .score(team.getScore())
                .rank(team.getRank())
                .numMembers(team.getMembers().size())
                .members(memberDTOS)
                .competitionId(team.getCompetition().getCompetitionId())
                .build();
    }

}
