package com.csit321.ctfbackend.user.dto.external;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MemberDTO {

    private Long userId;

    private String username;

    private String email;

    private Long teamId;

}
