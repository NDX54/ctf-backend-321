package com.csit321.ctfbackend.room.dto.internal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoomDTO {

    private Long roomId;
    private String name;
    private String difficulty;
    private String description;

}