package com.csit321.ctfbackend.room.service;

import com.csit321.ctfbackend.room.dto.internal.ChallengeDTO;
import com.csit321.ctfbackend.room.model.Challenge;
import com.csit321.ctfbackend.room.repository.ChallengeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    private final ChallengeRepository challengeRepository;

    public ChallengeServiceImpl(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    @Override
    public List<ChallengeDTO> getAllChallenges() {
        List<Challenge> challenges = challengeRepository.findAll();
        List<ChallengeDTO> challengeDTOS = new ArrayList<>();
        for (Challenge challenge : challenges) {
            challengeDTOS.add(convertToChallengeDTO(challenge));
        }

        return challengeDTOS;
    }

    @Override
    public ChallengeDTO createChallenge(ChallengeDTO challengeDTO) {
        Challenge challenge = new Challenge();
        challenge.setName(challengeDTO.getName());
        challenge.setDescription(challengeDTO.getDescription());

        return convertToChallengeDTO(challengeRepository.save(challenge));
    }

    private ChallengeDTO convertToChallengeDTO(Challenge challenge) {
        return new ChallengeDTO(
                challenge.getChallengeId(),
                challenge.getName(),
                challenge.getDescription()
        );
    }
}
