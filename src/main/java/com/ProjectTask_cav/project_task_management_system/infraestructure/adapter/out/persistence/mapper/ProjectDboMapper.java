package com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.persistence.mapper;

import com.ProjectTask_cav.project_task_management_system.domain.model.Project;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.persistence.entity.ProjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class ProjectDboMapper {

    private final TaskDboMapper taskMapper;

    // Usamos @Lazy para evitar problemas de dependencia circular si los mappers se llaman entre sí
    public ProjectDboMapper(@Lazy TaskDboMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    public ProjectEntity toEntity(Project project) {
        if (project == null) return null;
        return new ProjectEntity(
                project.getId(),
                project.getOwnerId(),
                project.getName(),
                project.getStatus(),
                project.isDeleted(),
                null // En la salida hacia BD podemos dejarlo null si las tareas se guardan aparte
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

        if (entity.getTasks() != null) {
            project.setTasks(entity.getTasks().stream()
                    .map(taskMapper::toDomain)
                    .collect(Collectors.toList()));
        }

        return project;
    }
}