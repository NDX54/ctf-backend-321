package com.csit321.ctfbackend.user.model;

import com.csit321.ctfbackend.core.utilities.RandomString;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long teamId;

    @Column(unique = true, nullable = false)
    private String teamPassword;

    private String teamName;

    private double score = 0.0;

    private int rank = 0;

    private int numMembers = 0;

    @OneToMany(mappedBy = "team")
    private List<Student> members = new ArrayList<>();

    @PrePersist
    private void onCreate() {
        this.teamPassword = new RandomString(6).nextString();
    }

    @PostPersist
    private void onPersist() {
        System.out.println("Team " + this.teamId + " created with password: " + this.teamPassword);
        setTeamName("Team " + this.teamId);
    }

    public void addMember(Student student) {
        members.add(student);
        student.setTeam(this);
        numMembers = members.size();
    }

    public void removeMember(Student student) {
        members.remove(student);
        student.setTeam(null);
        numMembers = members.size();
    }

    public boolean hasMember(Student student) {
        return members.contains(student);
    }

    public boolean isFull(Long maxMembers) {
        return numMembers >= maxMembers;
    }

}
