package com.maximiliano.backend.dto.times;

import com.maximiliano.backend.dto.employee.EmployeeResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TimeRecordDetailsResponseDTO(
        Long id,
        EmployeeResponseDTO employee,
        LocalDate date,
        Double hoursWorked,
        LocalDateTime createdAt,
        LocalDateTime updateAt
) {
}
