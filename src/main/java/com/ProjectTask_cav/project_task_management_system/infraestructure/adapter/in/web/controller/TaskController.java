package com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.controller;

import com.ProjectTask_cav.project_task_management_system.domain.port.in.task.CreateTaskUseCase;
import com.ProjectTask_cav.project_task_management_system.domain.port.in.task.CompleteTaskUseCase;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.dto.request.TaskRequest;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.dto.response.TaskResponse;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.mapper.TaskWebMapper;
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
    private final TaskWebMapper mapper;

    @PostMapping("/projects/{projectId}/tasks")
    public ResponseEntity<TaskResponse> createTask(@PathVariable UUID projectId, @RequestBody TaskRequest request) {
        // Guardamos el resultado del caso de uso
        var task = createTaskUseCase.create(projectId, mapper.toDomain(request));
        // Devolvemos el TaskResponse con el ID generado
        return ResponseEntity.ok(mapper.toResponse(task));
    }

    @PatchMapping("/tasks/{id}/complete")
    public ResponseEntity<Void> completeTask(@PathVariable UUID id) {
        completeTaskUseCase.complete(id);
        return ResponseEntity.noContent().build();
    }
}