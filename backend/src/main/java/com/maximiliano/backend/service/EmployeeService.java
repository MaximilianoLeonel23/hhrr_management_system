package com.maximiliano.backend.service;

import com.maximiliano.backend.dto.employee.EmployeeDetailsResponseDTO;
import com.maximiliano.backend.dto.employee.EmployeeRequestDTO;
import com.maximiliano.backend.dto.employee.EmployeeResponseDTO;
import com.maximiliano.backend.dto.employee.EmployeeUpdateRequestDTO;
import com.maximiliano.backend.dto.project.ProjectResponseDTO;
import com.maximiliano.backend.exception.employee.DuplicateEmployeeEmailException;
import com.maximiliano.backend.exception.employee.EmployeeNotFoundException;
import com.maximiliano.backend.exception.employee.NoEmployeesFoundException;
import com.maximiliano.backend.model.Employee;
import com.maximiliano.backend.repository.EmployeeRepository;
import com.maximiliano.backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public List<EmployeeResponseDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        if (!employees.isEmpty()) {
            return employees.stream().map(e -> new EmployeeResponseDTO(
                    e.getId(),
                    e.getFirstName(),
                    e.getLastName(),
                    e.getEmail(),
                    e.getDepartment(),
                    e.getRole(),
                    e.getDateOfHire(),
                    e.getSalary(),
                    e.getCreatedAt(),
                    e.getUpdatedAt()
            )).toList();
        } else {
            throw new NoEmployeesFoundException("No employees found.");
        }
    }

    public EmployeeDetailsResponseDTO getEmployeeByID(Long id) {
        Optional<Employee> employeeFound = employeeRepository.findById(id);
        if (employeeFound.isPresent()) {
            Employee employee = employeeFound.get();
            List<ProjectResponseDTO> projects = employee.getProjects().stream().map(p -> new ProjectResponseDTO(
                    p.getId(),
                    p.getName(),
                    p.getDescription(),
                    p.getStartDate(),
                    p.getEndDate(),
                    p.getCreatedAt(),
                    p.getUpdatedAt()
            )).collect(Collectors.toList());

            return new EmployeeDetailsResponseDTO(
                    employee.getId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getEmail(),
                    employee.getDepartment(),
                    employee.getRole(),
                    employee.getDateOfHire(),
                    employee.getSalary(),
                    employee.getCreatedAt(),
                    employee.getUpdatedAt(),
                    projects
            );
        } else {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found.");
        }
    }

    public EmployeeResponseDTO createNewEmployee(EmployeeRequestDTO employeeRequestDTO) {
        Employee newEmployee = new Employee(employeeRequestDTO);

        if (employeeRepository.existsByEmail(newEmployee.getEmail())) {
            throw new DuplicateEmployeeEmailException("Email " + newEmployee.getEmail() + " is already in use.");
        }

        Employee employee = employeeRepository.save(newEmployee);
        return new EmployeeResponseDTO(
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
    }

    public EmployeeResponseDTO updateEmployee(Long id, EmployeeUpdateRequestDTO employeeUpdateRequestDTO) {
        Optional<Employee> employeeFound = employeeRepository.findById(id);
        if (employeeFound.isPresent()) {
            Employee employee = employeeFound.get();
            employee.update(employeeUpdateRequestDTO);
            employeeRepository.save(employee);
            return new EmployeeResponseDTO(
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
        } else {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found.");
        }
    }

    public void deleteEmployee(Long id) {
        Optional<Employee> employeeFound = employeeRepository.findById(id);
        if (employeeFound.isEmpty()) {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found.");
        }
        employeeRepository.deleteById(employeeFound.get().getId());
    }
}
