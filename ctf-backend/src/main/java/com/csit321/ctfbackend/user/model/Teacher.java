package com.csit321.ctfbackend.user.model;

import com.csit321.ctfbackend.user.enums.UserType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@DiscriminatorValue("TEACHER")
@NoArgsConstructor
@Getter
@Setter
public class Teacher extends BaseUser {

    private String school;

    public Teacher(String username, String email, String password, UserType userType, String role, String school) {
        super(username, email, password, userType, role);
        this.school = school;
    }

}
