package com.rent.room.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rent.room.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}