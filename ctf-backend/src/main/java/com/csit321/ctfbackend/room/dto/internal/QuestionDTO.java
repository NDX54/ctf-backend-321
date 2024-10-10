package com.csit321.ctfbackend.room.dto.internal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionDTO {

    // ID of the question
    @Schema(hidden = true)
    private Long questionId;

    // Text of the question
    @Schema(description = "Text of the question", example = "What is the result of 2+2?")
    private String questionText;

    // Answer to the question
    @Schema(description = "Answer to the question", example = "4")
    private String answer;

    // Points awarded for the question
    @Schema(description = "Number of points you can get from a question", example = "50.0")
    private double points;

    // Index of the correct option in the list of options
    @Schema(description = "Index of the correct answer in the options array, ranging from 0-3.", example = "0")
    private int correctOption;

    // List of possible answers
    private List<String> options = new ArrayList<>();

    private String description;

    private String hint;

    private String flag;
}
