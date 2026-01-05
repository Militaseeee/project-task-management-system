package com.ProjectTask_cav.project_task_management_system.domain.port.out.repository;

import com.ProjectTask_cav.project_task_management_system.domain.model.User;
import java.util.Optional;

public interface UserRepositoryPort {
    User save(User user);
    Optional<User> findByUsername(String username);
}