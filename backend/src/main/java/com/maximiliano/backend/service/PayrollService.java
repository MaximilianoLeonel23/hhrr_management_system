package com.maximiliano.backend.service;

import com.maximiliano.backend.dto.payroll.PayrollDetailsResponseDTO;
import com.maximiliano.backend.dto.payroll.PayrollRequestDTO;
import com.maximiliano.backend.dto.payroll.PayrollResponseDTO;
import com.maximiliano.backend.repository.PayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayrollService {
    @Autowired
    private PayrollRepository payrollRepository;

    public List<PayrollResponseDTO> getAllPayrolls() {
        return null;
    }

    public PayrollDetailsResponseDTO getPayrollByID(Long id) {
        return null;
    }

    public PayrollResponseDTO createNewPayroll(PayrollRequestDTO payrollRequestDTO) {
        return null;
    }
}
