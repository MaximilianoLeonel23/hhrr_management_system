package com.maximiliano.backend.dto.user;

import com.maximiliano.backend.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Optional;

public record UserRequestDTO(
        @NotBlank(message = "Username is required.")
        String username,
        @NotBlank(message = "Password is required.")
        String password,
        @NotBlank(message = "Email is required.")
        @Email(message = "Invalid email format.")
        String email,
        Optional<Role> role
) {
}
