package com.maximiliano.backend.controller;

import com.maximiliano.backend.dto.project.ProjectDetailsResponseDTO;
import com.maximiliano.backend.dto.project.ProjectRequestDTO;
import com.maximiliano.backend.dto.project.ProjectResponseDTO;
import com.maximiliano.backend.dto.project.ProjectUpdateRequestDTO;
import com.maximiliano.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> getAllProjects() {
        List<ProjectResponseDTO> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDetailsResponseDTO> getProjectByID(@PathVariable Long id) {
        ProjectDetailsResponseDTO project = projectService.getProjectByID(id);
        return ResponseEntity.ok(project);
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> createNewProject(
            @RequestBody ProjectRequestDTO projectRequestDTO
    ) {
        ProjectResponseDTO project = projectService.createNewProject(projectRequestDTO);
        URI uri = UriComponentsBuilder.fromPath("/api/projects/{id}").buildAndExpand("1").toUri();
        return ResponseEntity.created(uri).body(project);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> updateProject(
            @PathVariable Long id,
            @RequestBody ProjectUpdateRequestDTO projectUpdateRequestDTO
    ) {
        ProjectResponseDTO project = projectService.updateProject(id, projectUpdateRequestDTO);
        return ResponseEntity.ok(project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(
            @PathVariable Long id
    ) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}

