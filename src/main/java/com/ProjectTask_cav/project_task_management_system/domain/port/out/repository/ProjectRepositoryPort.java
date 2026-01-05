package com.ProjectTask_cav.project_task_management_system.domain.port.out.repository;

import com.ProjectTask_cav.project_task_management_system.domain.model.Project;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectRepositoryPort {

    Project save(Project project);
    Optional<Project> findById(UUID id);
    List<Project> findByOwnerId(UUID ownerId);
    List<Project> findAllActive();
}
