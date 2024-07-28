package com.csit321.ctfbackend.core.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @Schema(example = "user1")
    private String username;

    @Schema(example = "abcd1234")
    private String password;
}
