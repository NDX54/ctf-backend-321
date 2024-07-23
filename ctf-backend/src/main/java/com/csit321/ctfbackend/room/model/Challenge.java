package com.csit321.ctfbackend.room.model;

import com.csit321.ctfbackend.room.enums.Difficulty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Challenge {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long challengeId;

    @Column(nullable = false)
    private String name;

    @Lob
    @Column(nullable = false, columnDefinition = "CLOB")
    private String description;

    @OneToMany(mappedBy = "challenge", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms = new ArrayList<>();

    public void addRoom(Room room) {
        rooms.add(room);
        room.setChallenge(this);
    }

    public void removeRoom(Room room) {
        rooms.remove(room);
        room.setChallenge(null);
    }

}
