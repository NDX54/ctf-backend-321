package com.csit321.ctfbackend.user.dto.internal;

import jakarta.validation.constraints.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentUpdateDTO extends BaseUpdateUserDTO {

    @Min(1)
    @Max(12)
    private Integer yearLevel;

    @Builder(builderMethodName = "studentUpdateDTOBuilder")
    public StudentUpdateDTO(Long userId,
                            @NotBlank(message = "Username must not be blank")
                      String username,
                            @NotBlank(message = "Email must not be blank")
                      @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
                      String email,
                            @NotBlank(message = "Password must not be blank")
                      @Size(min = 8, message = "Passwords must have at least 8 characters")
                      String password,
                            Integer yearLevel) {
        super(userId, username, email, password);
        this.yearLevel = yearLevel;
    }

}
