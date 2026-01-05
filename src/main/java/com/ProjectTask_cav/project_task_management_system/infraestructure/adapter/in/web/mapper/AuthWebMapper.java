package com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.mapper;

import com.ProjectTask_cav.project_task_management_system.domain.model.User;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.dto.request.RegisterRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthWebMapper {

    public User toDomain(RegisterRequest request) {
        if (request == null) return null;
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return user;
    }
}