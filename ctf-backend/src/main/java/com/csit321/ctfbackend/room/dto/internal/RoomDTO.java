package com.csit321.ctfbackend.room.dto.internal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoomDTO {

    @Schema(hidden = true)
    private Long roomId;

    @Schema(description = "Name of the room", example = "Beginner Room")
    private String name;

    @Schema(description = "Difficulty level of the room", example = "Beginner")
    private String difficulty;

    @Schema(description = "Description of the room", example = "Basic encryption techniques for beginners")
    private String description;

    @Schema(description = "List of questions in the room")
    private List<QuestionDTO> questionDTOList;

}
