package com.csit321.ctfbackend.room.service;

import com.csit321.ctfbackend.room.dto.internal.RoomDTO;

import java.util.List;

public interface RoomService {

    List<RoomDTO> getAllRooms();
    RoomDTO createRoom(RoomDTO roomDTO);

}
