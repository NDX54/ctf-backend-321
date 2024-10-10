package com.csit321.ctfbackend.room.controller;

import com.csit321.ctfbackend.room.dto.internal.QuestionDTO;
import com.csit321.ctfbackend.room.dto.internal.RoomDTO;
import com.csit321.ctfbackend.room.service.QuestionService;
import com.csit321.ctfbackend.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
public class RoomController {

    private final QuestionService questionService;
    private final RoomService roomService;

    @GetMapping("/all")
    public List<RoomDTO> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable Long roomId) {
        RoomDTO roomDTO = roomService.getRoomById(roomId);
        return ResponseEntity.ok(roomDTO);
    }

    @GetMapping("/{roomId}/questions")
    public List<QuestionDTO> getQuestionsForRoom(@PathVariable Long roomId) {
        return questionService.getQuestionsForRoom(roomId);
    }

}
