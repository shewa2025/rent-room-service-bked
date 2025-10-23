package com.rent.room.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rent.room.entity.Room;
import com.rent.room.repository.RoomRepository;
import com.rent.room.type.RentType;

@Service
public class RoomService {
    private final RoomRepository repo;

    public RoomService(RoomRepository repo) {
        this.repo = repo;
    }

    public Room postRoom(Room room) {
        room.setAvailable(true);
        return repo.save(room);
    }

    public List<Room> listAvailableRooms() {
        return repo.findByAvailableTrue();
    }

    public List<Room> listRoomsByRentType(RentType rentType) {
        return repo.findByRentTypeAndAvailableTrue(rentType);
    }
}