package com.csit321.ctfbackend.user.service;

import com.csit321.ctfbackend.core.api.exceptions.AlreadyMemberException;
import com.csit321.ctfbackend.core.api.exceptions.CustomNotFoundException;
import com.csit321.ctfbackend.core.api.exceptions.MemberNotInTeamException;
import com.csit321.ctfbackend.room.repository.CompetitionRepository;
import com.csit321.ctfbackend.user.dto.external.MemberDTO;
import com.csit321.ctfbackend.user.dto.external.TeamDTO;
import com.csit321.ctfbackend.user.model.Student;
import com.csit321.ctfbackend.user.model.Team;
import com.csit321.ctfbackend.user.repository.TeamRepository;
import com.csit321.ctfbackend.user.utility.TeamRankingUtility;
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
    private final CompetitionRepository competitionRepository;

    public String createTeam(String teamName) {
        Team newTeam = Team.builder()
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

    public List<TeamDTO> getRankedTeamsByCompetitionCode(Long competitionId) {
        List<Team> teams = teamRepository.findByCompetition_CompetitionId(competitionId);
        TeamRankingUtility.rankTeams(teams);

        List<TeamDTO> teamDTOS = new ArrayList<>();
        for (Team team : teams) {
            teamDTOS.add(convertToTeamDTO(team));
        }
        return teamDTOS;
    }

    public List<TeamDTO> getRankedTeamsByCompetitionCode(String competitionCode) {
        List<Team> teams = competitionRepository.findCompetitionByCompetitionCode(competitionCode)
                .map(competition -> teamRepository.findByCompetition_CompetitionId(competition.getCompetitionId()))
                .orElseThrow(() -> new CustomNotFoundException("Competition not found."));
        TeamRankingUtility.rankTeams(teams);

        List<TeamDTO> teamDTOS = new ArrayList<>();
        for (Team team : teams) {
            teamDTOS.add(convertToTeamDTO(team));
        }
        return teamDTOS;
    }

    /**
     * Retrieves the rank of a specific team in a competition.
     *
     * @param competitionId The ID of the competition.
     * @param teamId        The ID of the team.
     * @return The rank of the team within the competition.
     */
    public int getTeamRankInCompetition(Long competitionId, Long teamId) {
        List<Team> teams = teamRepository.findByCompetition_CompetitionId(competitionId);
        rankTeams(teams);

        return teams.stream()
                .filter(team -> team.getTeamId().equals(teamId))
                .findFirst()
                .map(Team::getRank)
                .orElseThrow(() -> new CustomNotFoundException("Team not found in the competition."));
    }

    /**
     * Overloaded method to retrieve the rank by team name.
     *
     * @param competitionId The ID of the competition.
     * @param teamName      The name of the team.
     * @return The rank of the team within the competition.
     */
    public int getTeamRankInCompetition(Long competitionId, String teamName) {
        List<Team> teams = teamRepository.findByCompetition_CompetitionId(competitionId);
        rankTeams(teams);

        return teams.stream()
                .filter(team -> team.getTeamName().equalsIgnoreCase(teamName))
                .findFirst()
                .map(Team::getRank)
                .orElseThrow(() -> new CustomNotFoundException("Team not found in the competition."));
    }

    public Team getTeamByTeamName(String teamName) {
        return teamRepository.findByTeamName(teamName)
                .orElseThrow(() -> new CustomNotFoundException("Team not found."));
    }

    public List<Student> getMembersByTeamName(String teamName) {
        Team team = teamRepository.findByTeamName(teamName)
                .orElseThrow(() -> new CustomNotFoundException("Team not found."));
        return new ArrayList<>(team.getMembers());
    }

    public TeamDTO getTeamByUsersUsername(String username) {
        Team teamEntity = teamRepository.findTeamByUsers_Username(username)
                .orElseThrow(() -> new CustomNotFoundException("Team not found."));
        return convertToTeamDTO(teamEntity);
    }

    public void addMemberToTeam(String teamPassword, String studentUsername) {
        Team team = teamRepository.findByTeamPassword(teamPassword)
                .orElseThrow(() -> new CustomNotFoundException("Team not found."));
        Student student = studentService.getStudentByUsername(studentUsername);

        if (team.hasMember(student)) {
            throw new AlreadyMemberException();
        }

        team.addMember(student);
        teamRepository.save(team);
    }

    public void removeMemberFromTeam(String teamName, String studentUsername) {
        Team team = teamRepository.findByTeamName(teamName)
                .orElseThrow(() -> new CustomNotFoundException("Team not found."));
        Student student = studentService.getStudentByUsername(studentUsername);

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

    private void rankTeams(List<Team> teams) {
        teams.sort(Comparator.comparingDouble(Team::getScore).reversed());

        int rank = 1;
        double previousScore = Double.NaN;
        int sameRankCount = 1;

        for (Team team : teams) {
            if (Double.compare(team.getScore(), previousScore) == 0) {
                // Same score as previous team
                team.setRank(rank);
                sameRankCount++;
            } else {
                // Different score
                rank += sameRankCount - 1; // Adjust rank if there were ties
                team.setRank(rank);
                sameRankCount = 1;
            }

            previousScore = team.getScore();
        }
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
                .maxMembers(team.getMaxMembers())
                .competitionId(team.getCompetition().getCompetitionId())
                .build();
    }

    public void updateTeamScore(String teamName, double score) {
        Team team = teamRepository.findByTeamName(teamName)
                .orElseThrow(() -> new CustomNotFoundException("Team not found."));
        team.setScore(score);
        teamRepository.save(team);
    }

    public void save(Team team) {
        teamRepository.save(team);
    }
}

