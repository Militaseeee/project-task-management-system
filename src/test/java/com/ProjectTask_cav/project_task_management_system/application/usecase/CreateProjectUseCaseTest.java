package com.ProjectTask_cav.project_task_management_system.application.usecase;

import com.ProjectTask_cav.project_task_management_system.application.usecase.project.CreateProjectUseCaseImpl;
import com.ProjectTask_cav.project_task_management_system.domain.model.Project;
import com.ProjectTask_cav.project_task_management_system.domain.model.ProjectStatus;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.repository.ProjectRepositoryPort;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.security.CurrentUserPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateProjectUseCaseTest {

    @Mock private ProjectRepositoryPort projectRepository;
    @Mock private CurrentUserPort currentUser;

    @InjectMocks private CreateProjectUseCaseImpl useCase;

    @Test
    void CreateProject_ShouldAssignOwnerAndDefaultStatus() {
        // Arrange
        UUID userId = UUID.randomUUID();
        Project inputProject = new Project();
        inputProject.setName("Nuevo Proyecto");

        when(currentUser.getCurrentUserId()).thenReturn(userId);
        // Simulamos que el repositorio devuelve el mismo proyecto con un ID generado
        when(projectRepository.save(any(Project.class))).thenAnswer(i -> i.getArgument(0));

        // Act
        Project savedProject = useCase.create(inputProject);

        // Assert
        assertNotNull(savedProject);
        assertEquals(userId, savedProject.getOwnerId());
        assertEquals(ProjectStatus.DRAFT, savedProject.getStatus());
        assertFalse(savedProject.isDeleted());
        verify(projectRepository, times(1)).save(any(Project.class));
    }
}