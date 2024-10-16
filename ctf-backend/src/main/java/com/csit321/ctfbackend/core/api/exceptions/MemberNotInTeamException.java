package com.csit321.ctfbackend.core.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MemberNotInTeamException extends RuntimeException {
    public MemberNotInTeamException() {
        super("Member not in team");
    }
}
