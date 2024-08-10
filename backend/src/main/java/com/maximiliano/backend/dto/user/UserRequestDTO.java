package com.maximiliano.backend.dto.user;

import com.maximiliano.backend.model.Role;

import java.util.Optional;

public record UserRequestDTO(
        String username,
        String password,
        String email,
        Optional<Role> role
) {
}
