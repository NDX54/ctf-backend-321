package com.csit321.ctfbackend.core.token;

import com.csit321.ctfbackend.user.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TokenType {
    BEARER("BEARER");

    private final String value;
    TokenType(String value) {
        this.value = value;
    }

    public static TokenType valueOfLabel(String label) {
        for (TokenType tokenType : TokenType.values()) {
            if (tokenType.value.equals(label)) {
                return tokenType;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
