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

    // Username of the user whose score is updated
    private String username;

    // New score of the user
    private double newScore;

}
