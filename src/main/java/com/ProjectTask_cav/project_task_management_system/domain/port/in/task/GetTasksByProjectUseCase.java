package com.ProjectTask_cav.project_task_management_system.domain.port.in.task;

import com.ProjectTask_cav.project_task_management_system.domain.model.Task;
import java.util.List;
import java.util.UUID;

public interface GetTasksByProjectUseCase {
    List<Task> getTasks(UUID projectId);
}