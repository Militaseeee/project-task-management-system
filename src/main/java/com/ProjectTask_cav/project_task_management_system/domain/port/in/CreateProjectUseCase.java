package com.ProjectTask_cav.project_task_management_system.domain.port.in;

import com.ProjectTask_cav.project_task_management_system.domain.model.Project;

public interface CreateProjectUseCase {
    Project create(Project project);
}