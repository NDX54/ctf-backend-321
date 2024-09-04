package com.csit321.ctfbackend.core.api.exceptions;

public class AlreadyMemberException extends RuntimeException {
    public AlreadyMemberException() {
        super("Already a member");
    }
}
