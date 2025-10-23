package com.rent.room.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtUtil jwtUtils;

	public AuthService(AuthenticationManager authenticationManager, UserDetailsService userDetailsService,
			JwtUtil jwtUtils) {
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.jwtUtils = jwtUtils;
	}

	public String authenticateAndGenerateToken(String username, String password) {
		// Authenticate the user
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		if (!authentication.isAuthenticated()) {
			throw new RuntimeException("Authentication failed");
		}

		// Load user details
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);

		// Generate JWT token
		String jwt = jwtUtils.generateToken(userDetails.getUsername());

		return jwt; // Return token to client
	}
}
