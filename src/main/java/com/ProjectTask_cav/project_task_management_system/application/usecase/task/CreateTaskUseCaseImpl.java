package com.ProjectTask_cav.project_task_management_system.application.usecase.task;

import com.ProjectTask_cav.project_task_management_system.domain.model.Project;
import com.ProjectTask_cav.project_task_management_system.domain.model.Task;
import com.ProjectTask_cav.project_task_management_system.domain.port.in.CreateTaskUseCase;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.repository.ProjectRepositoryPort;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.repository.TaskRepositoryPort;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.security.CurrentUserPort;
import java.util.UUID;

public class CreateTaskUseCaseImpl implements CreateTaskUseCase {
    private final TaskRepositoryPort taskRepositoryPort;
    private final ProjectRepositoryPort projectRepositoryPort;
    private final CurrentUserPort currentUserPort;

    public CreateTaskUseCaseImpl(TaskRepositoryPort taskRepositoryPort, ProjectRepositoryPort projectRepositoryPort, CurrentUserPort currentUserPort) {
        this.taskRepositoryPort = taskRepositoryPort;
        this.projectRepositoryPort = projectRepositoryPort;
        this.currentUserPort = currentUserPort;
    }

    @Override
    public Task create(UUID projectId, Task task) {
        // Buscamos el proyecto
        Project project = projectRepositoryPort.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        // Regla de negocio donde mira si es el dueño
        if (!project.getOwnerId().equals(currentUserPort.getCurrentUserId())) {
            throw new RuntimeException("403 Forbidden: No puedes agregar tareas a proyectos de otros");
        }

        // Seteamos datos basicos de la tarea
        task.setProjectId(projectId);
        task.setCompleted(false);
        task.setDeleted(false);

        return taskRepositoryPort.save(task);
    }
}