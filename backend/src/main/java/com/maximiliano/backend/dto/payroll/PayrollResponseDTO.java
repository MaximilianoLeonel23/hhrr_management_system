package com.maximiliano.backend.dto.payroll;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PayrollResponseDTO(
        Long id,
        Long employee,
        LocalDate periodStart,
        LocalDate periodEnd,
        Double grossPay,
        Double deductions,
        Double netPay,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
