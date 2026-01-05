package com.ProjectTask_cav.project_task_management_system.application.usecase.project;

import com.ProjectTask_cav.project_task_management_system.domain.model.Project;
import com.ProjectTask_cav.project_task_management_system.domain.model.ProjectStatus;
import com.ProjectTask_cav.project_task_management_system.domain.port.in.CreateProjectUseCase;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.repository.ProjectRepositoryPort;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.security.CurrentUserPort;

public class CreateProjectUseCaseImpl implements CreateProjectUseCase {
    private final ProjectRepositoryPort projectRepositoryPort;
    private final CurrentUserPort currentUserPort; // Para saber quién está logueado

    public CreateProjectUseCaseImpl(ProjectRepositoryPort projectRepositoryPort, CurrentUserPort currentUserPort) {
        this.projectRepositoryPort = projectRepositoryPort;
        this.currentUserPort = currentUserPort;
    }

    @Override
    public Project create(Project project) {
        // Asignamos el ID del usuario que está creando el proyecto (Regla de seguridad)
        project.setOwnerId(currentUserPort.getCurrentUserId());

        // all proyecto nuevo empieza como BORRADOR (DRAFT)
        project.setStatus(ProjectStatus.DRAFT);

        // Marcamos que no está eliminado
        project.setDeleted(false);

        return projectRepositoryPort.save(project);
    }
}