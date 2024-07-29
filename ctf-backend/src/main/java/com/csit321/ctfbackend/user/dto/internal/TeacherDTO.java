package com.csit321.ctfbackend.user.dto.internal;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeacherDTO extends BaseUserDTO {

    @NotBlank(message = "School must not be blank")
    private String school;

    @Builder(builderMethodName = "teacherDTOBuilder")
    public TeacherDTO(Long userId,
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
                      String school) {
        super(userId, username, email, password, userType, role);
        this.school = school;
    }
}
