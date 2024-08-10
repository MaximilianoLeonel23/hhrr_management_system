package com.maximiliano.backend.dto.payroll;

import java.time.LocalDate;
import java.util.Optional;

public record PayrollRequestDTO(
        Long employee,
        LocalDate periodStart,
        Optional<LocalDate> periodEnd,
        Double grossPay,
        Double deductions,
        Double netPay
) {
}
