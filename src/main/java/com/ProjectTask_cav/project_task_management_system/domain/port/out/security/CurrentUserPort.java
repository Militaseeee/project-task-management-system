package com.ProjectTask_cav.project_task_management_system.domain.port.out.security;

import java.util.UUID;

public interface CurrentUserPort {
    UUID getCurrentUserId();
}