package com.csit321.ctfbackend.room.service;

import com.csit321.ctfbackend.room.dto.internal.ChallengeDTO;
import com.csit321.ctfbackend.room.model.Challenge;

import java.util.List;

public interface ChallengeService {

    List<ChallengeDTO> getAllChallenges();
    ChallengeDTO createChallenge(ChallengeDTO challengeDTO);

}
