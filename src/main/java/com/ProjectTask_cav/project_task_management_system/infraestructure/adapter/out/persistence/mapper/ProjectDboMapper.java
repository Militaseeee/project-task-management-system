package com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.persistence.mapper;

import com.ProjectTask_cav.project_task_management_system.domain.model.Project;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.persistence.entity.ProjectEntity;
import org.springframework.stereotype.Component;

@Component
public class ProjectDboMapper {

    public ProjectEntity toEntity(Project project) {
        if (project == null) return null;
        return new ProjectEntity(
                project.getId(),
                project.getOwnerId(),
                project.getName(),
                project.getStatus(),
                project.isDeleted(),
                null // Se pone null para que las tareas se mapeen aparte para evitar recursion
        );
    }

    public Project toDomain(ProjectEntity entity) {
        if (entity == null) return null;
        Project project = new Project();
        project.setId(entity.getId());
        project.setOwnerId(entity.getOwnerId());
        project.setName(entity.getName());
        project.setStatus(entity.getStatus());
        project.setDeleted(entity.isDeleted());
        return project;
    }

}
