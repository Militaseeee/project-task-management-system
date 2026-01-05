package com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.mapper;

import com.ProjectTask_cav.project_task_management_system.domain.model.Task;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.dto.request.TaskRequest;
import org.springframework.stereotype.Component;

@Component
public class TaskWebMapper {

    public Task toDomain(TaskRequest request) {
        if (request == null) return null;
        Task task = new Task();
        task.setTitle(request.getTitle());
        return task;
    }
}