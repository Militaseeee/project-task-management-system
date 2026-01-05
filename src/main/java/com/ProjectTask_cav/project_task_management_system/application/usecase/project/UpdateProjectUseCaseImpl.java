package com.ProjectTask_cav.project_task_management_system.application.usecase.project;

import com.ProjectTask_cav.project_task_management_system.domain.model.Project;
import com.ProjectTask_cav.project_task_management_system.domain.port.in.project.UpdateProjectUseCase;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.repository.ProjectRepositoryPort;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.security.CurrentUserPort;
import lombok.RequiredArgsConstructor;
import java.util.UUID;

@RequiredArgsConstructor
public class UpdateProjectUseCaseImpl implements UpdateProjectUseCase {
    private final ProjectRepositoryPort projectRepositoryPort;
    private final CurrentUserPort currentUserPort;

    @Override
    public void update(UUID id, String name) {
        Project project = projectRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        // Regla de solo el propietario
        if (!project.getOwnerId().equals(currentUserPort.getCurrentUserId())) {
            throw new RuntimeException("403 Forbidden: No eres el dueño");
        }

        project.setName(name);
        projectRepositoryPort.save(project);
    }
}