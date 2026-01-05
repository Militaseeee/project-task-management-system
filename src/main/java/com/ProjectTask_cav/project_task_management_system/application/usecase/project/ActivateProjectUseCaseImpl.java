package com.ProjectTask_cav.project_task_management_system.application.usecase.project;

import com.ProjectTask_cav.project_task_management_system.domain.model.Project;
import com.ProjectTask_cav.project_task_management_system.domain.model.ProjectStatus;
import com.ProjectTask_cav.project_task_management_system.domain.port.in.ActivateProjectUseCase;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.audit.AuditLogPort;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.notification.NotificationPort;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.repository.ProjectRepositoryPort;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.security.CurrentUserPort;

import java.util.UUID;

public class ActivateProjectUseCaseImpl implements ActivateProjectUseCase {
    private final ProjectRepositoryPort projectRepositoryPort;
    private final AuditLogPort auditLogPort;
    private final NotificationPort notificationPort;
    private final CurrentUserPort currentUserPort;

    public ActivateProjectUseCaseImpl(ProjectRepositoryPort projectRepositoryPort, AuditLogPort auditLogPort, NotificationPort notificationPort, CurrentUserPort currentUserPort) {
        this.projectRepositoryPort = projectRepositoryPort;
        this.auditLogPort = auditLogPort;
        this.notificationPort = notificationPort;
        this.currentUserPort = currentUserPort;
    }

    @Override
    public void activate(UUID projectId) {
        Project project = projectRepositoryPort.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        // Esta regla es solo el propietario
        UUID currentUserId = currentUserPort.getCurrentUserId();
        if (!project.getOwnerId().equals(currentUserId)) {
            throw new RuntimeException("403 Forbidden: No eres el dueño");
        }

        // Esta es tener all menos una tarea activa (no eliminada)
        boolean hasActiveTasks = project.getTasks().stream()
                .anyMatch(task -> !task.isDeleted());

        if (!hasActiveTasks) {
            throw new RuntimeException("El proyecto debe tener al menos una tarea para activarse");
        }

        project.setStatus(ProjectStatus.ACTIVE);
        projectRepositoryPort.save(project);

        // Reglas sobre la Auditoria y Notificacion
        auditLogPort.register("PROJECT_ACTIVATED", project.getId());
        notificationPort.notify("El proyecto " + project.getName() + " ha sido activado.");
    }
}