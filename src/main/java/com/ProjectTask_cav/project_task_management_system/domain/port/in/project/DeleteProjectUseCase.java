package com.ProjectTask_cav.project_task_management_system.domain.port.in.project;
import java.util.UUID;

public interface DeleteProjectUseCase {
    void delete(UUID id);
}