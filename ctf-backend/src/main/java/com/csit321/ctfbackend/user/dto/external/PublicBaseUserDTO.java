package com.csit321.ctfbackend.user.dto.external;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder(builderMethodName = "publicBaseUserDTOBuilder")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PublicBaseUserDTO {

    @Schema(hidden = true)
    private Long userId;

    @Schema(description = "Username. Must be unique.", example = "user1")
    @NotBlank(message = "Username must not be blank")
    private String username;

    @Schema(description = """
                The User's email. This has a regex validation pattern.\s
                [a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}\s
                This field is also unique and must not reuse an already existing email.
               """, example = "user1@example.com")
    @NotBlank(message = "Email must not be blank")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    private String userType;

}
