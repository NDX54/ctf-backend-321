package com.csit321.ctfbackend.core.testdata;

import com.csit321.ctfbackend.room.model.Challenge;
import com.csit321.ctfbackend.room.model.Competition;
import com.csit321.ctfbackend.room.model.Question;
import com.csit321.ctfbackend.room.enums.Difficulty;
import com.csit321.ctfbackend.room.repository.ChallengeRepository;
import com.csit321.ctfbackend.room.repository.CompetitionRepository;
import com.csit321.ctfbackend.user.model.Student;
import com.csit321.ctfbackend.user.model.Teacher;
import com.csit321.ctfbackend.user.model.Team;
import com.csit321.ctfbackend.user.model.enums.Role;
import com.csit321.ctfbackend.user.model.enums.UserType;
import com.csit321.ctfbackend.user.repository.BaseUserRepository;
import com.csit321.ctfbackend.user.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

// Test data initializer for the application.
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ChallengeRepository challengeRepository;
    private final BaseUserRepository baseUserRepository;
    private final CompetitionRepository competitionRepository;
    private final TeamRepository teamRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        createTeachers();
        createStudents();
        createTestChallenges();
        createTestCompetitions();
    }

    private int generateRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private void createTeachers() {
        Teacher teacher1 = Teacher.teacherBuilderEntity()
                .username("teacher1")
                .password(passwordEncoder.encode("12345678"))
                .email("teacher1@t.com")
                .role(Role.TEACHER)
                .userType(UserType.TEACHER)
                .school("School 1")
                .build();

        Teacher teacher2 = Teacher.teacherBuilderEntity()
                .username("teacher2")
                .password(passwordEncoder.encode("12345678"))
                .email("teacher2@t.com")
                .role(Role.TEACHER)
                .userType(UserType.TEACHER)
                .school("School 2")
                .build();

        Teacher teacher3 = Teacher.teacherBuilderEntity()
                .username("teacher3")
                .password(passwordEncoder.encode("12345678"))
                .email("teacher3@t.com")
                .role(Role.TEACHER)
                .userType(UserType.TEACHER)
                .school("School 3")
                .build();

        Teacher teacher4 = Teacher.teacherBuilderEntity()
                .username("teacher4")
                .password(passwordEncoder.encode("12345678"))
                .email("teacher4@t.com")
                .role(Role.TEACHER)
                .userType(UserType.TEACHER)
                .school("School 4")
                .build();

        Teacher teacher5 = Teacher.teacherBuilderEntity()
                .username("teacher5")
                .password(passwordEncoder.encode("12345678"))
                .email("teacher5@t.com")
                .role(Role.TEACHER)
                .userType(UserType.TEACHER)
                .school("School 5")
                .build();

        Teacher teacher6 = Teacher.teacherBuilderEntity()
                .username("teacher6")
                .password(passwordEncoder.encode("12345678"))
                .email("teacher6@t.com")
                .role(Role.TEACHER)
                .userType(UserType.TEACHER)
                .school("School 6")
                .build();

        Teacher teacher7 = Teacher.teacherBuilderEntity()
                .username("teacher7")
                .password(passwordEncoder.encode("12345678"))
                .email("teacher7@t.com")
                .role(Role.TEACHER)
                .userType(UserType.TEACHER)
                .school("School 7")
                .build();

        Teacher teacher8 = Teacher.teacherBuilderEntity()
                .username("teacher8")
                .password(passwordEncoder.encode("12345678"))
                .email("teacher8@t.com")
                .role(Role.TEACHER)
                .userType(UserType.TEACHER)
                .school("School 8")
                .build();

        Teacher teacher9 = Teacher.teacherBuilderEntity()
                .username("teacher9")
                .password(passwordEncoder.encode("12345678"))
                .email("teacher9@t.com")
                .role(Role.TEACHER)
                .userType(UserType.TEACHER)
                .school("School 9")
                .build();

        Teacher teacher10 = Teacher.teacherBuilderEntity()
                .username("teacher10")
                .password(passwordEncoder.encode("12345678"))
                .email("teacher10@t.com")
                .role(Role.TEACHER)
                .userType(UserType.TEACHER)
                .school("School 10")
                .build();

        List<Teacher> teachers = Arrays.asList(
                teacher1,
                teacher2,
                teacher3,
                teacher4,
                teacher5,
                teacher6,
                teacher7,
                teacher8,
                teacher9,
                teacher10
        );

        baseUserRepository.saveAll(teachers);
    }

    private void createStudents() {
        Student student1 = Student.studentBuilderEntity()
                .username("student1")
                .password(passwordEncoder.encode("12345678"))
                .email("student1@g.com")
                .role(Role.STUDENT)
                .userType(UserType.STUDENT)
                .yearLevel(generateRandomNumber(1, 12))
                .build();

        Student student2 = Student.studentBuilderEntity()
                .username("student2")
                .password(passwordEncoder.encode("12345678"))
                .email("student2@g.com")
                .role(Role.STUDENT)
                .userType(UserType.STUDENT)
                .yearLevel(generateRandomNumber(1, 12))
                .build();

        Student student3 = Student.studentBuilderEntity()
                .username("student3")
                .password(passwordEncoder.encode("12345678"))
                .email("student3@g.com")
                .role(Role.STUDENT)
                .userType(UserType.STUDENT)
                .yearLevel(generateRandomNumber(1, 12))
                .build();

        Student student4 = Student.studentBuilderEntity()
                .username("student4")
                .password(passwordEncoder.encode("12345678"))
                .email("student4@g.com")
                .role(Role.STUDENT)
                .userType(UserType.STUDENT)
                .yearLevel(generateRandomNumber(1, 12))
                .build();

        Student student5 = Student.studentBuilderEntity()
                .username("student5")
                .password(passwordEncoder.encode("12345678"))
                .email("student5@g.com")
                .role(Role.STUDENT)
                .userType(UserType.STUDENT)
                .yearLevel(generateRandomNumber(1, 12))
                .build();

        Student student6 = Student.studentBuilderEntity()
                .username("student6")
                .password(passwordEncoder.encode("12345678"))
                .email("student6@g.com")
                .role(Role.STUDENT)
                .userType(UserType.STUDENT)
                .yearLevel(generateRandomNumber(1, 12))
                .build();

        Student student7 = Student.studentBuilderEntity()
                .username("student7")
                .password(passwordEncoder.encode("12345678"))
                .email("student7@g.com")
                .role(Role.STUDENT)
                .userType(UserType.STUDENT)
                .yearLevel(generateRandomNumber(1, 12))
                .build();

        Student student8 = Student.studentBuilderEntity()
                .username("student8")
                .password(passwordEncoder.encode("12345678"))
                .email("student8@g.com")
                .role(Role.STUDENT)
                .userType(UserType.STUDENT)
                .yearLevel(generateRandomNumber(1, 12))
                .build();

        Student student9 = Student.studentBuilderEntity()
                .username("student9")
                .password(passwordEncoder.encode("12345678"))
                .email("student9@g.com")
                .role(Role.STUDENT)
                .userType(UserType.STUDENT)
                .yearLevel(generateRandomNumber(1, 12))
                .build();

        Student student10 = Student.studentBuilderEntity()
                .username("student10")
                .password(passwordEncoder.encode("12345678"))
                .email("student10@g.com")
                .role(Role.STUDENT)
                .userType(UserType.STUDENT)
                .yearLevel(generateRandomNumber(1, 12))
                .build();

        List<Student> students = Arrays.asList(
                student1,
                student2,
                student3,
                student4,
                student5,
                student6,
                student7,
                student8,
                student9,
                student10
        );

        baseUserRepository.saveAll(students);
    }

    private void createTestChallenges() {
        Challenge challenge1 = Challenge.builder()
                .name("Cybersecurity Challenge")
                .description("Test your knowledge on various cybersecurity concepts.")
                .difficulty(Difficulty.INTERMEDIATE)
                .points(100)
                .build();

        List<Question> questions1 = Arrays.asList(
                Question.builder()
                        .questionText("Which of the following is a common method used in phishing attacks?")
                        .options(Arrays.asList("SQL Injection", "Email Spoofing", "Brute Force", "Denial of Service"))
                        .answer("Email Spoofing")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(challenge1)
                        .build(),
                Question.builder()
                        .questionText("What is the primary purpose of a firewall?")
                        .options(Arrays.asList("To manage network traffic", "To encrypt data", "To monitor employee productivity", "To block unauthorized access"))
                        .answer("To block unauthorized access")
                        .points(50.0)
                        .correctOption(3)
                        .challenge(challenge1)
                        .build(),
                Question.builder()
                        .questionText("Which cybersecurity principle involves maintaining the accuracy and reliability of data?")
                        .options(Arrays.asList("Confidentiality", "Integrity", "Availability", "Authentication"))
                        .answer("Integrity")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(challenge1)
                        .build(),
                Question.builder()
                        .questionText("What is the practice of protecting information by transforming it into an unreadable format?")
                        .options(Arrays.asList("Decryption", "Encryption", "Hashing", "Tokenization"))
                        .answer("Encryption")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(challenge1)
                        .build(),
                Question.builder()
                        .questionText("What type of malware is designed to spread across networks by exploiting vulnerabilities?")
                        .options(Arrays.asList("Virus", "Worm", "Trojan", "Spyware"))
                        .answer("Worm")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(challenge1)
                        .build(),
                Question.builder()
                        .questionText("Which security measure involves verifying the identity of a user before allowing access?")
                        .options(Arrays.asList("Authorization", "Authentication", "Accounting", "Auditing"))
                        .answer("Authentication")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(challenge1)
                        .build(),
                Question.builder()
                        .questionText("What does the 'CIA' triad stand for in cybersecurity?")
                        .options(Arrays.asList("Confidentiality, Integrity, Availability", "Confidentiality, Identity, Accessibility", "Confidentiality, Integrity, Authentication", "Confidentiality, Information, Authorization"))
                        .answer("Confidentiality, Integrity, Availability")
                        .points(50.0)
                        .correctOption(0)
                        .challenge(challenge1)
                        .build(),
                Question.builder()
                        .questionText("What type of attack involves overwhelming a system with traffic to render it unusable?")
                        .options(Arrays.asList("Phishing", "Man-in-the-Middle", "Denial of Service (DoS)", "Cross-site Scripting (XSS)"))
                        .answer("Denial of Service (DoS)")
                        .points(50.0)
                        .correctOption(2)
                        .challenge(challenge1)
                        .build(),
                Question.builder()
                        .questionText("Which tool is commonly used to test network security by simulating attacks?")
                        .options(Arrays.asList("Antivirus", "Penetration Testing Tool", "Firewall", "Encryption Software"))
                        .answer("Penetration Testing Tool")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(challenge1)
                        .build(),
                Question.builder()
                        .questionText("What is the process of converting data into a format that is unreadable without a key?")
                        .options(Arrays.asList("Hashing", "Encryption", "Decryption", "Tokenization"))
                        .answer("Encryption")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(challenge1)
                        .build(),
                Question.builder()
                        .questionText("What is a 'zero-day' vulnerability?")
                        .options(Arrays.asList("A vulnerability that has been known and unpatched for a long time", "A vulnerability discovered and exploited on the same day", "A vulnerability that is only a theory and not yet discovered", "A vulnerability that only affects specific software versions"))
                        .answer("A vulnerability discovered and exploited on the same day")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(challenge1)
                        .build(),
                Question.builder()
                        .questionText("Which tool is used to detect, prevent, and respond to security incidents?")
                        .options(Arrays.asList("SIEM", "VPN", "IDS", "WAF"))
                        .answer("SIEM")
                        .points(50.0)
                        .correctOption(0)
                        .challenge(challenge1)
                        .build(),
                Question.builder()
                        .questionText("What is the primary goal of a cybersecurity audit?")
                        .options(Arrays.asList("To update antivirus software", "To review and assess security policies and controls", "To train employees on security practices", "To install security patches"))
                        .answer("To review and assess security policies and controls")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(challenge1)
                        .build(),
                Question.builder()
                        .questionText("What is the purpose of multi-factor authentication (MFA)?")
                        .options(Arrays.asList("To encrypt data", "To provide a secondary means of identification", "To increase the speed of logging in", "To allow for password recovery"))
                        .answer("To provide a secondary means of identification")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(challenge1)
                        .build(),
                Question.builder()
                        .questionText("Which type of attack involves tricking a user into providing sensitive information?")
                        .options(Arrays.asList("Phishing", "Brute Force", "SQL Injection", "Man-in-the-Middle"))
                        .answer("Phishing")
                        .points(50.0)
                        .correctOption(0)
                        .challenge(challenge1)
                        .build()
        );

        Challenge challenge2 = Challenge.builder()
                .name("Hashing")
                .description("Hashing challenge.")
                .difficulty(Difficulty.BEGINNER)
                .points(100)
                .build();

        Challenge challenge3 = Challenge.builder()
                .name("OSINT")
                .description("Test your knowledge on OSINT.")
                .difficulty(Difficulty.ADVANCED)
                .points(100)
                .build();

        challenge1.setQuestions(questions1);

        List<Challenge> challenges = Arrays.asList(
                challenge1,
                challenge2,
                challenge3
        );

        challengeRepository.saveAll(challenges);
    }

    private void createTestCompetitions() {

        Team team1 = Team.builder()
                .teamName("Team 1")
                .build();

        Team team2 = Team.builder()
                .teamName("Team 2")
                .build();

        Team team3 = Team.builder()
                .teamName("Team 3")
                .build();

        Team team4 = Team.builder()
                .teamName("Team 4")
                .build();

        List<Team> teams = Arrays.asList(
                team1,
                team2,
                team3,
                team4
        );

        teamRepository.saveAll(teams);

        Competition competition1 = Competition.builder()
                .competitionCode("COMP1")
                .maxTeams(10)
                .maxTeamSize(5)
                .teamsList(Arrays.asList(team1, team2))
                .build();

        Competition competition2 = Competition.builder()
                .competitionCode("COMP2")
                .maxTeams(5)
                .maxTeamSize(4)
                .teamsList(Arrays.asList(team3, team4))
                .build();

        List<Competition> competitions = Arrays.asList(
                competition1,
                competition2
        );

        competitionRepository.saveAll(competitions);

        team1.setCompetition(competition1);
        team2.setCompetition(competition1);
        team3.setCompetition(competition2);
        team4.setCompetition(competition2);

        teamRepository.saveAll(teams);
    }
}
