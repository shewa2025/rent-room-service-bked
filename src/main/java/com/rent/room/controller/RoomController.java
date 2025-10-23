package com.rent.room.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rent.room.entity.Room;
import com.rent.room.service.RoomService;
import com.rent.room.type.RentType;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService service;

    public RoomController(RoomService service) {
        this.service = service;
    }

    @PostMapping
    public Room postRoom(@RequestBody Room room) {
        return service.postRoom(room);
    }

    @GetMapping
    public List<Room> listRooms() {
        return service.listAvailableRooms();
    }

    @GetMapping("/type/{rentType}")
    public List<Room> listRoomsByRentType(@PathVariable RentType rentType) {
        return service.listRoomsByRentType(rentType);
    }
}