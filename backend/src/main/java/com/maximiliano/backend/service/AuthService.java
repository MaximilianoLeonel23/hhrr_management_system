package com.maximiliano.backend.service;

import com.maximiliano.backend.dto.auth.LoginRequestDTO;
import com.maximiliano.backend.dto.auth.LoginResponseDTO;
import com.maximiliano.backend.dto.user.UserRequestDTO;
import com.maximiliano.backend.dto.user.UserResponseDTO;
import com.maximiliano.backend.model.Role;
import com.maximiliano.backend.model.User;
import com.maximiliano.backend.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private TokenService tokenService;

    public UserResponseDTO registerNewUser(UserRequestDTO userRequestDTO) {
        String passwordEncoded = encoder.encode(userRequestDTO.password());
        Role role = userRequestDTO.role().orElse(Role.EMPLOYEE);
        User createdUser = new User(
                userRequestDTO.username(),
                passwordEncoded,
                userRequestDTO.email(),
                role
        );

        User user = authRepository.save(createdUser);
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        User userFound = authRepository.findByUsername(loginRequestDTO.username());

        if (userFound != null) {
            if (encoder.matches(loginRequestDTO.password(), userFound.getPassword())) {
                String token = tokenService.generatedToken(userFound.getUsername());
                UserResponseDTO user = new UserResponseDTO(
                        userFound.getId(),
                        userFound.getUsername(),
                        userFound.getEmail(),
                        userFound.getRole(),
                        userFound.getCreatedAt(),
                        userFound.getUpdatedAt()
                );
                return new LoginResponseDTO(token, user);
            } else {
                throw new RuntimeException("Invalid credentials");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
