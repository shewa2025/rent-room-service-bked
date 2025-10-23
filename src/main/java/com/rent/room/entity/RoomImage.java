package com.rent.room.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class RoomImage {
    @Id @GeneratedValue private Long id;
    private String imageUrl;

    @ManyToOne
    private Room room;
}
