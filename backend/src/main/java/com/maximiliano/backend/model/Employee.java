package com.maximiliano.backend.model;

import com.maximiliano.backend.dto.employee.EmployeeRequestDTO;
import com.maximiliano.backend.dto.employee.EmployeeUpdateRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Employee")
@Table(name = "employees")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstname", nullable = false)
    private String firstName;
    @Column(name = "lastname", nullable = false)
    private String lastName;
    @Column(unique = true, nullable = false)
    @Email(message = "Invalid email format.")
    private String email;
    private String department;
    private String role;
    @Column(name = "date_of_hire")
    private LocalDate dateOfHire;
    private Double salary;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects = new ArrayList<>();

    public Employee(EmployeeRequestDTO employeeRequestDTO) {
        this.firstName = employeeRequestDTO.firstname();
        this.lastName = employeeRequestDTO.lastname();
        this.email = employeeRequestDTO.email();
        this.department = employeeRequestDTO.department().orElse(null);
        this.role = employeeRequestDTO.role().orElse(null);
        this.dateOfHire = employeeRequestDTO.dateOfHire().orElse(LocalDate.now());
        this.salary = employeeRequestDTO.salary().orElse(0.0);
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void update(EmployeeUpdateRequestDTO employee) {
        employee.firstname().ifPresent(firstname -> this.firstName = firstname);
        employee.lastname().ifPresent(lastname -> this.lastName = lastname);
        employee.email().ifPresent(email -> this.email = email);
        employee.department().ifPresent(department -> this.department = department);
        employee.role().ifPresent(role -> this.role = role);
        employee.dateOfHired().ifPresent(dateOfHire -> this.dateOfHire = dateOfHire);
        employee.salary().ifPresent(salary -> this.salary = salary);
        this.updatedAt = LocalDateTime.now();
    }
}
