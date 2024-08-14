package com.maximiliano.backend.service;

import com.maximiliano.backend.dto.employee.EmployeeDetailsResponseDTO;
import com.maximiliano.backend.dto.employee.EmployeeResponseDTO;
import com.maximiliano.backend.dto.payroll.PayrollDetailsResponseDTO;
import com.maximiliano.backend.dto.payroll.PayrollRequestDTO;
import com.maximiliano.backend.dto.payroll.PayrollResponseDTO;
import com.maximiliano.backend.dto.project.ProjectResponseDTO;
import com.maximiliano.backend.exception.employee.DuplicateEmployeeEmailException;
import com.maximiliano.backend.exception.employee.EmployeeNotFoundException;
import com.maximiliano.backend.exception.employee.NoEmployeesFoundException;
import com.maximiliano.backend.exception.payroll.NoPayrollsFoundException;
import com.maximiliano.backend.exception.payroll.PayrollNotFoundException;
import com.maximiliano.backend.model.Employee;
import com.maximiliano.backend.model.Payroll;
import com.maximiliano.backend.repository.EmployeeRepository;
import com.maximiliano.backend.repository.PayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PayrollService {
    @Autowired
    private PayrollRepository payrollRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<PayrollResponseDTO> getAllPayrolls() {
        List<Payroll> payrolls = payrollRepository.findAll();
        if (!payrolls.isEmpty()) {
            return payrolls.stream().map(p -> new PayrollResponseDTO(
                    p.getId(),
                    p.getEmployee().getId(),
                    p.getPeriodStart(),
                    p.getPeriodEnd(),
                    p.getGrossPay(),
                    p.getDeductions(),
                    p.getNetPay(),
                    p.getCreatedAt(),
                    p.getUpdatedAt()
            )).toList();
        } else {
            throw new NoPayrollsFoundException("No payrolls found.");
        }
    }

    public PayrollDetailsResponseDTO getPayrollByID(Long id) {
        Optional<Payroll> payrollFound = payrollRepository.findById(id);
        if (payrollFound.isPresent()) {
            Payroll payroll = payrollFound.get();
            Employee employee = payroll.getEmployee();
            EmployeeResponseDTO employeeDTO = new EmployeeResponseDTO(
                    employee.getId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getEmail(),
                    employee.getDepartment(),
                    employee.getRole(),
                    employee.getDateOfHire(),
                    employee.getSalary(),
                    employee.getCreatedAt(),
                    employee.getUpdatedAt()
            );
            return new PayrollDetailsResponseDTO(
                    payroll.getId(),
                    employeeDTO,
                    payroll.getPeriodStart(),
                    payroll.getPeriodEnd(),
                    payroll.getGrossPay(),
                    payroll.getDeductions(),
                    payroll.getNetPay(),
                    payroll.getCreatedAt(),
                    payroll.getUpdatedAt()
            );
        } else {
            throw new PayrollNotFoundException("Payroll with ID " + id + " not found.");
        }
    }

    public PayrollResponseDTO createNewPayroll(PayrollRequestDTO payrollRequestDTO) {
        Optional<Employee> employee = employeeRepository.findById(payrollRequestDTO.employee());
        if (employee.isPresent()) {
            Payroll newPayroll = new Payroll(payrollRequestDTO, employee.get());
            Payroll payroll = payrollRepository.save(newPayroll);
            return new PayrollResponseDTO(
                    payroll.getId(),
                    payroll.getEmployee().getId(),
                    payroll.getPeriodStart(),
                    payroll.getPeriodEnd(),
                    payroll.getGrossPay(),
                    payroll.getDeductions(),
                    payroll.getNetPay(),
                    payroll.getCreatedAt(),
                    payroll.getUpdatedAt()
            );
        } else {
            throw new EmployeeNotFoundException("Payroll with employee ID " + payrollRequestDTO.employee() + " not found.");
        }
    }
}
