package com.csit321.ctfbackend.room.service;

import com.csit321.ctfbackend.core.api.CustomNotFoundException;
import com.csit321.ctfbackend.room.dto.internal.QuestionDTO;
import com.csit321.ctfbackend.room.model.Question;
import com.csit321.ctfbackend.room.model.Room;
import com.csit321.ctfbackend.room.repository.QuestionRepository;
import com.csit321.ctfbackend.room.repository.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    private final RoomRepository roomRepository;
    private final QuestionRepository questionRepository;

    public QuestionService(RoomRepository roomRepository, QuestionRepository questionRepository) {
        this.roomRepository = roomRepository;
        this.questionRepository = questionRepository;
    }

    public QuestionDTO createQuestion(QuestionDTO questionDTO, Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new CustomNotFoundException("Room not found."));
        Question question = new Question();
        question.setQuestionText(questionDTO.getQuestionText());
        question.setAnswer(questionDTO.getAnswer());
        question.setRoom(room);
        return convertToQuestionDTO(questionRepository.save(question));
    }

    private QuestionDTO convertToQuestionDTO(Question question) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuestionId(question.getQuestionId());
        questionDTO.setQuestionText(question.getQuestionText());
        questionDTO.setAnswer(question.getAnswer());
        return questionDTO;
    }
}
