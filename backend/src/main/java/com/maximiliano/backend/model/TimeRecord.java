package com.maximiliano.backend.model;

import com.maximiliano.backend.dto.times.TimeRecordRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "TimeRecord")
@Table(name = "time_records")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class TimeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    private LocalDate date;
    @Column(name = "hours_worked")
    private Double hoursWorked;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public TimeRecord(TimeRecordRequestDTO timeRecord, Employee employee) {
        this.employee = employee;
        this.date = timeRecord.date();
        this.hoursWorked = timeRecord.hoursWorked();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
