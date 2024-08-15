package com.csit321.ctfbackend.core.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Configure CORS mappings.
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    // Username for authentication.
    @Schema(example = "user1")
    private String username;

    // Password for authentication.
    @Schema(example = "abcd1234")
    private String password;
}
