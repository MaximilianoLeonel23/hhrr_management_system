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

import java.util.Optional;

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
        Optional<User> userFound = authRepository.findByUsername(loginRequestDTO.username());

        if (userFound.isPresent()) {
            User user = userFound.get();
            if (encoder.matches(loginRequestDTO.password(), user.getPassword())) {
                String token = tokenService.generatedToken(user.getUsername());
                UserResponseDTO userDTO = new UserResponseDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getRole(),
                        user.getCreatedAt(),
                        user.getUpdatedAt()
                );
                return new LoginResponseDTO(token, userDTO);
            } else {
                throw new RuntimeException("Invalid credentials");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
