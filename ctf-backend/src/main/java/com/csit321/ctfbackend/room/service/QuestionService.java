package com.csit321.ctfbackend.room.service;

import com.csit321.ctfbackend.core.api.CustomNotFoundException;
import com.csit321.ctfbackend.room.dto.internal.QuestionDTO;
import com.csit321.ctfbackend.room.model.Question;
import com.csit321.ctfbackend.room.model.Room;
import com.csit321.ctfbackend.room.repository.QuestionRepository;
import com.csit321.ctfbackend.room.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private final RoomRepository roomRepository;
    private final QuestionRepository questionRepository;

    public QuestionService(RoomRepository roomRepository, QuestionRepository questionRepository) {
        this.roomRepository = roomRepository;
        this.questionRepository = questionRepository;
    }

    public List<QuestionDTO> getQuestionsForRoom(Long roomId) {

        Room room = roomRepository.findById(roomId).orElseThrow(() -> new CustomNotFoundException("Room not found."));

        List<Question> questionEntityList = room.getQuestions();
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questionEntityList) {

            questionDTOList.add(convertToQuestionDTO(question));
        }

        return questionDTOList;
    }

    public QuestionDTO createQuestion(QuestionDTO questionDTO, Long roomId) {

        Room room = roomRepository.findById(roomId).orElseThrow(() -> new CustomNotFoundException("Room not found."));

        var question = Question.builder()
                .questionText(questionDTO.getQuestionText())
                .answer(questionDTO.getAnswer())
                .room(room)
                .build();

        return convertToQuestionDTO(questionRepository.save(question));
    }

    private QuestionDTO convertToQuestionDTO(Question question) {

        return QuestionDTO.builder()
                .questionId(question.getQuestionId())
                .questionText(question.getQuestionText())
                .answer(question.getAnswer())
                .build();
    }
}
