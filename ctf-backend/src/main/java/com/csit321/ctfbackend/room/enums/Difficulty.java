package com.csit321.ctfbackend.room.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Difficulty {
    BEGINNER("Beginner"),
    INTERMEDIATE("Intermediate"),
    ADVANCED("Advanced");

    private final String value;
    Difficulty(String value) {
        this.value = value;
    }

    // Method to get Difficulty enum from string value
    public static Difficulty valueOfLabel(String label) {
        for (Difficulty difficulty : Difficulty.values()) {
            if (difficulty.value.equals(label)) {
                return difficulty;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

}
