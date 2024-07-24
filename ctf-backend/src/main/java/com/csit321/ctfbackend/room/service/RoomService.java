package com.csit321.ctfbackend.room.service;

import com.csit321.ctfbackend.core.api.CustomNotFoundException;
import com.csit321.ctfbackend.room.dto.internal.QuestionDTO;
import com.csit321.ctfbackend.room.dto.internal.RoomDTO;
import com.csit321.ctfbackend.room.enums.Difficulty;
import com.csit321.ctfbackend.room.model.Challenge;
import com.csit321.ctfbackend.room.model.Question;
import com.csit321.ctfbackend.room.model.Room;
import com.csit321.ctfbackend.room.repository.ChallengeRepository;
import com.csit321.ctfbackend.room.repository.QuestionRepository;
import com.csit321.ctfbackend.room.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final ChallengeRepository challengeRepository;
    private final QuestionRepository questionRepository;

    public RoomService(RoomRepository roomRepository, ChallengeRepository challengeRepository, QuestionRepository questionRepository) {
        this.roomRepository = roomRepository;
        this.challengeRepository = challengeRepository;
        this.questionRepository = questionRepository;
    }

    public List<RoomDTO> getAllRooms() {
        List<Room> roomsList = roomRepository.findAll();
        List<RoomDTO> roomDTOList = new ArrayList<>();
        for (Room room : roomsList) {
            roomDTOList.add(convertToRoomDTO(room));
        }

        return roomDTOList;
    }

    public RoomDTO createRoom(RoomDTO roomDTO, Long challengeId) {
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(() -> new CustomNotFoundException("Challenge not found."));
        Room room = convertToRoomEntity(roomDTO);
        room.setChallenge(challenge);
        return convertToRoomDTO(roomRepository.save(room));
    }

    private RoomDTO convertToRoomDTO(Room room) {
        List<Question> questionList = room.getQuestions();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            questionDTOList.add(convertToQuestionDTO(question));
        }
        return new RoomDTO(
                room.getRoomId(),
                room.getName(),
                room.getDifficulty().getValue(),
                room.getDescription(),
                questionDTOList
        );
    }

    private Room convertToRoomEntity(RoomDTO roomDTO) {
        Room room = new Room();
        room.setName(roomDTO.getName());
        room.setDifficulty(Difficulty.valueOf(roomDTO.getDifficulty().toUpperCase()));
        room.setDescription(roomDTO.getDescription());

        if (roomDTO.getQuestionDTOList() == null || roomDTO.getQuestionDTOList().isEmpty()) {
            room.setQuestions(new ArrayList<>());
        } else {
            for (QuestionDTO questionDTO : roomDTO.getQuestionDTOList()) {
                Question question = new Question();
                question.setQuestionText(questionDTO.getQuestionText());
                question.setAnswer(questionDTO.getAnswer());
                room.addQuestion(question);
            }
        }

        return room;
    }

    private QuestionDTO convertToQuestionDTO(Question question) {
        return new QuestionDTO(
                question.getQuestionId(),
                question.getQuestionText(),
                question.getAnswer()
        );
    }
}
