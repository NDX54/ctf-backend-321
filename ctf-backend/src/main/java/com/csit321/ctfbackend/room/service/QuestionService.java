package com.csit321.ctfbackend.room.service;

import com.csit321.ctfbackend.core.api.exceptions.CustomNotFoundException;
import com.csit321.ctfbackend.room.dto.internal.QuestionDTO;
import com.csit321.ctfbackend.room.model.Challenge;
import com.csit321.ctfbackend.room.model.Question;
import com.csit321.ctfbackend.room.model.QuestionData;
import com.csit321.ctfbackend.room.model.QuestionItem;
import com.csit321.ctfbackend.room.repository.ChallengeRepository;
import com.csit321.ctfbackend.room.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final ChallengeRepository challengeRepository;
    private final QuestionRepository questionRepository;

    // Method to get all questions
    public List<QuestionDTO> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question : questions) {
            var questionDTO = QuestionDTO.builder()
                    .questionId(question.getQuestionId())
                    .questionText(question.getQuestionText())
                    .answer(question.getAnswer())
                    .points(question.getPoints())
                    .options(question.getOptions())
                    .correctOption(question.getCorrectOption())
                    .description(question.getDescription())
                    .hint(question.getHint())
                    .flag(question.getFlag())
                    .build();
            questionDTOS.add(questionDTO);
        }
        return questionDTOS;
    }

    // Method to get questions for a specific challenge by its ID
    public List<QuestionDTO> getQuestionsForChallenge(Long challengeId) {
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(() -> new CustomNotFoundException("Challenge not found."));
        List<Question> questionEntityList = challenge.getQuestions();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionEntityList) {
            questionDTOList.add(convertToQuestionDTO(question));
        }
        return questionDTOList;
    }

    public List<QuestionDTO> getQuestionsForRoom(Long roomId) {
        List<Question> questionEntityList = questionRepository.findByRoom_RoomId(roomId);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionEntityList) {
            questionDTOList.add(convertToQuestionDTO(question));
        }
        return questionDTOList;
    }

    // Method to create a new question for a specific challenge
    public QuestionDTO createQuestion(QuestionDTO questionDTO, Long challengeId) {
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(() -> new CustomNotFoundException("Challenge not found."));
        var question = Question.builder()
                .questionText(questionDTO.getQuestionText())
                .answer(questionDTO.getAnswer())
                .challenge(challenge)
                .points(questionDTO.getPoints())
                .options(questionDTO.getOptions())
                .correctOption(questionDTO.getCorrectOption())
                .description(questionDTO.getDescription())
                .hint(questionDTO.getHint())
                .flag(questionDTO.getFlag())
                .build();
        return convertToQuestionDTO(questionRepository.save(question));
    }

    // Helper method to convert Question entity to QuestionDTO
    private QuestionDTO convertToQuestionDTO(Question question) {
        return QuestionDTO.builder()
                .questionId(question.getQuestionId())
                .questionText(question.getQuestionText())
                .answer(question.getAnswer())
                .points(question.getPoints())
                .options(question.getOptions())
                .correctOption(question.getCorrectOption())
                .description(question.getDescription())
                .hint(question.getHint())
                .flag(question.getFlag())
                .build();
    }

    public void importQuestions(QuestionData questionData, Challenge challenge) {
        double totalPoints = 0;

        for (QuestionItem item : questionData.getQuestions()) {
            Question question = Question.builder()
                    .questionText(item.getQuestion())
                    .options(item.getOptions())
                    .correctOption(item.getCorrectOption())
                    .answer(item.getOptions().get(item.getCorrectOption()))
                    .points(item.getPoints())
                    .challenge(challenge)
                    .build();
            questionRepository.save(question);

            challenge.addQuestion(question);

            totalPoints += question.getPoints();

        }

        challenge.setPoints(totalPoints);
    }
}
