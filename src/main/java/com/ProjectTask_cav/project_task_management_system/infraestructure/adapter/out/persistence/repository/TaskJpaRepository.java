package com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.persistence.repository;

import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.persistence.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskJpaRepository extends JpaRepository<TaskEntity, UUID> {
}