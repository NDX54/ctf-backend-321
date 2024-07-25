package com.csit321.ctfbackend.room.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
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
        this.rooms.add(room);
        room.setChallenge(this);
    }

    public void removeRoom(Room room) {
        this.rooms.remove(room);
        room.setChallenge(null);
    }

}
