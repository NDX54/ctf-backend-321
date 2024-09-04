package com.csit321.ctfbackend.core.api.exceptions;

public class MemberNotInTeamException extends RuntimeException {
    public MemberNotInTeamException() {
        super("Member not in team");
    }
}
