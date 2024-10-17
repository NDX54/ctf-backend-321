package com.csit321.ctfbackend.room.service;

import com.csit321.ctfbackend.core.api.exceptions.AlreadyMemberException;
import com.csit321.ctfbackend.core.api.exceptions.CustomBadRequestException;
import com.csit321.ctfbackend.core.api.exceptions.CustomNotFoundException;
import com.csit321.ctfbackend.core.utilities.RandomString;
import com.csit321.ctfbackend.room.dto.internal.CompetitionDTO;
import com.csit321.ctfbackend.room.enums.Status;
import com.csit321.ctfbackend.room.model.Competition;
import com.csit321.ctfbackend.room.repository.CompetitionRepository;
import com.csit321.ctfbackend.user.dto.external.MemberDTO;
import com.csit321.ctfbackend.user.dto.external.TeamDTO;
import com.csit321.ctfbackend.user.model.Student;
import com.csit321.ctfbackend.user.model.Team;
import com.csit321.ctfbackend.user.repository.StudentRepository;
import com.csit321.ctfbackend.user.service.BaseUserService;
import com.csit321.ctfbackend.user.service.StudentService;
import com.csit321.ctfbackend.user.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final TeamService teamService;
    private final StudentService studentService;
    private final BaseUserService baseUserService;
    private final StudentRepository studentRepository;

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
                    .status(competition.getStatus().getValue())
                    .teamsList(teamService.getTeamsByCompetitionId(competition.getCompetitionId()))
                    .build();
            competitionDTOS.add(competitionDTO);
        }

        return competitionDTOS;
    }

    public String getCompetitionStatus(String competitionCode) {
        Competition competition = competitionRepository.findCompetitionByCompetitionCode(competitionCode).orElseThrow(() -> new CustomNotFoundException("Competition not found."));
        return competition.getStatus().getValue();
    }

    public String createCompetition(String name, int maxTeams, int maxTeamSize) {

        Competition newCompetition = Competition.builder()
                .competitionName(name)
                .maxTeams(maxTeams)
                .maxTeamSize(maxTeamSize)
                .status(Status.STARTED)
                .teamsList(new ArrayList<>())
                .build();

        competitionRepository.save(newCompetition);

        // Create a Random instance
        Random random = new Random();

        // Define the score range
        double minScore = 20.0; // minimum possible score
        double maxScore = 700.0; // maximum possible score

        // Initialize teams with random scores
        for (int i = 1; i <= maxTeams; i++) {

            // Generate a random score between minScore and maxScore
            double randomScore = minScore + (maxScore - minScore) * random.nextDouble();

            Team team = Team.builder()
                    .teamName("Team " + i)
                    .competition(newCompetition)
                    .members(new ArrayList<>())
                    .score(randomScore)
                    .rank(0) // Rank will be updated after sorting
                    .maxMembers(maxTeamSize)
                    .build();
            teamService.save(team);
            newCompetition.addTeam(team);
        }

        // After all teams are added, sort and rank them based on their scores
        List<Team> teams = newCompetition.getTeamsList();

        // Sort teams in descending order of score
        teams.sort(Comparator.comparingDouble(Team::getScore).reversed());

        // Assign ranks
        int rank = 1;
        for (Team team : teams) {
            team.setRank(rank++);
            teamService.save(team);
        }

        // Evenly assign students who are not in any team to the teams

        // Fetch students who are not in any team
        List<Student> unassignedStudents = studentRepository.getAllStudentsNotInTeam();

        // Shuffle the list of students to randomize assignment order
        Collections.shuffle(unassignedStudents);

        int totalTeams = teams.size();
        int totalStudents = unassignedStudents.size();

        int teamIndex = 0;

        for (Student student : unassignedStudents) {
            Team team = teams.get(teamIndex);
            if (team.getNumMembers() < team.getMaxMembers()) {
                team.addMember(student);
                studentService.save(student); // Save the student with the new team
                teamService.save(team);           // Save the team with updated members
            }
            teamIndex = (teamIndex + 1) % totalTeams;
        }

        // Save the competition with updated teams
        competitionRepository.save(newCompetition);

        return newCompetition.getCompetitionCode();
    }

    public String startCompetition(String competitionCode) {
        Competition competition = competitionRepository.findCompetitionByCompetitionCode(competitionCode).orElseThrow(() -> new CustomNotFoundException("Competition not found."));
        competition.setStatus(Status.IN_PROGRESS);
        competitionRepository.save(competition);

        return competition.getStatus().getValue();
    }

    public String endCompetition(String competitionCode) {
        Competition competition = competitionRepository.findCompetitionByCompetitionCode(competitionCode).orElseThrow(() -> new CustomNotFoundException("Competition not found."));
        competition.setStatus(Status.FINISHED);
        competitionRepository.save(competition);

        return competition.getStatus().getValue();
    }

    public void addStudentToTeam(String competitionCode, String teamName, String username) {
        Competition competition = competitionRepository.findCompetitionByCompetitionCode(competitionCode).orElseThrow(() -> new CustomNotFoundException("Competition not found."));
        Team team = teamService.getTeamByTeamName(teamName);
        Student student = studentService.getStudentByUsername(username);

        if (student.getTeam() != null) {
            throw new AlreadyMemberException();
        }

        if (team.isFull(competition.getMaxTeamSize())) {
            throw new CustomBadRequestException("Team is full.");
        }

        team.addMember(student);
        teamService.save(team);
        studentService.save(student);
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
                .teamPassword(team.getTeamPassword())
                .maxMembers(team.getMaxMembers())
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

    public String setCompetitionStatus(String competitionCode, String status) {
        Competition competition = competitionRepository.findCompetitionByCompetitionCode(competitionCode).orElseThrow(() -> new CustomNotFoundException("Competition not found."));
        competition.setStatus(Status.valueOfLabel(status));
        competitionRepository.save(competition);

        return competition.getStatus().getValue();
    }

    public void save(Competition competition) {
        competitionRepository.save(competition);
    }

    public TeamDTO addTeamToCompetition(String competitionCode, String teamName) {
        Competition competition = competitionRepository.findCompetitionByCompetitionCode(competitionCode).orElseThrow(() -> new CustomNotFoundException("Competition not found."));
        Team team = Team.builder()
                .teamName(teamName)
                .members(new ArrayList<>())
                .teamPassword(new RandomString(6).nextString())
                .score(0.0)
                .rank(0)
                .maxMembers(competition.getMaxTeamSize())
                .build();
        team.setCompetition(competition);
        teamService.save(team);
        competition.addTeam(team);
        this.save(competition);
        return convertToTeamDTO(team);
    }
}
