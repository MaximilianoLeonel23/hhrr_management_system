package com.maximiliano.backend.model;

import com.maximiliano.backend.dto.task.TaskRequestDTO;
import com.maximiliano.backend.dto.task.TaskUpdateRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "Task")
@Table(name = "tasks")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to", nullable = false)
    private Employee assignedTo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
    @Column(name = "due_date")
    private LocalDate dueDate;
    private Status status;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Task(TaskRequestDTO task, Employee employee, Project project) {
        this.title = task.title();
        this.description = task.description().orElse(null);
        this.assignedTo = employee;
        this.project = project;
        this.dueDate = task.dueDate().orElse(null);
        this.status = task.status().orElse(Status.PENDING);
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void update(TaskUpdateRequestDTO task, Employee employee, Project project) {
        task.title().ifPresent(title -> this.title = title);
        task.description().ifPresent(description -> this.description = description);
        task.assignedTo().ifPresent(assignedTo -> this.assignedTo = employee);
        task.project().ifPresent(p-> this.project = project);
        task.dueDate().ifPresent(dueDate -> this.dueDate = dueDate);
        task.status().ifPresent(status -> this.status = status);
        this.updatedAt = LocalDateTime.now();
    }
}
