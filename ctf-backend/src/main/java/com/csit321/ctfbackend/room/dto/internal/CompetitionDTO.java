package com.csit321.ctfbackend.room.dto.internal;

import com.csit321.ctfbackend.user.dto.external.TeamDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompetitionDTO {

    private Long competitionId;

    private String competitionName;

    private String competitionCode;

    private int maxTeams;

    private int maxTeamSize;

    private List<TeamDTO> teamsList = new ArrayList<>();

}
