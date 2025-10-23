package com.rent.room.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rent.room.dto.AuthRequest;
import com.rent.room.entity.User;
import com.rent.room.repository.UserRepository;
import com.rent.room.security.JwtService;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtService jwtService;

	public void register(AuthRequest authRequest) {
		String encodedPassword = passwordEncoder.encode(authRequest.getPassword());
		
		User user = new User();
		user.setUsername(authRequest.getUsername());
		user.setPassword(encodedPassword);
		user.setRole("ADMIN"); // Default role as USER
		userRepository.save(user);
	}

	public String login(AuthRequest authRequest) {
		User user = userRepository.findByUsername(authRequest.getUsername())
				.orElseThrow(() -> new RuntimeException("User not found"));

		if (passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
			return jwtService.generateToken(user.getUsername(), user.getRole());
		} else {
			throw new RuntimeException("Invalid credentials");
		}
	}
}