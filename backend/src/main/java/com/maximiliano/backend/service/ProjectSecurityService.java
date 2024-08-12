package com.maximiliano.backend.service;

import com.maximiliano.backend.dto.project.ProjectDetailsResponseDTO;
import com.maximiliano.backend.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProjectSecurityService {

    @Autowired
    private ProjectService projectService;

    public boolean isEmployeeAssignedToProject(Long projectId, Long employeeId) {
        ProjectDetailsResponseDTO project = projectService.getProjectByID(projectId);
        return project.employees().stream().anyMatch(employee -> employee.id().equals(employeeId));
    }
}
