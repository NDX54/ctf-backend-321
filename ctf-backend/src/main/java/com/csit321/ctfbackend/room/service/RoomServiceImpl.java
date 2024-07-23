package com.csit321.ctfbackend.room.service;

import com.csit321.ctfbackend.room.dto.internal.RoomDTO;
import com.csit321.ctfbackend.room.model.Room;
import com.csit321.ctfbackend.room.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<RoomDTO> getAllRooms() {
        List<Room> roomsList = roomRepository.findAll();
        List<RoomDTO> roomDTOList = new ArrayList<>();
        for (Room room : roomsList) {
            roomDTOList.add(convertToRoomDTO(room));
        }

        return roomDTOList;
    }

    @Override
    public RoomDTO createRoom(RoomDTO roomDTO) {
        return null;
    }

    private RoomDTO convertToRoomDTO(Room room) {
        return new RoomDTO(
                room.getRoomId(),
                room.getName(),
                room.getDifficulty().getValue(),
                room.getDescription()
        );
    }
}
