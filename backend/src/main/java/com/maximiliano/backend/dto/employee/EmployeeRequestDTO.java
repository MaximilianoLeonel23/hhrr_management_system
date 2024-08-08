package com.maximiliano.backend.dto.employee;

import java.time.LocalDate;
import java.util.Optional;

public record EmployeeRequestDTO(
        String firstname,
        String lastname,
        String email,
        Optional<String> department,
        Optional<String> role,
        Optional<LocalDate> dateOfHire,
        Optional<Double> salary
) {
}
