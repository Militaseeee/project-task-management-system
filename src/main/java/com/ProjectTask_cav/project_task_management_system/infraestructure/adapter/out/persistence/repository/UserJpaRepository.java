package com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.persistence.repository;

import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUsername(String username);
}