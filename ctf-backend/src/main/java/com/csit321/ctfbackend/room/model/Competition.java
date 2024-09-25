package com.csit321.ctfbackend.room.model;

import com.csit321.ctfbackend.core.utilities.RandomString;
import com.csit321.ctfbackend.user.model.Team;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long competitionId;

    private String competitionCode;

    private int maxTeams = 2;

    private int maxTeamSize = 2;

    @OneToMany(mappedBy = "competition")
    private List<Team> teamsList = new ArrayList<>();

    @PrePersist
    private void onCreate() {
        this.competitionCode = new RandomString(6).nextString();
    }

}