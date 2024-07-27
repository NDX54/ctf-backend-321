package com.csit321.ctfbackend.room.dto.internal;

import lombok.*;

import java.io.Serializable;

@Data
@Builder(builderMethodName = "scoreUpdateDTOBuilder")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScoreUpdateDTO implements Serializable {

    private String username;
    private double newScore;

}
