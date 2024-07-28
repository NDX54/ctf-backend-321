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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final ChallengeRepository challengeRepository;
    private final QuestionRepository questionRepository;

    public List<RoomDTO> getAllRooms() {
        List<Room> roomsList = roomRepository.findAll();
        List<RoomDTO> roomDTOList = new ArrayList<>();
        for (Room room : roomsList) {
            roomDTOList.add(convertToRoomDTO(room));
        }

        return roomDTOList;
    }

    public RoomDTO getRoomById(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new CustomNotFoundException("Room not found."));

        return convertToRoomDTO(room);
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

        return RoomDTO.builder()
                .roomId(room.getRoomId())
                .name(room.getName())
                .difficulty(room.getDifficulty().getValue())
                .description(room.getDescription())
                .questionDTOList(questionDTOList)
                .build();
    }

    private Room convertToRoomEntity(RoomDTO roomDTO) {
        Room room = new Room();
        room.setName(roomDTO.getName());
        room.setDifficulty(Difficulty.valueOf(roomDTO.getDifficulty().toUpperCase()));
        room.setDescription(roomDTO.getDescription());

        if (roomDTO.getQuestionDTOList() == null || roomDTO.getQuestionDTOList().isEmpty()) {
            room.setQuestions(new ArrayList<>());
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
