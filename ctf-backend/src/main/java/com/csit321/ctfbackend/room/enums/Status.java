package com.csit321.ctfbackend.room.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    STARTED("Started"),
    IN_PROGRESS("In progress"),
    FINISHED("Finished"),
    PAUSED("Paused"),
    CANCELLED("Cancelled");

    private final String value;
    Status(String value) {
        this.value = value;
    }

    // Method to get Status enum from string value
    public static Status valueOfLabel(String label) {
        for (Status status : Status.values()) {
            if (status.value.equals(label)) {
                return status;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
