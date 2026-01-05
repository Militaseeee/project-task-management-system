package com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.persistence.mapper;

import com.ProjectTask_cav.project_task_management_system.domain.model.User;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserDboMapper {
    public UserEntity toEntity(User user) {
        if (user == null) return null;
        return new UserEntity(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
    }

    public User toDomain(UserEntity entity) {
        if (entity == null) return null;
        return new User(entity.getId(), entity.getUsername(), entity.getEmail(), entity.getPassword());
    }
}