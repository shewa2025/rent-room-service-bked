package com.rent.room.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rent.room.entity.RoomImage;

public interface RoomImageRepository extends JpaRepository<RoomImage, Long> {}