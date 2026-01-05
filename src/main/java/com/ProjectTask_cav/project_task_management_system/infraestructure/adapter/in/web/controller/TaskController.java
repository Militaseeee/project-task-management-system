package com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.controller;

import com.ProjectTask_cav.project_task_management_system.domain.port.in.task.*;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.dto.request.TaskRequest;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.dto.response.TaskResponse;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.mapper.TaskWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final CompleteTaskUseCase completeTaskUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;
    private final GetTasksByProjectUseCase getTasksByProjectUseCase;
    private final TaskWebMapper mapper;

    @PostMapping("/projects/{projectId}/tasks")
    public ResponseEntity<TaskResponse> createTask(@PathVariable UUID projectId, @RequestBody TaskRequest request) {
        var task = createTaskUseCase.create(projectId, mapper.toDomain(request));
        return ResponseEntity.ok(mapper.toResponse(task));
    }

    @PatchMapping("/tasks/{id}/complete")
    public ResponseEntity<Void> completeTask(@PathVariable UUID id) {
        completeTaskUseCase.complete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Void> updateTask(@PathVariable UUID id, @RequestBody TaskRequest request) {
        updateTaskUseCase.update(id, request.getTitle());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        deleteTaskUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/projects/{projectId}/tasks")
    public ResponseEntity<List<TaskResponse>> getTasks(@PathVariable UUID projectId) {
        var tasks = getTasksByProjectUseCase.getTasks(projectId);
        return ResponseEntity.ok(tasks.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList()));
    }
}