package com.csit321.ctfbackend.room.service;

import com.csit321.ctfbackend.core.api.exceptions.CustomNotFoundException;
import com.csit321.ctfbackend.room.dto.internal.QuestionDTO;
import com.csit321.ctfbackend.room.dto.internal.RoomDTO;
import com.csit321.ctfbackend.room.model.Question;
import com.csit321.ctfbackend.room.model.Room;
import com.csit321.ctfbackend.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public List<RoomDTO> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream()
                .map(this::convertToRoomDTO)
                .toList();
    }

    public RoomDTO getRoomById(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new CustomNotFoundException("Room not found"));
        return convertToRoomDTO(room);
    }

    private RoomDTO convertToRoomDTO(Room room) {
        return RoomDTO.builder()
                .roomId(room.getRoomId())
                .description(room.getDescription())
                .build();
    }

    private QuestionDTO convertToQuestionDTO(Question question) {
        return QuestionDTO.builder()
                .questionId(question.getQuestionId())
                .questionText(question.getQuestionText())
                .options(question.getOptions())
                .points(question.getPoints())
                .build();
    }

}
