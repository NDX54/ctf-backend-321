package com.csit321.ctfbackend.room.dto.internal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

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

}
