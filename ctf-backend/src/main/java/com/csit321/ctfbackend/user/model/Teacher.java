package com.csit321.ctfbackend.user.model;

import com.csit321.ctfbackend.user.enums.UserType;
import com.csit321.ctfbackend.user.model.enums.Role;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Teacher extends BaseUser {

    private String school;

    @Builder(builderMethodName = "teacherBuilder")
    public Teacher(String username, String email, String password, UserType userType, Role role, String school) {
        super(username, email, password, userType, role);
        this.school = school;
    }

}
