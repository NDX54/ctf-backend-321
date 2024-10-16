package com.csit321.ctfbackend.room.dto.internal;

import com.csit321.ctfbackend.room.enums.Difficulty;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomDTO {

    private Long roomId;
    private String name;
    private Difficulty difficulty;
    private String description;
    private double points;

}
