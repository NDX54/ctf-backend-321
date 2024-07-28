package com.csit321.ctfbackend.room.controller;

import com.csit321.ctfbackend.core.api.APIResponse;
import com.csit321.ctfbackend.core.api.CustomNotFoundException;
import com.csit321.ctfbackend.room.dto.internal.RoomDTO;
import com.csit321.ctfbackend.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/all")
    public List<RoomDTO> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<?> getRoomById(@PathVariable Long roomId) {

        return new ResponseEntity<>(roomService.getRoomById(roomId), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<?> createRoom(@RequestParam("challengeId") Long challengeId, @RequestBody RoomDTO roomDTO, WebRequest request) {
        RoomDTO newRoom = roomService.createRoom(roomDTO, challengeId);

        return APIResponse.build(newRoom, "Room added successfully", HttpStatus.CREATED, request);
    }
}
