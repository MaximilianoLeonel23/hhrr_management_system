package com.maximiliano.backend.service;

import com.maximiliano.backend.dto.payroll.PayrollDetailsResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayrollSecurityService {

    @Autowired
    private PayrollService payrollService;

    public boolean isPayrollForEmployee(Long payrollId, Long employeeId) {
        PayrollDetailsResponseDTO payroll = payrollService.getPayrollByID(payrollId);
        return payroll.employee().id().equals(employeeId);
    }
}
