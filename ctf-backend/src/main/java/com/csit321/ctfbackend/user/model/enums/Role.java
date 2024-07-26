package com.csit321.ctfbackend.user.model.enums;

import com.csit321.ctfbackend.room.enums.Difficulty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {

    ADMIN("ADMIN"),
    MODERATOR("MODERATOR"),
    TEACHER("TEACHER"),
    STUDENT("STUDENT");

    private final String value;
    Role(String value) {
        this.value = value;
    }

    public static Role valueOfLabel(String label) {
        for (Role role : Role.values()) {
            if (role.value.equals(label)) {
                return role;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
