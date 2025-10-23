package com.rent.room.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rent.room.entity.Room;
import com.rent.room.type.RentType;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByAvailableTrue();
    List<Room> findByRentTypeAndAvailableTrue(RentType rentType);
}
