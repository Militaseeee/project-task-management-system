package com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.controller;

import com.ProjectTask_cav.project_task_management_system.domain.port.in.task.CreateTaskUseCase;
import com.ProjectTask_cav.project_task_management_system.domain.port.in.task.CompleteTaskUseCase;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.dto.request.TaskRequest;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.mapper.ProjectWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final CompleteTaskUseCase completeTaskUseCase;
    private final ProjectWebMapper mapper;

    @PostMapping("/projects/{projectId}/tasks")
    public ResponseEntity<Void> createTask(@PathVariable UUID projectId, @RequestBody TaskRequest request) {
        createTaskUseCase.create(projectId, mapper.toDomain(request));
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/tasks/{id}/complete")
    public ResponseEntity<Void> completeTask(@PathVariable UUID id) {
        completeTaskUseCase.complete(id);
        return ResponseEntity.noContent().build();
    }
}