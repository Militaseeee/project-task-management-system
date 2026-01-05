package com.ProjectTask_cav.project_task_management_system.application.usecase;

import com.ProjectTask_cav.project_task_management_system.application.usecase.task.CreateTaskUseCaseImpl;
import com.ProjectTask_cav.project_task_management_system.domain.model.Project;
import com.ProjectTask_cav.project_task_management_system.domain.model.Task;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.audit.AuditLogPort;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.notification.NotificationPort;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.repository.ProjectRepositoryPort;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.repository.TaskRepositoryPort;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.security.CurrentUserPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateTaskUseCaseTest {

    @Mock private TaskRepositoryPort taskRepository;
    @Mock private ProjectRepositoryPort projectRepository;
    @Mock private CurrentUserPort currentUser;

    @InjectMocks private CreateTaskUseCaseImpl useCase;

    @Test
    void CreateTask_AsOwner_ShouldSucceed() {
        // Arrange
        UUID userId = UUID.randomUUID();
        UUID projectId = UUID.randomUUID();

        Project project = new Project();
        project.setId(projectId);
        project.setOwnerId(userId); // Es el dueño

        Task taskToCreate = new Task();
        taskToCreate.setTitle("Mi Tarea");

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(currentUser.getCurrentUserId()).thenReturn(userId);
        when(taskRepository.save(any(Task.class))).thenAnswer(i -> i.getArgument(0));

        // Act
        Task savedTask = useCase.create(projectId, taskToCreate);

        // Assert
        assertNotNull(savedTask);
        assertEquals(projectId, savedTask.getProjectId());
        assertFalse(savedTask.isCompleted());
        verify(taskRepository).save(any(Task.class));
    }

    @Test
    void CreateTask_ByNonOwner_ShouldFail() {
        // Arrange
        UUID ownerId = UUID.randomUUID();
        UUID intruderId = UUID.randomUUID();
        UUID projectId = UUID.randomUUID();

        Project project = new Project();
        project.setId(projectId);
        project.setOwnerId(ownerId); // El dueño es otro

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(currentUser.getCurrentUserId()).thenReturn(intruderId); // Quien intenta es el intruso

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                useCase.create(projectId, new Task())
        );

        assertTrue(exception.getMessage().contains("403 Forbidden"));
        verify(taskRepository, never()).save(any());
    }
}