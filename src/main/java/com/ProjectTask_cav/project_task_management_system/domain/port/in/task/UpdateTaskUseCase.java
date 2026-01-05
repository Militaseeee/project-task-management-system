package com.ProjectTask_cav.project_task_management_system.domain.port.in.task;
import java.util.UUID;

public interface UpdateTaskUseCase {
    void update(UUID id, String title);
}