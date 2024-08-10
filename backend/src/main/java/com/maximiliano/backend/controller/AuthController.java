package com.maximiliano.backend.controller;

import com.maximiliano.backend.dto.auth.LoginRequestDTO;
import com.maximiliano.backend.dto.auth.LoginResponseDTO;
import com.maximiliano.backend.dto.user.UserRequestDTO;
import com.maximiliano.backend.dto.user.UserResponseDTO;
import com.maximiliano.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerNewUser(
            @RequestBody UserRequestDTO userRequestDTO
    ) {
        UserResponseDTO newUser = authService.registerNewUser(userRequestDTO);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO loginRequestDTO
    ) {
        LoginResponseDTO login = authService.login(loginRequestDTO);
        return ResponseEntity.ok(login);
    }
}
