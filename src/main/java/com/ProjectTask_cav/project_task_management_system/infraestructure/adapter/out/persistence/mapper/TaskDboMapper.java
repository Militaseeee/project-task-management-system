package com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.persistence.mapper;

import com.ProjectTask_cav.project_task_management_system.domain.model.Task;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.persistence.entity.TaskEntity;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.persistence.entity.ProjectEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskDboMapper {

    public TaskEntity toEntity(Task task) {
        if (task == null) return null;

        TaskEntity entity = new TaskEntity();
        entity.setId(task.getId());
        entity.setTitle(task.getTitle());
        entity.setCompleted(task.isCompleted());
        entity.setDeleted(task.isDeleted());

        // Seteamos solo el ID del proyecto
        if (task.getProjectId() != null) {
            ProjectEntity projectEntity = new ProjectEntity();
            projectEntity.setId(task.getProjectId());
            entity.setProject(projectEntity);
        }

        return entity;
    }

    public Task toDomain(TaskEntity entity) {
        if (entity == null) return null;

        Task task = new Task();
        task.setId(entity.getId());
        task.setTitle(entity.getTitle());
        task.setCompleted(entity.isCompleted());
        task.setDeleted(entity.isDeleted());

        if (entity.getProject() != null) {
            task.setProjectId(entity.getProject().getId());
        }

        return task;
    }
}