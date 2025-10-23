package com.rent.room.entity;

import java.util.List;

import com.rent.room.type.RentType;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Data
@Entity
public class Room {
    @Id @GeneratedValue private Long id;
    private String title;
    private String description;
    private String address;
    private double price;
    private boolean available;

    @ElementCollection
    private List<String> amenities;

    @Enumerated(EnumType.STRING)
    private RentType rentType;  // New field to specify rent type (Room, Basement, Whole House)

    @ManyToOne
    private User postedBy;
    
    
}