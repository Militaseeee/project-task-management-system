package com.ProjectTask_cav.project_task_management_system.domain.port.in.user;

import com.ProjectTask_cav.project_task_management_system.domain.model.User;

public interface RegisterUserUseCase {
    User register(User user);
}