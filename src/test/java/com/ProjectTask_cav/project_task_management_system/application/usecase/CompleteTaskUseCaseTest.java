package com.ProjectTask_cav.project_task_management_system.application.usecase;

import com.ProjectTask_cav.project_task_management_system.application.usecase.task.CompleteTaskUseCaseImpl;
import com.ProjectTask_cav.project_task_management_system.domain.model.Task;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.repository.*;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.audit.AuditLogPort;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.notification.NotificationPort;

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
class CompleteTaskUseCaseTest {

    @Mock private TaskRepositoryPort taskRepository;
    @Mock private AuditLogPort auditLog;
    @Mock private NotificationPort notification;

    @InjectMocks private CompleteTaskUseCaseImpl useCase;

    @Test
    void CompleteTask_AlreadyCompleted_ShouldFail() {
        // Arrange
        UUID taskId = UUID.randomUUID();
        Task task = new Task(taskId, UUID.randomUUID(), "Test", true, false); // Ya completada
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> useCase.complete(taskId));
    }

    @Test
    void CompleteTask_ShouldGenerateAuditAndNotification() {
        // Arrange
        UUID taskId = UUID.randomUUID();
        Task task = new Task(taskId, UUID.randomUUID(), "Test", false, false);
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        // Act
        useCase.complete(taskId);

        // Assert
        assertTrue(task.isCompleted());
        verify(auditLog).register(eq("TASK_COMPLETED"), eq(taskId));
        verify(notification).notify(contains("finalizada"));
    }
}