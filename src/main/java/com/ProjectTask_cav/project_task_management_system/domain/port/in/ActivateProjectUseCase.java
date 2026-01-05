package com.ProjectTask_cav.project_task_management_system.domain.port.in;

import java.util.UUID;

public interface ActivateProjectUseCase {
    void activate(UUID projectId);
}