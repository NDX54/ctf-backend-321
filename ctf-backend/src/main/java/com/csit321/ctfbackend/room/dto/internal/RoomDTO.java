package com.csit321.ctfbackend.room.dto.internal;

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
    private String description;

}
