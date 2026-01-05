package com.ProjectTask_cav.project_task_management_system.application.usecase.task;

import com.ProjectTask_cav.project_task_management_system.domain.model.Task;
import com.ProjectTask_cav.project_task_management_system.domain.port.in.CompleteTaskUseCase;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.audit.AuditLogPort;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.notification.NotificationPort;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.repository.TaskRepositoryPort;

import java.util.UUID;

public class CompleteTaskUseCaseImpl implements CompleteTaskUseCase {
    private final TaskRepositoryPort taskRepositoryPort;
    private final AuditLogPort auditLogPort;
    private final NotificationPort notificationPort;

    public CompleteTaskUseCaseImpl(TaskRepositoryPort taskRepositoryPort, AuditLogPort auditLogPort, NotificationPort notificationPort) {
        this.taskRepositoryPort = taskRepositoryPort;
        this.auditLogPort = auditLogPort;
        this.notificationPort = notificationPort;
    }

    @Override
    public void complete(UUID taskId) {
        Task task = taskRepositoryPort.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

        // Regla donde una tarea completada no puede modificarse
        if (task.isCompleted()) {
            throw new RuntimeException("La tarea ya está completada y no puede modificarse");
        }

        task.setCompleted(true);
        taskRepositoryPort.save(task);

        // Auditoría y Notificación
        auditLogPort.register("TASK_COMPLETED", task.getId());
        notificationPort.notify("Tarea finalizada: " + task.getTitle());
    }
}