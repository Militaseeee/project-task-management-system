package com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.controller;

import com.ProjectTask_cav.project_task_management_system.domain.port.in.project.CreateProjectUseCase;
import com.ProjectTask_cav.project_task_management_system.domain.port.in.project.ActivateProjectUseCase;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.repository.ProjectRepositoryPort;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.dto.request.ProjectRequest;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.dto.response.ProjectResponse;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.mapper.ProjectWebMapper;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.security.CurrentUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final CreateProjectUseCase createProjectUseCase;
    private final ActivateProjectUseCase activateProjectUseCase;
    private final ProjectRepositoryPort projectRepositoryPort; // Para el GET
    private final CurrentUserPort currentUserPort;
    private final ProjectWebMapper mapper;

    @PostMapping
    public ResponseEntity<ProjectResponse> create(@RequestBody ProjectRequest request) {
        var project = createProjectUseCase.create(mapper.toDomain(request));
        return ResponseEntity.ok(mapper.toResponse(project));
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> list() {
        UUID userId = currentUserPort.getCurrentUserId();
        var projects = projectRepositoryPort.findByOwnerId(userId);
        return ResponseEntity.ok(projects.stream().map(mapper::toResponse).collect(Collectors.toList()));
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activate(@PathVariable UUID id) {
        activateProjectUseCase.activate(id);
        return ResponseEntity.noContent().build();
    }
}