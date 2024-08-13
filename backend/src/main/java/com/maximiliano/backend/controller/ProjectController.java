package com.maximiliano.backend.controller;

import com.maximiliano.backend.dto.project.ProjectDetailsResponseDTO;
import com.maximiliano.backend.dto.project.ProjectRequestDTO;
import com.maximiliano.backend.dto.project.ProjectResponseDTO;
import com.maximiliano.backend.dto.project.ProjectUpdateRequestDTO;
import com.maximiliano.backend.service.ProjectSecurityService;
import com.maximiliano.backend.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectSecurityService projectSecurityService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> getAllProjects() {
        List<ProjectResponseDTO> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @PreAuthorize("hasRole('ADMIN') or (hasRole('MANAGER')) or (hasRole('EMPLOYEE') and @projectSecurityService.isEmployeeAssignedToProject(#id, principal.id))")
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDetailsResponseDTO> getProjectByID(@PathVariable Long id) {
        ProjectDetailsResponseDTO project = projectService.getProjectByID(id);
        return ResponseEntity.ok(project);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PostMapping
    public ResponseEntity<ProjectResponseDTO> createNewProject(
           @Valid @RequestBody ProjectRequestDTO projectRequestDTO
    ) {
        ProjectResponseDTO project = projectService.createNewProject(projectRequestDTO);
        URI uri = UriComponentsBuilder.fromPath("/api/projects/{id}").buildAndExpand(project.id()).toUri();
        return ResponseEntity.created(uri).body(project);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> updateProject(
            @PathVariable Long id,
            @Valid @RequestBody ProjectUpdateRequestDTO projectUpdateRequestDTO
    ) {
        ProjectResponseDTO project = projectService.updateProject(id, projectUpdateRequestDTO);
        return ResponseEntity.ok(project);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(
            @PathVariable Long id
    ) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}

