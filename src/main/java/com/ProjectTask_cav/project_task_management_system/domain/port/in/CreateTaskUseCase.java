package com.ProjectTask_cav.project_task_management_system.domain.port.in;

import com.ProjectTask_cav.project_task_management_system.domain.model.Task;
import java.util.UUID;

public interface CreateTaskUseCase {
    Task create(UUID projectId, Task task);
}