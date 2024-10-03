package com.csit321.ctfbackend.room.dto.internal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChallengeDTO {

    // ID of the challenge, hidden from the API response.
    @Schema(hidden = true)
    private Long challengeId;

    // Name of the challenge.
    private String name;

    // Description of the challenge
    private String description;

    // Difficulty level of the challenge
    @Schema(example = "Beginner")
    private String difficulty;

    // Description of the challenge
    private Integer points;

    // List of questions in the challenge
    @Schema(description = "The list of questions in a challenge", hidden = true)
    private List<QuestionDTO> questions;
}
