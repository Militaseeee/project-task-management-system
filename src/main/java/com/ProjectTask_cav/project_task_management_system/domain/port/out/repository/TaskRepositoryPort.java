package com.ProjectTask_cav.project_task_management_system.domain.port.out.repository;

import com.ProjectTask_cav.project_task_management_system.domain.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepositoryPort {
    Task save(Task task);
    Optional<Task> findById(UUID id);

    List<Task> findByProjectId(UUID projectId);
}