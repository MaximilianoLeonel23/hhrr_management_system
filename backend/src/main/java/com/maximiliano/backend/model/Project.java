package com.maximiliano.backend.model;

import com.maximiliano.backend.dto.project.ProjectRequestDTO;
import com.maximiliano.backend.dto.project.ProjectUpdateRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Project")
@Table(name = "projects")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @ManyToMany(mappedBy = "projects")
    private List<Employee> employees = new ArrayList<>();
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Project(ProjectRequestDTO project) {
        this.name = project.name();
        this.description = project.description().orElse(null);
        this.startDate = project.startDate();
        this.endDate = project.endDate().orElse(null);
    }


    public void update(ProjectUpdateRequestDTO project) {
        project.name().ifPresent(name -> this.name = name);
        project.description().ifPresent(description -> this.description = description);
        project.startDate().ifPresent(startDate -> this.startDate = startDate);
        project.endDate().ifPresent(endDate -> this.endDate = endDate);
        this.updatedAt = LocalDateTime.now();
    }
}
