package com.csit321.ctfbackend.core.api;

public class JwtException extends RuntimeException {
    public JwtException(String message) {
        super(message);
    }
}
