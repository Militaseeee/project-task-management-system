package com.ProjectTask_cav.project_task_management_system.application.usecase.task;

import com.ProjectTask_cav.project_task_management_system.domain.model.Project;
import com.ProjectTask_cav.project_task_management_system.domain.model.Task;
import com.ProjectTask_cav.project_task_management_system.domain.port.in.task.DeleteTaskUseCase;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.repository.ProjectRepositoryPort;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.repository.TaskRepositoryPort;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.security.CurrentUserPort;
import lombok.RequiredArgsConstructor;
import java.util.UUID;

@RequiredArgsConstructor
public class DeleteTaskUseCaseImpl implements DeleteTaskUseCase {
    private final TaskRepositoryPort taskRepositoryPort;
    private final ProjectRepositoryPort projectRepositoryPort;
    private final CurrentUserPort currentUserPort;

    @Override
    public void delete(UUID id) {
        Task task = taskRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

        Project project = projectRepositoryPort.findById(task.getProjectId()).get();

        if (!project.getOwnerId().equals(currentUserPort.getCurrentUserId())) {
            throw new RuntimeException("403 Forbidden");
        }

        // Regla: Soft Delete
        task.setDeleted(true);
        taskRepositoryPort.save(task);
    }
}