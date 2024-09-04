package com.csit321.ctfbackend.core.api.exceptions;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() { super("Wrong password"); }
}
