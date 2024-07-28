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

    @Schema(hidden = true)
    private Long challengeId;
    private String name;
    private String description;

    @Schema(description = "The list of rooms in a challenge", hidden = true)
    private List<RoomDTO> roomDTOList;
}
