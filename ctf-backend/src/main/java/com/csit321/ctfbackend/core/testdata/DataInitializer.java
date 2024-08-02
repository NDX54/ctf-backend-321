package com.csit321.ctfbackend.core.testdata;

import com.csit321.ctfbackend.room.model.Challenge;
import com.csit321.ctfbackend.room.model.Question;
import com.csit321.ctfbackend.room.enums.Difficulty;
import com.csit321.ctfbackend.room.repository.ChallengeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ChallengeRepository challengeRepository;

    @Override
    public void run(String... args) throws Exception {
        createTestChallenges();
    }

    private void createTestChallenges() {
        Challenge challenge1 = Challenge.builder()
                .name("Cybersecurity Challenge")
                .description("Test your knowledge on various cybersecurity concepts.")
                .difficulty(Difficulty.INTERMEDIATE)
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
                .build();

        Challenge challenge3 = Challenge.builder()
                .name("OSINT")
                .description("Test your knowledge on OSINT.")
                .difficulty(Difficulty.ADVANCED)
                .build();

        challenge1.setQuestions(questions1);
        challengeRepository.save(challenge1);
        challengeRepository.save(challenge2);
        challengeRepository.save(challenge3);
    }
}
