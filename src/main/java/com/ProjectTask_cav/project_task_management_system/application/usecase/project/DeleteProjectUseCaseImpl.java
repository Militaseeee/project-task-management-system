package com.ProjectTask_cav.project_task_management_system.application.usecase.project;

import com.ProjectTask_cav.project_task_management_system.domain.model.Project;
import com.ProjectTask_cav.project_task_management_system.domain.port.in.project.DeleteProjectUseCase;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.repository.ProjectRepositoryPort;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.security.CurrentUserPort;
import lombok.RequiredArgsConstructor;
import java.util.UUID;

@RequiredArgsConstructor
public class DeleteProjectUseCaseImpl implements DeleteProjectUseCase {
    private final ProjectRepositoryPort projectRepositoryPort;
    private final CurrentUserPort currentUserPort;

    @Override
    public void delete(UUID id) {
        Project project = projectRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        if (!project.getOwnerId().equals(currentUserPort.getCurrentUserId())) {
            throw new RuntimeException("403 Forbidden: No eres el dueño");
        }

        // Regla 4: Borrado lógico (Soft Delete)
        project.setDeleted(true);
        projectRepositoryPort.save(project);
    }
}