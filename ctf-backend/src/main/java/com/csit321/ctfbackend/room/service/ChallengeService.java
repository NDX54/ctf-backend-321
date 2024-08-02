package com.csit321.ctfbackend.room.service;

import com.csit321.ctfbackend.core.api.CustomNotFoundException;
import com.csit321.ctfbackend.room.dto.internal.ChallengeDTO;
import com.csit321.ctfbackend.room.dto.internal.QuestionDTO;
import com.csit321.ctfbackend.room.enums.Difficulty;
import com.csit321.ctfbackend.room.model.Challenge;
import com.csit321.ctfbackend.room.model.Question;
import com.csit321.ctfbackend.room.repository.ChallengeRepository;
import com.csit321.ctfbackend.room.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final QuestionRepository questionRepository;

    public List<ChallengeDTO> getAllChallenges() {
        List<Challenge> challenges = challengeRepository.findAll();
        List<ChallengeDTO> challengeDTOS = new ArrayList<>();
        for (Challenge challenge : challenges) {
            challengeDTOS.add(convertToChallengeDTO(challenge));
        }

        return challengeDTOS;
    }

    public ChallengeDTO getChallengeById(Long challengeId) {
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(() -> new CustomNotFoundException("Challenge not found"));

        return convertToChallengeDTO(challenge);
    }

    public ChallengeDTO createChallenge(ChallengeDTO challengeDTO) {
        Challenge challenge = convertToChallengeEntity(challengeDTO);
        return convertToChallengeDTO(challengeRepository.save(challenge));
    }

    private Challenge convertToChallengeEntity(ChallengeDTO challengeDTO) {
        Challenge challenge = new Challenge();
        challenge.setName(challengeDTO.getName());
        challenge.setDescription(challengeDTO.getDescription());
        challenge.setDifficulty(Difficulty.valueOfLabel(challengeDTO.getDifficulty()));
        if (challengeDTO.getQuestions() == null || challengeDTO.getQuestions().isEmpty()) {
            challenge.setQuestions(new ArrayList<>());
        }
        return challenge;
    }

    private ChallengeDTO convertToChallengeDTO(Challenge challenge) {
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
            questionDTOS.add(questionDTO);
        }


        return ChallengeDTO.builder()
                .challengeId(challenge.getChallengeId())
                .name(challenge.getName())
                .description(challenge.getDescription())
                .difficulty(challenge.getDifficulty().getValue())
                .questions(questionDTOS)
                .build();
    }

//    private List<Question> getQuestionEntityList(RoomDTO roomDTO) {
//        List<QuestionDTO> questionDTOList = roomDTO.getQuestionDTOList();
//        List<Question> questions = new ArrayList<>();
//        Room room = roomRepository.findById(roomDTO.getRoomId()).orElseThrow(() -> new CustomNotFoundException("Room not found."));
//        System.out.println(room);
//        if (questionDTOList == null || questionDTOList.isEmpty()) {
//            return new ArrayList<>();
//        } else {
//            for (QuestionDTO questionDTO : questionDTOList) {
//                Question question = new Question();
//                question.setQuestionId(questionDTO.getQuestionId());
//                question.setQuestionText(questionDTO.getQuestionText());
//                question.setAnswer(questionDTO.getAnswer());
//                questions.add(question);
//            }
//            return questions;
//        }
//    }

    private List<QuestionDTO> getQuestionDTOList(Challenge challenge) {
        List<Question> questions = challenge.getQuestions();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setQuestionId(question.getQuestionId());
            questionDTO.setQuestionText(question.getQuestionText());
            questionDTO.setAnswer(question.getAnswer());
            questionDTOList.add(questionDTO);
        }

        return questionDTOList;
    }
}
