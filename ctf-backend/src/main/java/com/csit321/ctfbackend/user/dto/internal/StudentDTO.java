package com.csit321.ctfbackend.user.dto.internal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentDTO extends BaseUserDTO {

    @Min(1)
    @Max(12)
    private Integer yearLevel;

    @Schema(hidden = true)
    private double score = 0.0;

    @Builder(builderMethodName = "studentDTOBuilder")
    public StudentDTO(Long userId,
                      @NotBlank(message = "Username must not be blank")
                      String username,
                      @NotBlank(message = "Email must not be blank")
                      @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
                      String email,
                      @NotBlank(message = "Password must not be blank")
                      @Size(min = 8, message = "Passwords must have at least 8 characters")
                      String password,
                      String userType,
                      String role,
                      Integer yearLevel,
                      double score) {
        super(userId, username, email, password, userType, role);
        this.yearLevel = yearLevel;
        this.score = score;
    }
}
