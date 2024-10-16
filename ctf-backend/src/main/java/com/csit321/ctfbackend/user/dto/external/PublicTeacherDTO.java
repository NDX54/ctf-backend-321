package com.csit321.ctfbackend.user.dto.external;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Getter
@Setter
public class PublicTeacherDTO extends PublicBaseUserDTO {

    @NotBlank(message = "School must not be blank")
    private String school;

    @Builder(builderMethodName = "publicTeacherDTOBuilder")
    public PublicTeacherDTO(Long userId,
                            @NotBlank(message = "Username must not be blank")
                            String username,
                            @NotBlank(message = "Email must not be blank")
                            @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
                                    flags = Pattern.Flag.CASE_INSENSITIVE) String email,
                            String role,
                            String token,
                            String school) {
        super(userId, username, email, role, token);
        this.school = school;
    }
}
