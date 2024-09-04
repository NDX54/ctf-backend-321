package com.csit321.ctfbackend.user.dto.external;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TeamDTO {

    private Long teamId;

    private String teamPassword;

    private double score;

    private int rank;

    private int numMembers;

    private List<MemberDTO> members;

}
