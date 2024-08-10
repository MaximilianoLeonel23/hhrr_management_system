package com.maximiliano.backend.dto.auth;

import com.maximiliano.backend.dto.user.UserResponseDTO;

public record LoginResponseDTO(
        String token,
        UserResponseDTO user
) {
}
