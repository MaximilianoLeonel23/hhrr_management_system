package com.maximiliano.backend.dto.payroll;

import com.maximiliano.backend.dto.employee.EmployeeResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PayrollDetailsResponseDTO(
        Long id,
        EmployeeResponseDTO employee,
        LocalDate periodStart,
        LocalDate periodEnd,
        Double grossPay,
        Double deductions,
        Double netPay,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
