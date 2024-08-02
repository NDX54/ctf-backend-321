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

    @Schema(hidden = true)
    private Long questionId;

    @Schema(description = "Text of the question", example = "What is the result of 2+2?")
    private String questionText;

    @Schema(description = "Answer to the question", example = "4")
    private String answer;

    @Schema(description = "Number of points you can get from a question", example = "50.0")
    private double points;

    @Schema(description = "Index of the correct answer in the options array, ranging from 0-3.", example = "0")
    private int correctOption;

    private List<String> options = new ArrayList<>();

}
