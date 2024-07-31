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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long challengeId;

    @Column(nullable = false)
    private String name;

    @Lob
    @Column(nullable = false, columnDefinition = "CLOB")
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    public void addQuestion(Question question) {
        this.questions.add(question);
        question.setChallenge(this);
    }

    public void removeQuestion(Question question) {
        this.questions.remove(question);
        question.setChallenge(null);
    }

}
