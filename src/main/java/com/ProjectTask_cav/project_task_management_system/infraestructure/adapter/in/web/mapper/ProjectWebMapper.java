package com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.mapper;

import com.ProjectTask_cav.project_task_management_system.domain.model.Project;
import com.ProjectTask_cav.project_task_management_system.domain.model.Task;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.dto.request.ProjectRequest;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.dto.request.TaskRequest;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.dto.response.ProjectResponse;
import org.springframework.stereotype.Component;

@Component
public class ProjectWebMapper {

    public Project toDomain(ProjectRequest request) {
        Project project = new Project();
        project.setName(request.getName());
        return project;
    }

    public ProjectResponse toResponse(Project project) {
        ProjectResponse response = new ProjectResponse();
        response.setId(project.getId());
        response.setName(project.getName());
        response.setStatus(project.getStatus().name());
        return response;
    }

    public Task toDomain(TaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        return task;
    }
}