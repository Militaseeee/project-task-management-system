package com.ProjectTask_cav.project_task_management_system.application.usecase;

import com.ProjectTask_cav.project_task_management_system.application.usecase.project.ActivateProjectUseCaseImpl;
import com.ProjectTask_cav.project_task_management_system.domain.model.Project;
import com.ProjectTask_cav.project_task_management_system.domain.model.ProjectStatus;
import com.ProjectTask_cav.project_task_management_system.domain.model.Task;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.repository.*;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.audit.AuditLogPort;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.notification.NotificationPort;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.security.CurrentUserPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActivateProjectUseCaseTest {

    @Mock private ProjectRepositoryPort projectRepository;
    @Mock private AuditLogPort auditLog;
    @Mock private NotificationPort notification;
    @Mock private CurrentUserPort currentUser;

    @InjectMocks private ActivateProjectUseCaseImpl useCase;

    private UUID userId;
    private UUID projectId;
    private Project project;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        projectId = UUID.randomUUID();
        project = new Project();
        project.setId(projectId);
        project.setOwnerId(userId);
        project.setStatus(ProjectStatus.DRAFT);
    }

    @Test
    void ActivateProject_WithTasks_ShouldSucceed() {
        // Arrange: Proyecto del dueño con una tarea activa
        Task task = new Task(UUID.randomUUID(), projectId, "Tarea 1", false, false);
        project.setTasks(List.of(task));

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(currentUser.getCurrentUserId()).thenReturn(userId);

        // Act
        useCase.activate(projectId);

        // Assert
        assertEquals(ProjectStatus.ACTIVE, project.getStatus());
        verify(projectRepository).save(project);
        verify(auditLog).register(eq("PROJECT_ACTIVATED"), any());
        verify(notification).notify(anyString());
    }

    @Test
    void ActivateProject_WithoutTasks_ShouldFail() {
        // Arrange: Proyecto sin tareas
        project.setTasks(List.of());
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(currentUser.getCurrentUserId()).thenReturn(userId);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> useCase.activate(projectId));
    }

    @Test
    void ActivateProject_ByNonOwner_ShouldFail() {
        // Arrange: El ID del usuario actual es diferente al dueño
        UUID intruderId = UUID.randomUUID();
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(currentUser.getCurrentUserId()).thenReturn(intruderId);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> useCase.activate(projectId));
    }
}