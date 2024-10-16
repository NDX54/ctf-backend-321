package com.csit321.ctfbackend.room.model;

import com.csit321.ctfbackend.room.enums.Difficulty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Challenge {

    // ID of the challenge
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long challengeId;

    // Name of the challenge
    @Column(nullable = false)
    private String name;

    // Description of the challenge
    @Lob
    @Column(nullable = false, columnDefinition = "CLOB")
    private String description;

    // Difficulty level of the challenge
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    // Point worth of the challenge
    @Column(nullable = false)
    private double points;

    private boolean isChallengeOpen = true;

    // List of questions associated with the challenge
    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id", referencedColumnName = "roomId")
    private Room room;

    // Method to add a question to the challenge
    public void addQuestion(Question question) {
        this.questions.add(question);
        question.setChallenge(this);
    }

    // Method to remove a question from the challenge
    public void removeQuestion(Question question) {
        this.questions.remove(question);
        question.setChallenge(null);
    }

}
