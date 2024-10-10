package com.csit321.ctfbackend.room.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Question {

    // ID of the question
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questionId;

    // Text of the question
    @Column(nullable = false)
    private String questionText;

    // Answer to the question
    @Column(nullable = false)
    private String answer;

    // List of possible answers
    @ElementCollection
    private List<String> options = new ArrayList<>();

    // Points awarded for the question
    private double points;

    // Index of the correct option in the list of options
    private int correctOption;

    @Lob
    private String description; // Previously q1Description or q2Description

    private String hint;        // Previously q1Hint or q2Hint

    private String flag;        // Previously q1Flag or q2Flag

    // Challenge to which the question belongs
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id")
    private Challenge challenge;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;


}
