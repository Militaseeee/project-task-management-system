package com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.persistence.repository;

import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.persistence.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectJpaRepository extends JpaRepository<ProjectEntity, UUID> {

    // Metodo con la regla de soft delete y busca por dueno
    List<ProjectEntity> findByOwnerIdAndDeletedFalse(UUID ownerId);

    Optional<ProjectEntity> findByIdAndDeletedFalse(UUID id);
}