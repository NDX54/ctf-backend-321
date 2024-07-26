package com.csit321.ctfbackend.user.model;

import com.csit321.ctfbackend.user.model.enums.UserType;
import com.csit321.ctfbackend.user.model.enums.Role;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student extends BaseUser {

    private Integer yearLevel;

    private double score = 0.0;

    @Builder(builderMethodName = "studentBuilderEntity")
    public Student(String username, String email, String password, UserType userType, Role role, Integer yearLevel, double score) {
        super(username, email, password, userType, role);
        this.yearLevel = yearLevel;
        this.score = score;
    }
}
