package com.csit321.ctfbackend.user.model;

import com.csit321.ctfbackend.user.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    @JsonIgnore
    private Team team = null;

    @Builder(builderMethodName = "studentBuilderEntity")
    public Student(String username, String email, String password, Role role, Integer yearLevel, double score) {
        super(username, email, password, role);
        this.yearLevel = yearLevel;
        this.score = score;
    }
}
