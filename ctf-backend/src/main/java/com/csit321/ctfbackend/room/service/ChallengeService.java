package com.csit321.ctfbackend.room.service;

import com.csit321.ctfbackend.core.api.exceptions.CustomNotFoundException;
import com.csit321.ctfbackend.room.dto.internal.ChallengeDTO;
import com.csit321.ctfbackend.room.dto.internal.QuestionDTO;
import com.csit321.ctfbackend.room.enums.Difficulty;
import com.csit321.ctfbackend.room.model.Challenge;
import com.csit321.ctfbackend.room.model.Question;
import com.csit321.ctfbackend.room.model.QuestionData;
import com.csit321.ctfbackend.room.model.QuestionItem;
import com.csit321.ctfbackend.room.repository.ChallengeRepository;
import com.csit321.ctfbackend.room.repository.QuestionRepository;
import com.csit321.ctfbackend.user.dto.external.TeamDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final QuestionRepository questionRepository;

    // Method to get all challenges
    public List<ChallengeDTO> getAllChallenges() {
        List<Challenge> challenges = challengeRepository.findAll();
        List<ChallengeDTO> challengeDTOS = new ArrayList<>();
        for (Challenge challenge : challenges) {
            challengeDTOS.add(convertToChallengeDTO(challenge));
        }
        return challengeDTOS;
    }

    // Method to get a challenge by its ID
    public ChallengeDTO getChallengeById(Long challengeId) {
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(() -> new CustomNotFoundException("Challenge not found"));
        return convertToChallengeDTO(challenge);
    }

    // Method to create a new challenge
    public ChallengeDTO createChallenge(ChallengeDTO challengeDTO) {
        Challenge challenge = convertToChallengeEntity(challengeDTO);
        return convertToChallengeDTO(challengeRepository.save(challenge));
    }

    public Challenge createChallengeEntity(ChallengeDTO challengeDTO) {
        Challenge challenge = convertToChallengeEntity(challengeDTO);
        return challengeRepository.save(challenge);
    }

    public void updateChallenge(Challenge challenge) {
        challengeRepository.save(challenge);
    }

    // Helper method to convert ChallengeDTO to Challenge entity
    public Challenge convertToChallengeEntity(ChallengeDTO challengeDTO) {
        Challenge challenge = new Challenge();
        challenge.setName(challengeDTO.getName());
        challenge.setDescription(challengeDTO.getDescription());
        challenge.setDifficulty(Difficulty.valueOfLabel(challengeDTO.getDifficulty()));
        challenge.setPoints(challengeDTO.getPoints());
        if (challengeDTO.getQuestions() == null || challengeDTO.getQuestions().isEmpty()) {
            challenge.setQuestions(new ArrayList<>());
        }
        return challenge;
    }

    // Helper method to convert Challenge entity to ChallengeDTO
    public ChallengeDTO convertToChallengeDTO(Challenge challenge) {
        List<Question> questions = challenge.getQuestions();
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setQuestionId(question.getQuestionId());
            questionDTO.setQuestionText(question.getQuestionText());
            questionDTO.setAnswer(question.getAnswer());
            questionDTO.setPoints(question.getPoints());
            questionDTO.setOptions(question.getOptions());
            questionDTO.setCorrectOption(question.getCorrectOption());
            questionDTO.setDescription(question.getDescription());
            questionDTO.setHint(question.getHint());
            questionDTO.setFlag(question.getFlag());
            questionDTOS.add(questionDTO);
        }
        return ChallengeDTO.builder()
                .challengeId(challenge.getChallengeId())
                .name(challenge.getName())
                .description(challenge.getDescription())
                .difficulty(challenge.getDifficulty().getValue())
                .points(challenge.getPoints())
                .questions(questionDTOS)
                .build();
    }

    // Helper method to get a list of QuestionDTOs for a challenge
    private List<QuestionDTO> getQuestionDTOList(Challenge challenge) {
        List<Question> questions = challenge.getQuestions();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setQuestionId(question.getQuestionId());
            questionDTO.setQuestionText(question.getQuestionText());
            questionDTO.setAnswer(question.getAnswer());
            questionDTO.setPoints(question.getPoints());
            questionDTO.setOptions(question.getOptions());
            questionDTO.setCorrectOption(question.getCorrectOption());
            questionDTO.setDescription(question.getDescription());
            questionDTO.setHint(question.getHint());
            questionDTO.setFlag(question.getFlag());
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

}
