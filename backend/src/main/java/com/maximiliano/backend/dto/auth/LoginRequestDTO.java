package com.maximiliano.backend.dto.auth;


public record LoginRequestDTO(
        String username,
        String password
) {
}
