package com.maximiliano.backend.controller;

import com.maximiliano.backend.dto.payroll.PayrollDetailsResponseDTO;
import com.maximiliano.backend.dto.payroll.PayrollRequestDTO;
import com.maximiliano.backend.dto.payroll.PayrollResponseDTO;
import com.maximiliano.backend.service.PayrollSecurityService;
import com.maximiliano.backend.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/payrolls")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;

    @Autowired
    private PayrollSecurityService payrollSecurityService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    @GetMapping
    public ResponseEntity<List<PayrollResponseDTO>> getAllPayrolls() {
        List<PayrollResponseDTO> payrolls = payrollService.getAllPayrolls();
        return ResponseEntity.ok(payrolls);
    }

    @PreAuthorize("hasRole('ADMIN') or (hasRole('MANAGER') or (hasRole('EMPLOYEE') and @payrollSecurityService.isPayrollForEmployee(#id, principal.id)))")
    @GetMapping("/{id}")
    public ResponseEntity<PayrollDetailsResponseDTO> getPayrollByID(
            @PathVariable Long id
    ) {
        PayrollDetailsResponseDTO payroll = payrollService.getPayrollByID(id);
        return ResponseEntity.ok(payroll);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PayrollResponseDTO> createNewPayroll(
            @RequestBody PayrollRequestDTO payrollRequestDTO
    ) {
        PayrollResponseDTO payroll = payrollService.createNewPayroll(payrollRequestDTO);
        URI uri = UriComponentsBuilder.fromPath("/api/payrolls/{id}").buildAndExpand(payroll.id()).toUri();
        return ResponseEntity.created(uri).body(payroll);
    }

}
