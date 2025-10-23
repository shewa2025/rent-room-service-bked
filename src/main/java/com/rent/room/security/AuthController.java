package com.rent.room.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rent.room.dto.AuthRequest;
import com.rent.room.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
@Autowired
    private UserService userService;

private final AuthenticationManager authenticationManager;
private final JwtUtil jwtUtils;

public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtils) {
    this.authenticationManager = authenticationManager;
    this.jwtUtils = jwtUtils;
}

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest authRequest) {
        userService.register(authRequest);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        if (authentication.isAuthenticated()) {
        	return ResponseEntity.ok(new AuthResponse(jwtUtils.generateToken(loginRequest.getUsername())));
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}
