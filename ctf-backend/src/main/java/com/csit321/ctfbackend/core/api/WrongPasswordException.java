package com.csit321.ctfbackend.core.api;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() { super("Wrong password"); }
}
