package com.maximiliano.backend.dto.document;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public record DocumentUploadResponseDTO(
        String message,
        String filePath,
        String title,
        Long employee,
        LocalDateTime uploadDate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
