package com.maximiliano.backend.model;

import com.maximiliano.backend.dto.payroll.PayrollRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "Payroll")
@Table(name = "payrolls")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    @Column(name = "period_start")
    private LocalDate periodStart;
    @Column(name = "period_end")
    private LocalDate periodEnd;
    @Column(name = "gross_pay")
    private Double grossPay;
    private Double deductions;
    @Column(name = "net_pay")
    private Double netPay;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Payroll(PayrollRequestDTO payroll, Employee employee) {
        this.employee = employee;
        this.periodStart = payroll.periodStart();
        this.periodEnd = payroll.periodEnd().orElse(null);
        this.grossPay = payroll.grossPay();
        this.deductions = payroll.deductions();
        this.netPay = payroll.netPay();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
