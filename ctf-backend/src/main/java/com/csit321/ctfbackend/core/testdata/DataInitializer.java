package com.csit321.ctfbackend.core.testdata;

import com.csit321.ctfbackend.room.model.*;
import com.csit321.ctfbackend.room.enums.Difficulty;
import com.csit321.ctfbackend.room.repository.ChallengeRepository;
import com.csit321.ctfbackend.room.repository.CompetitionRepository;
import com.csit321.ctfbackend.room.service.ChallengeService;
import com.csit321.ctfbackend.room.service.QuestionService;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

// Test data initializer for the application.
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final JsonImportService jsonImportService;
    private final QuestionService questionService;
    private final ChallengeService challengeService;
    private final ChallengeRepository challengeRepository;
    private final BaseUserRepository baseUserRepository;
    private final CompetitionRepository competitionRepository;
    private final TeamRepository teamRepository;
    private final PasswordEncoder passwordEncoder;

    private static final Logger logger = Logger.getLogger(DataInitializer.class.getName());

    @Override
    public void run(String... args) throws Exception {
//        createTeachers();
//        createStudents();
//        createTestChallenges();
//        createChallengesWithRooms();
        createCybersecurityChallenge();
        createOSINTChallenges();
        createNetworkSecurityChallenge();
        createCryptographyChallenge();
        createEthicalHackingChallenge();
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

    private void createTestCompetitions() {

        Team team1 = Team.builder()
                .teamName("Team 1")
                .score(200.45)
                .build();

        Team team2 = Team.builder()
                .teamName("Team 2")
                .score(90.25)
                .build();

        Team team3 = Team.builder()
                .teamName("Team 3")
                .score(30.24)
                .build();

        Team team4 = Team.builder()
                .teamName("Team 4")
                .score(120.70)
                .build();

        List<Team> teams = Arrays.asList(
                team1,
                team2,
                team3,
                team4
        );

        teamRepository.saveAll(teams);

        Competition competition1 = Competition.builder()
                .competitionName("Competition 1")
                .competitionCode("COMP1")
                .maxTeams(10)
                .maxTeamSize(5)
                .teamsList(Arrays.asList(team1, team2))
                .build();

        Competition competition2 = Competition.builder()
                .competitionName("Competition 2")
                .competitionCode("COMP2")
                .maxTeams(5)
                .maxTeamSize(4)
                .teamsList(Arrays.asList(team3, team4))
                .build();

        List<Competition> competitions = Arrays.asList(
                competition1,
                competition2
        );

//        competitionRepository.saveAll(competitions);
        competitionRepository.save(competition1);

        team1.setCompetition(competition1);
        team2.setCompetition(competition1);
        team3.setCompetition(competition1);
        team4.setCompetition(competition1);

        teamRepository.saveAll(teams);
    }

    private void createCybersecurityChallenge() {
        // Create a new Room
        Room room = new Room();
        room.setName("Cybersecurity Basics");
        room.setDescription("Welcome to the Cybersecurity Basics room. Test your knowledge and solve the challenges.");
        room.setDifficulty(Difficulty.BEGINNER);

        // Create a new Challenge and associate it with the Room
        Challenge challenge = Challenge.builder()
                .name("Cybersecurity Basics")
                .description("A set of questions covering basic cybersecurity concepts.")
                .difficulty(Difficulty.BEGINNER)
                .points(0) // We'll calculate total points from questions
                .questions(new ArrayList<>())
                .room(room) // Associate the Room
                .isChallengeOpen(true)
                .build();

        // Associate the Room with the Challenge
//        room.setChallenge(challenge);

        // Create the list of Questions with dynamic descriptions, hints, and flags
        List<Question> questions = Arrays.asList(
                Question.builder()
                        .questionText("Which of the following is a common method used in phishing attacks?")
                        .description("First, let's start with the basics.")
                        .hint("Remember common attack methods.")
                        .flag("FLAG{EMAIL_SPOOFING}")
                        .options(Arrays.asList("SQL Injection", "Email Spoofing", "Brute Force", "Denial of Service"))
                        .answer("Email Spoofing")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(challenge)
                        .room(room)
                        .build(),
                Question.builder()
                        .questionText("What is the primary purpose of a firewall?")
                        .description("Now, let's dive a bit deeper.")
                        .hint("Think about network security barriers.")
                        .flag("FLAG{BLOCK_UNAUTHORIZED_ACCESS}")
                        .options(Arrays.asList("To manage network traffic", "To encrypt data", "To monitor employee productivity", "To block unauthorized access"))
                        .answer("To block unauthorized access")
                        .points(50.0)
                        .correctOption(3)
                        .challenge(challenge)
                        .room(room)
                        .build(),
                Question.builder()
                        .questionText("Which cybersecurity principle involves maintaining the accuracy and reliability of data?")
                        .description("Understanding fundamental cybersecurity principles.")
                        .hint("Consider the aspect that ensures data is not altered.")
                        .flag("FLAG{DATA_INTEGRITY}")
                        .options(Arrays.asList("Confidentiality", "Integrity", "Availability", "Authentication"))
                        .answer("Integrity")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(challenge)
                        .room(room)
                        .build(),
                Question.builder()
                        .questionText("What is the practice of protecting information by transforming it into an unreadable format?")
                        .description("Exploring data protection techniques.")
                        .hint("Think about converting plaintext to ciphertext.")
                        .flag("FLAG{USE_ENCRYPTION}")
                        .options(Arrays.asList("Decryption", "Encryption", "Hashing", "Tokenization"))
                        .answer("Encryption")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(challenge)
                        .room(room)
                        .build(),
                Question.builder()
                        .questionText("What type of malware is designed to spread across networks by exploiting vulnerabilities?")
                        .description("Identifying different types of malware.")
                        .hint("Consider malware that replicates itself without human intervention.")
                        .flag("FLAG{WORM_DETECTED}")
                        .options(Arrays.asList("Virus", "Worm", "Trojan", "Spyware"))
                        .answer("Worm")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(challenge)
                        .room(room)
                        .build(),
                Question.builder()
                        .questionText("Which security measure involves verifying the identity of a user before allowing access?")
                        .description("Focusing on user access controls.")
                        .hint("Think about login processes.")
                        .flag("FLAG{AUTHENTICATION_REQUIRED}")
                        .options(Arrays.asList("Authorization", "Authentication", "Accounting", "Auditing"))
                        .answer("Authentication")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(challenge)
                        .room(room)
                        .build(),
                Question.builder()
                        .questionText("What does the 'CIA' triad stand for in cybersecurity?")
                        .description("Reviewing core cybersecurity concepts.")
                        .hint("Consider the three primary goals of information security.")
                        .flag("FLAG{CONFIDENTIALITY_INTEGRITY_AVAILABILITY}")
                        .options(Arrays.asList(
                                "Confidentiality, Integrity, Availability",
                                "Confidentiality, Identity, Accessibility",
                                "Confidentiality, Integrity, Authentication",
                                "Confidentiality, Information, Authorization"))
                        .answer("Confidentiality, Integrity, Availability")
                        .points(50.0)
                        .correctOption(0)
                        .challenge(challenge)
                        .room(room)
                        .build(),
                Question.builder()
                        .questionText("What type of attack involves overwhelming a system with traffic to render it unusable?")
                        .description("Understanding common network attacks.")
                        .hint("Think about attacks that cause service disruptions.")
                        .flag("FLAG{DOS_ATTACK}")
                        .options(Arrays.asList("Phishing", "Man-in-the-Middle", "Denial of Service (DoS)", "Cross-site Scripting (XSS)"))
                        .answer("Denial of Service (DoS)")
                        .points(50.0)
                        .correctOption(2)
                        .challenge(challenge)
                        .room(room)
                        .build(),
                Question.builder()
                        .questionText("Which tool is commonly used to test network security by simulating attacks?")
                        .description("Exploring security assessment tools.")
                        .hint("Think about tools used by ethical hackers.")
                        .flag("FLAG{USE_PEN_TESTING_TOOL}")
                        .options(Arrays.asList("Antivirus", "Penetration Testing Tool", "Firewall", "Encryption Software"))
                        .answer("Penetration Testing Tool")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(challenge)
                        .room(room)
                        .build(),
                Question.builder()
                        .questionText("What is the process of converting data into a format that is unreadable without a key?")
                        .description("Delving deeper into data security.")
                        .hint("This process ensures data privacy during transmission.")
                        .flag("FLAG{ENCRYPT_DATA}")
                        .options(Arrays.asList("Hashing", "Encryption", "Decryption", "Tokenization"))
                        .answer("Encryption")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(challenge)
                        .room(room)
                        .build(),
                Question.builder()
                        .questionText("What is a 'zero-day' vulnerability?")
                        .description("Understanding vulnerabilities and exploits.")
                        .hint("Consider vulnerabilities that are newly discovered and unpatched.")
                        .flag("FLAG{ZERO_DAY_FOUND}")
                        .options(Arrays.asList(
                                "A vulnerability that has been known and unpatched for a long time",
                                "A vulnerability discovered and exploited on the same day",
                                "A vulnerability that is only a theory and not yet discovered",
                                "A vulnerability that only affects specific software versions"))
                        .answer("A vulnerability discovered and exploited on the same day")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(challenge)
                        .room(room)
                        .build(),
                Question.builder()
                        .questionText("Which tool is used to detect, prevent, and respond to security incidents?")
                        .description("Looking at security monitoring tools.")
                        .hint("Think about systems that aggregate and analyze security logs.")
                        .flag("FLAG{DEPLOY_SIEM}")
                        .options(Arrays.asList("SIEM", "VPN", "IDS", "WAF"))
                        .answer("SIEM")
                        .points(50.0)
                        .correctOption(0)
                        .challenge(challenge)
                        .room(room)
                        .build(),
                Question.builder()
                        .questionText("What is the primary goal of a cybersecurity audit?")
                        .description("Understanding the purpose of security assessments.")
                        .hint("Consider reviewing and evaluating security measures.")
                        .flag("FLAG{AUDIT_COMPLETED}")
                        .options(Arrays.asList(
                                "To update antivirus software",
                                "To review and assess security policies and controls",
                                "To train employees on security practices",
                                "To install security patches"))
                        .answer("To review and assess security policies and controls")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(challenge)
                        .room(room)
                        .build(),
                Question.builder()
                        .questionText("What is the purpose of multi-factor authentication (MFA)?")
                        .description("Enhancing security in authentication processes.")
                        .hint("Think about adding extra layers of verification.")
                        .flag("FLAG{MFA_ENABLED}")
                        .options(Arrays.asList(
                                "To encrypt data",
                                "To provide a secondary means of identification",
                                "To increase the speed of logging in",
                                "To allow for password recovery"))
                        .answer("To provide a secondary means of identification")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(challenge)
                        .room(room)
                        .build(),
                Question.builder()
                        .questionText("Which type of attack involves tricking a user into providing sensitive information?")
                        .description("Exploring social engineering attacks.")
                        .hint("Think about fraudulent attempts to obtain personal data.")
                        .flag("FLAG{PHISHING_DETECTED}")
                        .options(Arrays.asList("Phishing", "Brute Force", "SQL Injection", "Man-in-the-Middle"))
                        .answer("Phishing")
                        .points(50.0)
                        .correctOption(0)
                        .challenge(challenge)
                        .room(room)
                        .build()
        );


        // Calculate total points and add questions to the challenge
        double totalPoints = 0;
        for (Question question : questions) {
            totalPoints += question.getPoints();
            challenge.addQuestion(question);
        }
        challenge.setPoints(totalPoints);
        room.setPoints(totalPoints);

        // Save the Challenge (cascades to save Room and Questions)
        challengeRepository.save(challenge);
    }

    private void createOSINTChallenges() {
        // Create a new Room for OSINT Basics Beginner
        Room osintBasicsRoom = new Room();
        osintBasicsRoom.setName("Osint Basics");
        osintBasicsRoom.setDescription("Welcome to the OSINT Basics room. Explore the world of Open-source Intelligence.");
        osintBasicsRoom.setDifficulty(Difficulty.BEGINNER);

        // Create a new Challenge and associate it with the Room
        Challenge osintBasicsChallenge = Challenge.builder()
                .name("OSINT Basics")
                .description("A set of questions covering basic concepts of Open-source Intelligence.")
                .difficulty(Difficulty.BEGINNER)
                .points(0) // We'll calculate total points from questions
                .questions(new ArrayList<>())
                .room(osintBasicsRoom) // Associate the Room
                .isChallengeOpen(true)
                .build();

        // Create the list of Questions for OSINT Basics Beginner
        List<Question> osintBasicsQuestions = Arrays.asList(
                Question.builder()
                        .questionText("What is Open-source Intelligence (OSINT) and why is it important?")
                        .description("First, let's understand the fundamentals of OSINT.")
                        .hint("Consider how OSINT helps in gathering publicly available information.")
                        .flag("FLAG{OSINT_IMPORTANCE}")
                        .options(Arrays.asList(
                                "OSINT is classified information; it's important for secrecy.",
                                "OSINT is random data; it's not important.",
                                "OSINT is finding information from the internet to learn new things. It helps keep us safe online.",
                                "OSINT is a type of malware; it's important to avoid it."
                        ))
                        .answer("OSINT is finding information from the internet to learn new things. It helps keep us safe online.")
                        .points(50.0)
                        .correctOption(2)
                        .challenge(osintBasicsChallenge)
                        .room(osintBasicsRoom)
                        .build(),
                Question.builder()
                        .questionText("Using simple tools like Google, find any three interesting facts about 'John Doe'.")
                        .description("Practice using search engines to gather information.")
                        .hint("Think about common uses of the name 'John Doe'.")
                        .flag("FLAG{JOHN_DOE_FACTS}")
                        .options(Arrays.asList(
                                "John Doe is a fictional character in a book.",
                                "John Doe is a famous athlete.",
                                "John Doe is a common name used for unknown people, it's used in legal cases, and there's even a movie named 'John Doe'.",
                                "John Doe invented the internet."
                        ))
                        .answer("John Doe is a common name used for unknown people, it's used in legal cases, and there's even a movie named 'John Doe'.")
                        .points(50.0)
                        .correctOption(2)
                        .challenge(osintBasicsChallenge)
                        .room(osintBasicsRoom)
                        .build(),
                Question.builder()
                        .questionText("Why is it important to respect people's privacy when looking for information online?")
                        .description("Understanding ethical considerations in OSINT.")
                        .hint("Consider the rights individuals have regarding their personal information.")
                        .flag("FLAG{RESPECT_PRIVACY}")
                        .options(Arrays.asList(
                                "Because privacy laws don't matter online.",
                                "It's important to respect privacy because everyone deserves to feel safe and secure.",
                                "Privacy is only important for celebrities.",
                                "You should share all information you find."
                        ))
                        .answer("It's important to respect privacy because everyone deserves to feel safe and secure.")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(osintBasicsChallenge)
                        .room(osintBasicsRoom)
                        .build(),
                Question.builder()
                        .questionText("How can social media help us find information about people?")
                        .description("Exploring social media as an OSINT tool.")
                        .hint("Think about the types of information people share on social platforms.")
                        .flag("FLAG{SOCIAL_MEDIA_OSINT}")
                        .options(Arrays.asList(
                                "Social media is private and cannot be used.",
                                "Social media shows what people like, who their friends are, and what they do.",
                                "Social media only contains fake information.",
                                "Social media is not useful for finding information."
                        ))
                        .answer("Social media shows what people like, who their friends are, and what they do.")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(osintBasicsChallenge)
                        .room(osintBasicsRoom)
                        .build(),
                Question.builder()
                        .questionText("What is the difference between using a computer tool to find information and looking it up by yourself?")
                        .description("Comparing manual and automated OSINT methods.")
                        .hint("Consider speed and understanding.")
                        .flag("FLAG{TOOLS_VS_MANUAL}")
                        .options(Arrays.asList(
                                "Tools are faster, but looking up by yourself helps you understand better.",
                                "There is no difference.",
                                "Manual search is faster than tools.",
                                "Using tools is illegal."
                        ))
                        .answer("Tools are faster, but looking up by yourself helps you understand better.")
                        .points(50.0)
                        .correctOption(0)
                        .challenge(osintBasicsChallenge)
                        .room(osintBasicsRoom)
                        .build(),
                Question.builder()
                        .questionText("Find a publicly traded company (like Apple) and list one possible risk they might face online.")
                        .description("Analyzing risks for companies using OSINT.")
                        .hint("Consider cybersecurity threats to corporations.")
                        .flag("FLAG{COMPANY_RISKS}")
                        .options(Arrays.asList(
                                "They might run out of products.",
                                "People might try to hack them to steal new product information.",
                                "They have no risks.",
                                "Their stock prices will always go up."
                        ))
                        .answer("People might try to hack them to steal new product information.")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(osintBasicsChallenge)
                        .room(osintBasicsRoom)
                        .build(),
                Question.builder()
                        .questionText("How can tricking someone online (social engineering) help in finding information?")
                        .description("Understanding social engineering in OSINT.")
                        .hint("Think about manipulation techniques.")
                        .flag("FLAG{SOCIAL_ENGINEERING}")
                        .options(Arrays.asList(
                                "Tricking someone can make them give away secrets they shouldn't.",
                                "It doesn't help at all.",
                                "It's illegal and never used.",
                                "It's only used in marketing."
                        ))
                        .answer("Tricking someone can make them give away secrets they shouldn't.")
                        .points(50.0)
                        .correctOption(0)
                        .challenge(osintBasicsChallenge)
                        .room(osintBasicsRoom)
                        .build(),
                Question.builder()
                        .questionText("How can you tell if the information you found online is true or not?")
                        .description("Verifying information in OSINT.")
                        .hint("Consider cross-referencing sources.")
                        .flag("FLAG{VERIFY_INFORMATION}")
                        .options(Arrays.asList(
                                "Assume everything online is true.",
                                "Check if the information is the same on other websites.",
                                "There's no way to tell.",
                                "Only trust social media posts."
                        ))
                        .answer("Check if the information is the same on other websites.")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(osintBasicsChallenge)
                        .room(osintBasicsRoom)
                        .build(),
                Question.builder()
                        .questionText("Can OSINT help catch cybercriminals? How?")
                        .description("Exploring OSINT in cybersecurity.")
                        .hint("Think about tracking online activities.")
                        .flag("FLAG{OSINT_CATCH_CRIMINALS}")
                        .options(Arrays.asList(
                                "No, OSINT is not used in cybersecurity.",
                                "Yes, it can find clues about bad things people do online.",
                                "Only law enforcement can catch criminals.",
                                "Cybercriminals cannot be caught."
                        ))
                        .answer("Yes, it can find clues about bad things people do online.")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(osintBasicsChallenge)
                        .room(osintBasicsRoom)
                        .build(),
                Question.builder()
                        .questionText("What does it mean to check information from different places (data triangulation)?")
                        .description("Understanding data verification methods.")
                        .hint("Consider validating data through multiple sources.")
                        .flag("FLAG{DATA_TRIANGULATION}")
                        .options(Arrays.asList(
                                "It means guessing information.",
                                "It means checking the same info in different places to see if it’s true.",
                                "It means ignoring other sources.",
                                "It means using GPS coordinates."
                        ))
                        .answer("It means checking the same info in different places to see if it’s true.")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(osintBasicsChallenge)
                        .room(osintBasicsRoom)
                        .build()
        );

        // Calculate total points and add questions to the challenge
        double totalPoints = 0;
        for (Question question : osintBasicsQuestions) {
            totalPoints += question.getPoints();
            osintBasicsChallenge.addQuestion(question);
        }
        osintBasicsChallenge.setPoints(totalPoints);
        osintBasicsRoom.setPoints(totalPoints);

        // Save the Challenge (cascades to save Room and Questions)
        challengeRepository.save(osintBasicsChallenge);

        // Repeat the process for the Intermediate and Advanced levels
        createOSINTIntermediateChallenge();
        createOSINTAdvancedChallenge();
    }

    private void createOSINTIntermediateChallenge() {
        // Create a new Room for Social Media Mining Intermediate
        Room socialMediaMiningRoom = new Room();
        socialMediaMiningRoom.setName("Social Media Mining Room");
        socialMediaMiningRoom.setDescription("Welcome to the Social Media Mining room. Dive deeper into analyzing social media data.");
        socialMediaMiningRoom.setDifficulty(Difficulty.INTERMEDIATE);

        // Create a new Challenge and associate it with the Room
        Challenge socialMediaMiningChallenge = Challenge.builder()
                .name("Social Media Mining")
                .description("An intermediate set of questions on social media mining techniques.")
                .difficulty(Difficulty.INTERMEDIATE)
                .points(0) // We'll calculate total points from questions
                .questions(new ArrayList<>())
                .room(socialMediaMiningRoom) // Associate the Room
                .isChallengeOpen(false)
                .build();

        // Create the list of Questions for Social Media Mining Intermediate
        List<Question> socialMediaMiningQuestions = Arrays.asList(
                Question.builder()
                        .questionText("Use Maltego to gather information about a specific Twitter hashtag (#CyberSecurity) and visualize its impact across various social media platforms.")
                        .description("Practicing with visualization tools in OSINT.")
                        .hint("Consider how hashtags trend across platforms.")
                        .flag("FLAG{MALTEGO_HASHTAG_ANALYSIS}")
                        .options(Arrays.asList(
                                "Visualization tools are not helpful.",
                                "Hashtags cannot be analyzed.",
                                "Visualization tools like Maltego help analyze hashtag trends and social media interactions in OSINT investigations.",
                                "Maltego is used for encryption."
                        ))
                        .answer("Visualization tools like Maltego help analyze hashtag trends and social media interactions in OSINT investigations.")
                        .points(50.0)
                        .correctOption(2)
                        .challenge(socialMediaMiningChallenge)
                        .room(socialMediaMiningRoom)
                        .build(),
                Question.builder()
                        .questionText("Analyze the potential security implications of a leaked database containing customer information from a major retailer.")
                        .description("Understanding data breaches and their impacts.")
                        .hint("Consider the effects on customers and the company.")
                        .flag("FLAG{LEAKED_DATABASE_IMPACT}")
                        .options(Arrays.asList(
                                "There's no impact from a leaked database.",
                                "OSINT can't help in this situation.",
                                "OSINT can uncover the extent of data exposure, identify affected individuals, and assess the impact on the retailer's reputation and legal liabilities.",
                                "The data is useless to attackers."
                        ))
                        .answer("OSINT can uncover the extent of data exposure, identify affected individuals, and assess the impact on the retailer's reputation and legal liabilities.")
                        .points(50.0)
                        .correctOption(2)
                        .challenge(socialMediaMiningChallenge)
                        .room(socialMediaMiningRoom)
                        .build(),
                Question.builder()
                        .questionText("Discuss the role of geolocation data in OSINT investigations. How can it be used effectively?")
                        .description("Understanding the importance of geolocation data.")
                        .hint("Think about physical locations linked to online activities.")
                        .flag("FLAG{GEOLOCATION_IN_OSINT}")
                        .options(Arrays.asList(
                                "Geolocation data is irrelevant in OSINT.",
                                "Geolocation data provides insights into physical locations associated with online activities, aiding in identifying targets and assessing threats in OSINT investigations.",
                                "Using geolocation is illegal.",
                                "Geolocation only works with GPS devices."
                        ))
                        .answer("Geolocation data provides insights into physical locations associated with online activities, aiding in identifying targets and assessing threats in OSINT investigations.")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(socialMediaMiningChallenge)
                        .room(socialMediaMiningRoom)
                        .build(),
                Question.builder()
                        .questionText("Compare the ethical considerations in OSINT investigations involving public figures versus private individuals.")
                        .description("Exploring ethics in OSINT.")
                        .hint("Consider privacy rights and public interest.")
                        .flag("FLAG{ETHICAL_CONSIDERATIONS}")
                        .options(Arrays.asList(
                                "There are no ethical considerations.",
                                "OSINT involving public figures balances transparency with privacy rights, while investigations on private individuals require careful ethical considerations to avoid harm and intrusion.",
                                "It's the same for both.",
                                "Private individuals have no privacy rights."
                        ))
                        .answer("OSINT involving public figures balances transparency with privacy rights, while investigations on private individuals require careful ethical considerations to avoid harm and intrusion.")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(socialMediaMiningChallenge)
                        .room(socialMediaMiningRoom)
                        .build(),
                Question.builder()
                        .questionText("Using OSINT techniques, analyze the online presence of a competitor company to gain strategic insights.")
                        .description("Applying OSINT for competitive intelligence.")
                        .hint("Think about public information available about the company.")
                        .flag("FLAG{COMPETITOR_ANALYSIS}")
                        .options(Arrays.asList(
                                "OSINT cannot be used on competitors.",
                                "OSINT reveals competitor strategies, market positioning, customer feedback, and potential vulnerabilities through analysis of their online presence.",
                                "It's illegal to analyze competitors.",
                                "Competitors have no online presence."
                        ))
                        .answer("OSINT reveals competitor strategies, market positioning, customer feedback, and potential vulnerabilities through analysis of their online presence.")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(socialMediaMiningChallenge)
                        .room(socialMediaMiningRoom)
                        .build(),
                Question.builder()
                        .questionText("Describe how OSINT tools like Shodan can be used to identify vulnerable IoT devices connected to the internet.")
                        .description("Exploring OSINT tools for network scanning.")
                        .hint("Consider how devices can be discovered via open ports.")
                        .flag("FLAG{SHODAN_IOT_DEVICES}")
                        .options(Arrays.asList(
                                "Shodan is not related to OSINT.",
                                "Shodan scans for open ports and identifies IoT devices, revealing security weaknesses and potential entry points for cyberattacks in OSINT investigations.",
                                "IoT devices cannot be found online.",
                                "It's illegal to use Shodan."
                        ))
                        .answer("Shodan scans for open ports and identifies IoT devices, revealing security weaknesses and potential entry points for cyberattacks in OSINT investigations.")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(socialMediaMiningChallenge)
                        .room(socialMediaMiningRoom)
                        .build(),
                Question.builder()
                        .questionText("Discuss the challenges of analyzing encrypted communications in OSINT investigations.")
                        .description("Understanding limitations in OSINT.")
                        .hint("Consider the impact of encryption on data accessibility.")
                        .flag("FLAG{ENCRYPTION_CHALLENGES}")
                        .options(Arrays.asList(
                                "Encryption has no effect on OSINT.",
                                "Encryption complicates OSINT by securing communications, requiring decryption techniques or lawful access to investigate encrypted content effectively.",
                                "Encrypted data is easily accessible.",
                                "OSINT investigators can bypass encryption legally."
                        ))
                        .answer("Encryption complicates OSINT by securing communications, requiring decryption techniques or lawful access to investigate encrypted content effectively.")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(socialMediaMiningChallenge)
                        .room(socialMediaMiningRoom)
                        .build(),
                Question.builder()
                        .questionText("Evaluate the reliability of information gathered from social media for OSINT investigations during crisis situations.")
                        .description("Assessing information quality in OSINT.")
                        .hint("Consider the prevalence of misinformation during crises.")
                        .flag("FLAG{SOCIAL_MEDIA_RELIABILITY}")
                        .options(Arrays.asList(
                                "All social media information is reliable.",
                                "Social media provides real-time updates during crises, but information verification is crucial due to the prevalence of misinformation and rumors impacting OSINT reliability.",
                                "Social media should not be used during crises.",
                                "Information is always accurate during emergencies."
                        ))
                        .answer("Social media provides real-time updates during crises, but information verification is crucial due to the prevalence of misinformation and rumors impacting OSINT reliability.")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(socialMediaMiningChallenge)
                        .room(socialMediaMiningRoom)
                        .build(),
                Question.builder()
                        .questionText("Use OSINT techniques to identify potential security threats associated with a new cryptocurrency exchange platform.")
                        .description("Applying OSINT to emerging technologies.")
                        .hint("Consider user feedback and platform vulnerabilities.")
                        .flag("FLAG{CRYPTO_EXCHANGE_THREATS}")
                        .options(Arrays.asList(
                                "Cryptocurrency platforms are always secure.",
                                "OSINT reveals platform vulnerabilities, user feedback, regulatory compliance issues, and potential risks affecting the security of the cryptocurrency exchange.",
                                "OSINT cannot analyze cryptocurrencies.",
                                "Threats cannot be identified beforehand."
                        ))
                        .answer("OSINT reveals platform vulnerabilities, user feedback, regulatory compliance issues, and potential risks affecting the security of the cryptocurrency exchange.")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(socialMediaMiningChallenge)
                        .room(socialMediaMiningRoom)
                        .build(),
                Question.builder()
                        .questionText("Describe the role of open-source data in threat intelligence and its impact on cybersecurity operations.")
                        .description("Understanding OSINT's role in cybersecurity.")
                        .hint("Think about how open data contributes to threat detection.")
                        .flag("FLAG{OSINT_THREAT_INTELLIGENCE}")
                        .options(Arrays.asList(
                                "Open-source data is irrelevant to cybersecurity.",
                                "Open-source data provides real-time threat indicators, threat actor profiles, and attack trends, enhancing threat intelligence and proactive cybersecurity measures.",
                                "Threat intelligence does not use OSINT.",
                                "Only private data is useful for cybersecurity."
                        ))
                        .answer("Open-source data provides real-time threat indicators, threat actor profiles, and attack trends, enhancing threat intelligence and proactive cybersecurity measures.")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(socialMediaMiningChallenge)
                        .room(socialMediaMiningRoom)
                        .build()
        );

        // Calculate total points and add questions to the challenge
        double totalPoints = 0;
        for (Question question : socialMediaMiningQuestions) {
            totalPoints += question.getPoints();
            socialMediaMiningChallenge.addQuestion(question);
        }
        socialMediaMiningChallenge.setPoints(totalPoints);
        socialMediaMiningRoom.setPoints(totalPoints);

        // Save the Challenge (cascades to save Room and Questions)
        challengeRepository.save(socialMediaMiningChallenge);
    }

    private void createOSINTAdvancedChallenge() {
        // Create a new Room for Analyzing Data Breaches Advanced
        Room dataBreachesRoom = new Room();
        dataBreachesRoom.setName("Data Breaches Room");
        dataBreachesRoom.setDescription("Welcome to the Analyzing Data Breaches room. Master advanced OSINT techniques.");
        dataBreachesRoom.setDifficulty(Difficulty.ADVANCED);

        // Create a new Challenge and associate it with the Room
        Challenge dataBreachesChallenge = Challenge.builder()
                .name("Analyzing Data Breaches")
                .description("An advanced set of questions on analyzing data breaches using OSINT.")
                .difficulty(Difficulty.ADVANCED)
                .points(0) // We'll calculate total points from questions
                .questions(new ArrayList<>())
                .room(dataBreachesRoom) // Associate the Room
                .isChallengeOpen(false)
                .build();

        // Create the list of Questions for Analyzing Data Breaches Advanced
        List<Question> dataBreachesQuestions = Arrays.asList(
                Question.builder()
                        .questionText("Analyze the impact of social media manipulation on political elections using OSINT techniques.")
                        .description("Exploring the influence of social media on politics.")
                        .hint("Consider misinformation and coordinated campaigns.")
                        .flag("FLAG{SOCIAL_MEDIA_ELECTION_IMPACT}")
                        .options(Arrays.asList(
                                "OSINT cannot analyze social media.",
                                "Social media has no impact on elections.",
                                "OSINT exposes coordinated campaigns, fake accounts, misinformation tactics, and influence operations affecting electoral outcomes through social media manipulation.",
                                "Elections are only influenced by traditional media."
                        ))
                        .answer("OSINT exposes coordinated campaigns, fake accounts, misinformation tactics, and influence operations affecting electoral outcomes through social media manipulation.")
                        .points(50.0)
                        .correctOption(2)
                        .challenge(dataBreachesChallenge)
                        .room(dataBreachesRoom)
                        .build(),
                Question.builder()
                        .questionText("Discuss the legal challenges of using OSINT data in criminal investigations and court proceedings.")
                        .description("Understanding legal considerations in OSINT.")
                        .hint("Think about data privacy laws and evidence admissibility.")
                        .flag("FLAG{OSINT_LEGAL_CHALLENGES}")
                        .options(Arrays.asList(
                                "There are no legal challenges.",
                                "OSINT data is always admissible in court.",
                                "Legal considerations include data privacy laws, evidence admissibility, chain of custody, and ensuring OSINT data integrity in criminal investigations and court cases.",
                                "OSINT cannot be used in legal contexts."
                        ))
                        .answer("Legal considerations include data privacy laws, evidence admissibility, chain of custody, and ensuring OSINT data integrity in criminal investigations and court cases.")
                        .points(50.0)
                        .correctOption(2)
                        .challenge(dataBreachesChallenge)
                        .room(dataBreachesRoom)
                        .build(),
                Question.builder()
                        .questionText("Evaluate the effectiveness of OSINT in tracking down state-sponsored cyber espionage activities.")
                        .description("Analyzing OSINT's role in national security.")
                        .hint("Consider attribution and evidence gathering.")
                        .flag("FLAG{STATE_SPONSORED_ESPIONAGE}")
                        .options(Arrays.asList(
                                "OSINT is ineffective in cyber espionage.",
                                "OSINT provides evidence of state-sponsored cyber activities, including targeted attacks, espionage operations, and threat actor attribution, aiding in diplomatic responses and cybersecurity defenses.",
                                "Only classified intelligence can track espionage.",
                                "Cyber espionage cannot be detected."
                        ))
                        .answer("OSINT provides evidence of state-sponsored cyber activities, including targeted attacks, espionage operations, and threat actor attribution, aiding in diplomatic responses and cybersecurity defenses.")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(dataBreachesChallenge)
                        .room(dataBreachesRoom)
                        .build(),
                Question.builder()
                        .questionText("Describe how OSINT tools like Dark Web monitoring services can be used to detect illegal activities and underground markets.")
                        .description("Exploring advanced OSINT tools.")
                        .hint("Think about monitoring hidden services.")
                        .flag("FLAG{DARK_WEB_MONITORING}")
                        .options(Arrays.asList(
                                "Dark Web cannot be accessed.",
                                "Dark Web monitoring tracks illicit activities, such as illegal trade, cybercrime forums, and black market transactions, providing early detection and mitigation of criminal threats using OSINT.",
                                "It's illegal to monitor the Dark Web.",
                                "OSINT tools do not access the Dark Web."
                        ))
                        .answer("Dark Web monitoring tracks illicit activities, such as illegal trade, cybercrime forums, and black market transactions, providing early detection and mitigation of criminal threats using OSINT.")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(dataBreachesChallenge)
                        .room(dataBreachesRoom)
                        .build(),
                Question.builder()
                        .questionText("Analyze the ethical implications of using OSINT to monitor employees' social media activities in corporate cybersecurity programs.")
                        .description("Understanding privacy in corporate OSINT.")
                        .hint("Consider employee rights and corporate policies.")
                        .flag("FLAG{EMPLOYEE_MONITORING_ETHICS}")
                        .options(Arrays.asList(
                                "There are no ethical issues.",
                                "Ethical considerations in corporate OSINT programs include employee privacy rights, consent, transparency, and balancing security objectives with individual rights and organizational policies.",
                                "Employees have no privacy rights at work.",
                                "Monitoring employees is always illegal."
                        ))
                        .answer("Ethical considerations in corporate OSINT programs include employee privacy rights, consent, transparency, and balancing security objectives with individual rights and organizational policies.")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(dataBreachesChallenge)
                        .room(dataBreachesRoom)
                        .build(),
                Question.builder()
                        .questionText("Use OSINT techniques to investigate a cyberattack targeting a critical infrastructure provider.")
                        .description("Applying OSINT in critical infrastructure protection.")
                        .hint("Consider identifying threat actors and vulnerabilities.")
                        .flag("FLAG{CRITICAL_INFRASTRUCTURE_ATTACK}")
                        .options(Arrays.asList(
                                "OSINT cannot be used in such investigations.",
                                "OSINT identifies attack vectors, threat actors, motives, and impacts on critical services, supporting incident response, recovery efforts, and cybersecurity resilience in critical infrastructure protection.",
                                "Critical infrastructure is immune to attacks.",
                                "Only internal security can investigate such attacks."
                        ))
                        .answer("OSINT identifies attack vectors, threat actors, motives, and impacts on critical services, supporting incident response, recovery efforts, and cybersecurity resilience in critical infrastructure protection.")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(dataBreachesChallenge)
                        .room(dataBreachesRoom)
                        .build(),
                Question.builder()
                        .questionText("Discuss the role of OSINT in detecting and countering disinformation campaigns and fake news propagation.")
                        .description("Exploring OSINT in information warfare.")
                        .hint("Consider identifying sources and patterns of disinformation.")
                        .flag("FLAG{COUNTERING_DISINFORMATION}")
                        .options(Arrays.asList(
                                "Disinformation cannot be detected.",
                                "OSINT uncovers disinformation tactics, fake news sources, propaganda networks, and manipulation techniques, enabling media literacy, public awareness, and countermeasures against information warfare.",
                                "Disinformation is not a cybersecurity concern.",
                                "Only social media companies can counter fake news."
                        ))
                        .answer("OSINT uncovers disinformation tactics, fake news sources, propaganda networks, and manipulation techniques, enabling media literacy, public awareness, and countermeasures against information warfare.")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(dataBreachesChallenge)
                        .room(dataBreachesRoom)
                        .build(),
                Question.builder()
                        .questionText("Evaluate the challenges of OSINT data integration and analysis across multiple jurisdictions in international cybersecurity investigations.")
                        .description("Understanding cross-border OSINT challenges.")
                        .hint("Consider legal and cultural differences.")
                        .flag("FLAG{INTERNATIONAL_OSINT_CHALLENGES}")
                        .options(Arrays.asList(
                                "There are no challenges internationally.",
                                "OSINT faces challenges in legal frameworks, data sovereignty, cross-border cooperation, language barriers, and cultural differences impacting effective data integration and analysis in international cybersecurity investigations.",
                                "All countries have the same laws.",
                                "International investigations are not allowed."
                        ))
                        .answer("OSINT faces challenges in legal frameworks, data sovereignty, cross-border cooperation, language barriers, and cultural differences impacting effective data integration and analysis in international cybersecurity investigations.")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(dataBreachesChallenge)
                        .room(dataBreachesRoom)
                        .build(),
                Question.builder()
                        .questionText("Describe how OSINT can contribute to threat hunting and proactive defense strategies in cybersecurity operations.")
                        .description("Applying OSINT to proactive security.")
                        .hint("Think about early detection and threat intelligence.")
                        .flag("FLAG{OSINT_IN_THREAT_HUNTING}")
                        .options(Arrays.asList(
                                "OSINT is only reactive.",
                                "OSINT provides threat intelligence, early warning indicators, attack patterns, and adversary tactics, enhancing threat hunting capabilities, proactive defense measures, and cybersecurity posture.",
                                "OSINT does not aid in defense.",
                                "Threat hunting doesn't use OSINT."
                        ))
                        .answer("OSINT provides threat intelligence, early warning indicators, attack patterns, and adversary tactics, enhancing threat hunting capabilities, proactive defense measures, and cybersecurity posture.")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(dataBreachesChallenge)
                        .room(dataBreachesRoom)
                        .build(),
                Question.builder()
                        .questionText("Use advanced OSINT tools to analyze the digital footprint and online activities of a high-profile threat actor.")
                        .description("Advanced OSINT in threat analysis.")
                        .hint("Consider profiling and tracking techniques.")
                        .flag("FLAG{THREAT_ACTOR_ANALYSIS}")
                        .options(Arrays.asList(
                                "High-profile actors cannot be analyzed.",
                                "OSINT tools reveal threat actor profiles, affiliations, communication channels, tactics, techniques, and procedures (TTPs), aiding in threat attribution and cybersecurity response strategies.",
                                "It's illegal to investigate threat actors.",
                                "Digital footprints cannot be tracked."
                        ))
                        .answer("OSINT tools reveal threat actor profiles, affiliations, communication channels, tactics, techniques, and procedures (TTPs), aiding in threat attribution and cybersecurity response strategies.")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(dataBreachesChallenge)
                        .room(dataBreachesRoom)
                        .build()
        );

        // Calculate total points and add questions to the challenge
        double totalPoints = 0;
        for (Question question : dataBreachesQuestions) {
            totalPoints += question.getPoints();
            dataBreachesChallenge.addQuestion(question);
        }
        dataBreachesChallenge.setPoints(totalPoints);
        dataBreachesRoom.setPoints(totalPoints);

        // Save the Challenge (cascades to save Room and Questions)
        challengeRepository.save(dataBreachesChallenge);
    }

    private void createNetworkSecurityChallenge() {
        // Create a new Room for Network Security Fundamentals
        Room networkSecurityRoom = new Room();
        networkSecurityRoom.setName("Network Security Fundamentals");
        networkSecurityRoom.setDescription("Welcome to the Network Security Fundamentals room. Explore the basics of securing networks.");
        networkSecurityRoom.setDifficulty(Difficulty.BEGINNER);

        // Create a new Challenge and associate it with the Room
        Challenge networkSecurityChallenge = Challenge.builder()
                .name("Network Security Fundamentals")
                .description("A set of questions covering basic network security concepts.")
                .difficulty(Difficulty.BEGINNER)
                .points(0) // We'll calculate total points from questions
                .questions(new ArrayList<>())
                .room(networkSecurityRoom) // Associate the Room
                .isChallengeOpen(true)
                .build();

        // Create the list of Questions for Network Security Fundamentals
        List<Question> networkSecurityQuestions = Arrays.asList(
                Question.builder()
                        .questionText("What is the purpose of a firewall in network security?")
                        .description("Understanding the role of firewalls.")
                        .hint("Think about network traffic filtering.")
                        .flag("FLAG{FIREWALL_PROTECTION}")
                        .options(Arrays.asList(
                                "To provide wireless connectivity",
                                "To prevent unauthorized access by filtering incoming and outgoing network traffic",
                                "To increase network speed",
                                "To manage user passwords"
                        ))
                        .answer("To prevent unauthorized access by filtering incoming and outgoing network traffic")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(networkSecurityChallenge)
                        .room(networkSecurityRoom)
                        .build(),
                Question.builder()
                        .questionText("Which protocol is used to securely access a remote computer over an unsecured network?")
                        .description("Exploring secure remote access protocols.")
                        .hint("Consider Secure Shell.")
                        .flag("FLAG{USE_SSH}")
                        .options(Arrays.asList(
                                "FTP",
                                "Telnet",
                                "SSH",
                                "HTTP"
                        ))
                        .answer("SSH")
                        .points(50.0)
                        .correctOption(2)
                        .challenge(networkSecurityChallenge)
                        .room(networkSecurityRoom)
                        .build(),
                Question.builder()
                        .questionText("What does the term 'network segmentation' refer to?")
                        .description("Understanding network architecture.")
                        .hint("Think about dividing a network into smaller parts.")
                        .flag("FLAG{SEGMENT_NETWORK}")
                        .options(Arrays.asList(
                                "Combining multiple networks into one",
                                "Dividing a network into multiple subnetworks to enhance security and performance",
                                "Disconnecting all devices from the network",
                                "Upgrading network hardware"
                        ))
                        .answer("Dividing a network into multiple subnetworks to enhance security and performance")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(networkSecurityChallenge)
                        .room(networkSecurityRoom)
                        .build(),
                Question.builder()
                        .questionText("Which device inspects incoming and outgoing network traffic and decides whether to allow or block specific traffic based on security rules?")
                        .description("Identifying network security devices.")
                        .hint("Consider devices that enforce security policies.")
                        .flag("FLAG{FIREWALL_DEVICE}")
                        .options(Arrays.asList(
                                "Router",
                                "Switch",
                                "Firewall",
                                "Modem"
                        ))
                        .answer("Firewall")
                        .points(50.0)
                        .correctOption(2)
                        .challenge(networkSecurityChallenge)
                        .room(networkSecurityRoom)
                        .build(),
                Question.builder()
                        .questionText("What is the main purpose of intrusion detection systems (IDS)?")
                        .description("Learning about network monitoring tools.")
                        .hint("Think about detecting malicious activities.")
                        .flag("FLAG{IDS_PURPOSE}")
                        .options(Arrays.asList(
                                "To block unauthorized access",
                                "To detect and alert administrators about potential security breaches",
                                "To encrypt network traffic",
                                "To provide IP addresses to devices"
                        ))
                        .answer("To detect and alert administrators about potential security breaches")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(networkSecurityChallenge)
                        .room(networkSecurityRoom)
                        .build(),
                Question.builder()
                        .questionText("Which layer of the OSI model is responsible for end-to-end communication and error checking?")
                        .description("Understanding the OSI model.")
                        .hint("Consider the Transport Layer.")
                        .flag("FLAG{OSI_TRANSPORT_LAYER}")
                        .options(Arrays.asList(
                                "Physical Layer",
                                "Data Link Layer",
                                "Transport Layer",
                                "Application Layer"
                        ))
                        .answer("Transport Layer")
                        .points(50.0)
                        .correctOption(2)
                        .challenge(networkSecurityChallenge)
                        .room(networkSecurityRoom)
                        .build(),
                Question.builder()
                        .questionText("What is the term for a network that spans a relatively small area, such as a single building?")
                        .description("Learning about network types.")
                        .hint("Think about Local Area Networks.")
                        .flag("FLAG{DEFINE_LAN}")
                        .options(Arrays.asList(
                                "WAN",
                                "MAN",
                                "LAN",
                                "PAN"
                        ))
                        .answer("LAN")
                        .points(50.0)
                        .correctOption(2)
                        .challenge(networkSecurityChallenge)
                        .room(networkSecurityRoom)
                        .build(),
                Question.builder()
                        .questionText("Which protocol is used to securely transmit web pages over the internet?")
                        .description("Exploring secure web communication.")
                        .hint("Consider HTTPS.")
                        .flag("FLAG{USE_HTTPS}")
                        .options(Arrays.asList(
                                "HTTP",
                                "FTP",
                                "SMTP",
                                "HTTPS"
                        ))
                        .answer("HTTPS")
                        .points(50.0)
                        .correctOption(3)
                        .challenge(networkSecurityChallenge)
                        .room(networkSecurityRoom)
                        .build(),
                Question.builder()
                        .questionText("What does 'DoS' stand for in network security, and what does it involve?")
                        .description("Understanding network attacks.")
                        .hint("Think about Denial of Service.")
                        .flag("FLAG{DOS_ATTACK}")
                        .options(Arrays.asList(
                                "Denial of Security; it encrypts data",
                                "Data over Server; it backs up data",
                                "Denial of Service; it overwhelms a network or system to make it unavailable",
                                "Device on Standby; it puts devices to sleep"
                        ))
                        .answer("Denial of Service; it overwhelms a network or system to make it unavailable")
                        .points(50.0)
                        .correctOption(2)
                        .challenge(networkSecurityChallenge)
                        .room(networkSecurityRoom)
                        .build(),
                Question.builder()
                        .questionText("Which of the following is a best practice for securing a wireless network?")
                        .description("Learning about wireless security.")
                        .hint("Consider encryption methods.")
                        .flag("FLAG{SECURE_WIRELESS}")
                        .options(Arrays.asList(
                                "Using WEP encryption",
                                "Leaving the network open without a password",
                                "Using WPA2 or WPA3 encryption",
                                "Hiding the SSID"
                        ))
                        .answer("Using WPA2 or WPA3 encryption")
                        .points(50.0)
                        .correctOption(2)
                        .challenge(networkSecurityChallenge)
                        .room(networkSecurityRoom)
                        .build()
        );

        // Calculate total points and add questions to the challenge
        double totalPoints = 0;
        for (Question question : networkSecurityQuestions) {
            totalPoints += question.getPoints();
            networkSecurityChallenge.addQuestion(question);
        }
        networkSecurityChallenge.setPoints(totalPoints);
        networkSecurityRoom.setPoints(totalPoints);

        // Save the Challenge (cascades to save Room and Questions)
        challengeRepository.save(networkSecurityChallenge);
    }

    private void createCryptographyChallenge() {
        // Create a new Room for Cryptography Concepts
        Room cryptographyRoom = new Room();
        cryptographyRoom.setName("Cryptography Concepts");
        cryptographyRoom.setDescription("Welcome to the Cryptography Concepts room. Dive into the world of encryption and data protection.");
        cryptographyRoom.setDifficulty(Difficulty.INTERMEDIATE);

        // Create a new Challenge and associate it with the Room
        Challenge cryptographyChallenge = Challenge.builder()
                .name("Cryptography Concepts")
                .description("An intermediate set of questions on cryptographic principles and practices.")
                .difficulty(Difficulty.INTERMEDIATE)
                .points(0) // We'll calculate total points from questions
                .questions(new ArrayList<>())
                .room(cryptographyRoom) // Associate the Room
                .isChallengeOpen(false)
                .build();

        // Create the list of Questions for Cryptography Concepts
        List<Question> cryptographyQuestions = Arrays.asList(
                Question.builder()
                        .questionText("What is the primary difference between symmetric and asymmetric encryption?")
                        .description("Understanding encryption types.")
                        .hint("Consider key usage in encryption and decryption.")
                        .flag("FLAG{SYMMETRIC_VS_ASYMMETRIC}")
                        .options(Arrays.asList(
                                "Symmetric uses one key for encryption, asymmetric uses two separate keys",
                                "Symmetric is slower than asymmetric",
                                "Asymmetric encryption is less secure",
                                "There is no difference"
                        ))
                        .answer("Symmetric uses one key for encryption, asymmetric uses two separate keys")
                        .points(50.0)
                        .correctOption(0)
                        .challenge(cryptographyChallenge)
                        .room(cryptographyRoom)
                        .build(),
                Question.builder()
                        .questionText("Which algorithm is considered a symmetric key encryption algorithm?")
                        .description("Identifying encryption algorithms.")
                        .hint("Think about AES.")
                        .flag("FLAG{USE_AES}")
                        .options(Arrays.asList(
                                "RSA",
                                "DSA",
                                "AES",
                                "ECDSA"
                        ))
                        .answer("AES")
                        .points(50.0)
                        .correctOption(2)
                        .challenge(cryptographyChallenge)
                        .room(cryptographyRoom)
                        .build(),
                Question.builder()
                        .questionText("What is the main purpose of a digital signature?")
                        .description("Exploring digital authentication methods.")
                        .hint("Consider integrity and authentication.")
                        .flag("FLAG{DIGITAL_SIGNATURE_PURPOSE}")
                        .options(Arrays.asList(
                                "To encrypt data",
                                "To compress files",
                                "To verify the authenticity and integrity of a message",
                                "To generate random numbers"
                        ))
                        .answer("To verify the authenticity and integrity of a message")
                        .points(50.0)
                        .correctOption(2)
                        .challenge(cryptographyChallenge)
                        .room(cryptographyRoom)
                        .build(),
                Question.builder()
                        .questionText("What does SSL/TLS stand for, and what is its purpose?")
                        .description("Understanding secure communication protocols.")
                        .hint("Consider Secure Sockets Layer and Transport Layer Security.")
                        .flag("FLAG{SSL_TLS_PURPOSE}")
                        .options(Arrays.asList(
                                "Secure Sockets Layer / Transport Layer Security; protocols for encrypting internet traffic",
                                "Super Secure Layer / Trusted Link Security; protocols for data compression",
                                "Single Sign-On / Token Layer Security; methods for authentication",
                                "Server Side Logic / Transaction Logging System; database management tools"
                        ))
                        .answer("Secure Sockets Layer / Transport Layer Security; protocols for encrypting internet traffic")
                        .points(50.0)
                        .correctOption(0)
                        .challenge(cryptographyChallenge)
                        .room(cryptographyRoom)
                        .build(),
                Question.builder()
                        .questionText("What is a hash function, and what is its primary use in cryptography?")
                        .description("Learning about hash functions.")
                        .hint("Consider data integrity checks.")
                        .flag("FLAG{HASH_FUNCTION_USE}")
                        .options(Arrays.asList(
                                "A function that encrypts data for secure transmission",
                                "A function that maps data of arbitrary size to fixed-size values for integrity verification",
                                "A function that compresses files to save space",
                                "A function that generates encryption keys"
                        ))
                        .answer("A function that maps data of arbitrary size to fixed-size values for integrity verification")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(cryptographyChallenge)
                        .room(cryptographyRoom)
                        .build(),
                Question.builder()
                        .questionText("Which of the following is an example of a hash algorithm?")
                        .description("Identifying cryptographic hash algorithms.")
                        .hint("Think about SHA-256.")
                        .flag("FLAG{USE_SHA256}")
                        .options(Arrays.asList(
                                "RSA",
                                "AES",
                                "SHA-256",
                                "Diffie-Hellman"
                        ))
                        .answer("SHA-256")
                        .points(50.0)
                        .correctOption(2)
                        .challenge(cryptographyChallenge)
                        .room(cryptographyRoom)
                        .build(),
                Question.builder()
                        .questionText("What is the purpose of the Diffie-Hellman algorithm?")
                        .description("Understanding key exchange methods.")
                        .hint("Consider secure key exchange over public channels.")
                        .flag("FLAG{DIFFIE_HELLMAN_PURPOSE}")
                        .options(Arrays.asList(
                                "Encrypting data using symmetric keys",
                                "Exchanging cryptographic keys securely over a public channel",
                                "Hashing data for integrity",
                                "Creating digital certificates"
                        ))
                        .answer("Exchanging cryptographic keys securely over a public channel")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(cryptographyChallenge)
                        .room(cryptographyRoom)
                        .build(),
                Question.builder()
                        .questionText("What is 'public key infrastructure' (PKI) and its role in cryptography?")
                        .description("Exploring PKI concepts.")
                        .hint("Consider certificate authorities and key management.")
                        .flag("FLAG{UNDERSTAND_PKI}")
                        .options(Arrays.asList(
                                "A system for secure key exchange between users",
                                "A framework that manages public keys and digital certificates",
                                "A method for encrypting emails",
                                "A protocol for secure web browsing"
                        ))
                        .answer("A framework that manages public keys and digital certificates")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(cryptographyChallenge)
                        .room(cryptographyRoom)
                        .build(),
                Question.builder()
                        .questionText("Which cryptographic attack involves attempting all possible keys until the correct one is found?")
                        .description("Understanding cryptographic attacks.")
                        .hint("Consider brute-force methods.")
                        .flag("FLAG{BRUTE_FORCE_ATTACK}")
                        .options(Arrays.asList(
                                "Brute-force attack",
                                "Man-in-the-middle attack",
                                "Replay attack",
                                "Phishing attack"
                        ))
                        .answer("Brute-force attack")
                        .points(50.0)
                        .correctOption(0)
                        .challenge(cryptographyChallenge)
                        .room(cryptographyRoom)
                        .build(),
                Question.builder()
                        .questionText("What is 'steganography' in the context of data security?")
                        .description("Exploring data hiding techniques.")
                        .hint("Consider hiding information within other files.")
                        .flag("FLAG{DEFINE_STEGANOGRAPHY}")
                        .options(Arrays.asList(
                                "Encrypting messages with a cipher",
                                "Hiding messages within other non-secret text or data",
                                "Analyzing network traffic",
                                "Compressing files to reduce size"
                        ))
                        .answer("Hiding messages within other non-secret text or data")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(cryptographyChallenge)
                        .room(cryptographyRoom)
                        .build()
        );

        // Calculate total points and add questions to the challenge
        double totalPoints = 0;
        for (Question question : cryptographyQuestions) {
            totalPoints += question.getPoints();
            cryptographyChallenge.addQuestion(question);
        }
        cryptographyChallenge.setPoints(totalPoints);
        cryptographyRoom.setPoints(totalPoints);

        // Save the Challenge (cascades to save Room and Questions)
        challengeRepository.save(cryptographyChallenge);
    }

    private void createEthicalHackingChallenge() {
        // Create a new Room for Ethical Hacking Techniques
        Room ethicalHackingRoom = new Room();
        ethicalHackingRoom.setName("Ethical Hacking Techniques");
        ethicalHackingRoom.setDescription("Welcome to the Ethical Hacking Techniques room. Master advanced hacking methods responsibly.");
        ethicalHackingRoom.setDifficulty(Difficulty.ADVANCED);

        // Create a new Challenge and associate it with the Room
        Challenge ethicalHackingChallenge = Challenge.builder()
                .name("Ethical Hacking Techniques")
                .description("An advanced set of questions on ethical hacking practices and methodologies.")
                .difficulty(Difficulty.ADVANCED)
                .points(0) // We'll calculate total points from questions
                .questions(new ArrayList<>())
                .room(ethicalHackingRoom) // Associate the Room
                .isChallengeOpen(false)
                .build();

        // Create the list of Questions for Ethical Hacking Techniques
        List<Question> ethicalHackingQuestions = Arrays.asList(
                Question.builder()
                        .questionText("What is the primary objective of a penetration test in cybersecurity?")
                        .description("Understanding penetration testing purposes.")
                        .hint("Consider assessing security measures.")
                        .flag("FLAG{PEN_TEST_OBJECTIVE}")
                        .options(Arrays.asList(
                                "To install malware",
                                "To evaluate the security of a system by simulating attacks",
                                "To permanently disable systems",
                                "To train users on software applications"
                        ))
                        .answer("To evaluate the security of a system by simulating attacks")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(ethicalHackingChallenge)
                        .room(ethicalHackingRoom)
                        .build(),
                Question.builder()
                        .questionText("Which phase of ethical hacking involves gathering information without directly interacting with the target system?")
                        .description("Exploring hacking phases.")
                        .hint("Consider passive information gathering.")
                        .flag("FLAG{RECONNAISSANCE_PHASE}")
                        .options(Arrays.asList(
                                "Scanning",
                                "Gaining Access",
                                "Reconnaissance",
                                "Maintaining Access"
                        ))
                        .answer("Reconnaissance")
                        .points(50.0)
                        .correctOption(2)
                        .challenge(ethicalHackingChallenge)
                        .room(ethicalHackingRoom)
                        .build(),
                Question.builder()
                        .questionText("What is 'SQL Injection' and how does it impact web applications?")
                        .description("Understanding web application vulnerabilities.")
                        .hint("Consider manipulating database queries.")
                        .flag("FLAG{SQL_INJECTION_IMPACT}")
                        .options(Arrays.asList(
                                "Injecting SQL databases with malware",
                                "A code injection technique that might destroy a database",
                                "A method to speed up database queries",
                                "Encrypting SQL queries for security"
                        ))
                        .answer("A code injection technique that might destroy a database")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(ethicalHackingChallenge)
                        .room(ethicalHackingRoom)
                        .build(),
                Question.builder()
                        .questionText("Which tool is commonly used for network scanning and vulnerability detection in ethical hacking?")
                        .description("Learning about hacking tools.")
                        .hint("Consider Nmap.")
                        .flag("FLAG{USE_NMAP}")
                        .options(Arrays.asList(
                                "Photoshop",
                                "Nmap",
                                "Microsoft Word",
                                "Visual Studio"
                        ))
                        .answer("Nmap")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(ethicalHackingChallenge)
                        .room(ethicalHackingRoom)
                        .build(),
                Question.builder()
                        .questionText("What does 'privilege escalation' mean in the context of ethical hacking?")
                        .description("Understanding attack techniques.")
                        .hint("Consider gaining higher access levels.")
                        .flag("FLAG{PRIVILEGE_ESCALATION}")
                        .options(Arrays.asList(
                                "Reducing user permissions",
                                "Gaining higher levels of access than initially granted",
                                "Logging out of a system",
                                "Updating user profiles"
                        ))
                        .answer("Gaining higher levels of access than initially granted")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(ethicalHackingChallenge)
                        .room(ethicalHackingRoom)
                        .build(),
                Question.builder()
                        .questionText("Explain the difference between black-box, white-box, and gray-box penetration testing.")
                        .description("Exploring penetration testing methodologies.")
                        .hint("Consider the level of information provided to testers.")
                        .flag("FLAG{PEN_TEST_TYPES}")
                        .options(Arrays.asList(
                                "They are all the same type of testing",
                                "Black-box has full knowledge, white-box has none, gray-box has partial",
                                "Black-box has no knowledge, white-box has full knowledge, gray-box has partial knowledge",
                                "They refer to different operating systems"
                        ))
                        .answer("Black-box has no knowledge, white-box has full knowledge, gray-box has partial knowledge")
                        .points(50.0)
                        .correctOption(2)
                        .challenge(ethicalHackingChallenge)
                        .room(ethicalHackingRoom)
                        .build(),
                Question.builder()
                        .questionText("What is 'Metasploit' and how is it used in ethical hacking?")
                        .description("Understanding exploitation frameworks.")
                        .hint("Consider tools for developing and executing exploit code.")
                        .flag("FLAG{METASPLOIT_USAGE}")
                        .options(Arrays.asList(
                                "A web browser",
                                "A penetration testing framework used to develop and execute exploit code against a remote target machine",
                                "An antivirus software",
                                "A database management system"
                        ))
                        .answer("A penetration testing framework used to develop and execute exploit code against a remote target machine")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(ethicalHackingChallenge)
                        .room(ethicalHackingRoom)
                        .build(),
                Question.builder()
                        .questionText("Which attack involves intercepting and altering communication between two parties without their knowledge?")
                        .description("Learning about network attacks.")
                        .hint("Consider Man-in-the-Middle attacks.")
                        .flag("FLAG{MITM_ATTACK}")
                        .options(Arrays.asList(
                                "Denial of Service",
                                "Phishing",
                                "Man-in-the-Middle attack",
                                "Social Engineering"
                        ))
                        .answer("Man-in-the-Middle attack")
                        .points(50.0)
                        .correctOption(2)
                        .challenge(ethicalHackingChallenge)
                        .room(ethicalHackingRoom)
                        .build(),
                Question.builder()
                        .questionText("What legal considerations must an ethical hacker be aware of before conducting a penetration test?")
                        .description("Understanding legal and ethical responsibilities.")
                        .hint("Consider authorization and scope.")
                        .flag("FLAG{LEGAL_CONSIDERATIONS}")
                        .options(Arrays.asList(
                                "No legal considerations are necessary",
                                "They must obtain proper authorization and define the scope of testing to avoid legal repercussions",
                                "They can test any system they choose",
                                "Only verbal consent is needed"
                        ))
                        .answer("They must obtain proper authorization and define the scope of testing to avoid legal repercussions")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(ethicalHackingChallenge)
                        .room(ethicalHackingRoom)
                        .build(),
                Question.builder()
                        .questionText("Describe the purpose of 'post-exploitation' in the ethical hacking process.")
                        .description("Exploring hacking phases.")
                        .hint("Consider actions after gaining access.")
                        .flag("FLAG{POST_EXPLOITATION_PURPOSE}")
                        .options(Arrays.asList(
                                "Cleaning up evidence of the attack",
                                "Maintaining access, gathering additional data, and pivoting to other systems",
                                "Reporting findings to authorities",
                                "None of the above"
                        ))
                        .answer("Maintaining access, gathering additional data, and pivoting to other systems")
                        .points(50.0)
                        .correctOption(1)
                        .challenge(ethicalHackingChallenge)
                        .room(ethicalHackingRoom)
                        .build()
        );

        // Calculate total points and add questions to the challenge
        double totalPoints = 0;
        for (Question question : ethicalHackingQuestions) {
            totalPoints += question.getPoints();
            ethicalHackingChallenge.addQuestion(question);
        }
        ethicalHackingChallenge.setPoints(totalPoints);
        ethicalHackingRoom.setPoints(totalPoints);

        // Save the Challenge (cascades to save Room and Questions)
        challengeRepository.save(ethicalHackingChallenge);
    }

}


