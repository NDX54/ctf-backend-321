package com.csit321.ctfbackend.user.model;

import com.csit321.ctfbackend.user.enums.UserType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@DiscriminatorValue("STUDENT")
@NoArgsConstructor
@Getter
@Setter
public class Student extends BaseUser {

    private Integer yearLevel;

    private double score = 0.0;

    public Student(String username, String email, String password, UserType userType, String role, Integer yearLevel, double score) {
        super(username, email, password, userType, role);
        this.yearLevel = yearLevel;
        this.score = score;
    }
}
