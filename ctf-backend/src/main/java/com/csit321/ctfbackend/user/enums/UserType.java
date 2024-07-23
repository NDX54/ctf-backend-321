package com.csit321.ctfbackend.user.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserType {
    BASE_USER("Base User"),
    TEACHER("Teacher"),
    STUDENT("Student");

    private final String value;
    UserType(String value) {
        this.value = value;
    }

    public static UserType valueOfLabel(String label) {
        for (UserType ut : UserType.values()) {
            if (ut.value.equals(label)) {
                return ut;
            }
        }
        return BASE_USER;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
