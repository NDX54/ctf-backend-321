package com.csit321.ctfbackend.user.dto.external;

import jakarta.validation.constraints.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PublicStudentDTO extends PublicBaseUserDTO {

    @Min(value = 1, message = "Value should not fall below 1.")
    @Max(value = 12, message = "Value should not exceed 12.")
    private Integer yearLevel;

    private double score = 0.0;

    @Builder(builderMethodName = "publicStudentDTOBuilder")
    public PublicStudentDTO(Long userId,
                            @NotBlank(message = "Username must not be blank")
                            String username,
                            @NotBlank(message = "Email must not be blank")
                            @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
                                    flags = Pattern.Flag.CASE_INSENSITIVE)
                            String email,
                            String userType,
                            Integer yearLevel,
                            String role,
                            String token,
                            double score) {
        super(userId, username, email, userType, role, token);
        this.yearLevel = yearLevel;
        this.score = score;
    }
}
