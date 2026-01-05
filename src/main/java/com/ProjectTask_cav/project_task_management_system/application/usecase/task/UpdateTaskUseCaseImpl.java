package com.ProjectTask_cav.project_task_management_system.application.usecase.task;

import com.ProjectTask_cav.project_task_management_system.domain.model.Project;
import com.ProjectTask_cav.project_task_management_system.domain.model.Task;
import com.ProjectTask_cav.project_task_management_system.domain.port.in.task.UpdateTaskUseCase;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.repository.ProjectRepositoryPort;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.repository.TaskRepositoryPort;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.security.CurrentUserPort;
import lombok.RequiredArgsConstructor;
import java.util.UUID;

@RequiredArgsConstructor
public class UpdateTaskUseCaseImpl implements UpdateTaskUseCase {
    private final TaskRepositoryPort taskRepositoryPort;
    private final ProjectRepositoryPort projectRepositoryPort;
    private final CurrentUserPort currentUserPort;

    @Override
    public void update(UUID id, String title) {
        Task task = taskRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

        Project project = projectRepositoryPort.findById(task.getProjectId()).get();

        // Propiedad
        if (!project.getOwnerId().equals(currentUserPort.getCurrentUserId())) {
            throw new RuntimeException("403 Forbidden: No eres el dueño");
        }

        // Tarea completada no puede modificarse
        if (task.isCompleted()) {
            throw new RuntimeException("No se puede modificar una tarea completada");
        }

        task.setTitle(title);
        taskRepositoryPort.save(task);
    }
}