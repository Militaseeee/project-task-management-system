package com.ProjectTask_cav.project_task_management_system.infraestructure.config;

import com.ProjectTask_cav.project_task_management_system.application.usecase.project.ActivateProjectUseCaseImpl;
import com.ProjectTask_cav.project_task_management_system.application.usecase.project.CreateProjectUseCaseImpl;
import com.ProjectTask_cav.project_task_management_system.application.usecase.project.DeleteProjectUseCaseImpl;
import com.ProjectTask_cav.project_task_management_system.application.usecase.project.UpdateProjectUseCaseImpl;
import com.ProjectTask_cav.project_task_management_system.application.usecase.task.*;
import com.ProjectTask_cav.project_task_management_system.application.usecase.user.*;
import com.ProjectTask_cav.project_task_management_system.domain.port.in.project.ActivateProjectUseCase;
import com.ProjectTask_cav.project_task_management_system.domain.port.in.project.CreateProjectUseCase;
import com.ProjectTask_cav.project_task_management_system.domain.port.in.project.DeleteProjectUseCase;
import com.ProjectTask_cav.project_task_management_system.domain.port.in.project.UpdateProjectUseCase;
import com.ProjectTask_cav.project_task_management_system.domain.port.in.task.*;
import com.ProjectTask_cav.project_task_management_system.domain.port.in.user.*;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.audit.AuditLogPort;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.notification.NotificationPort;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.repository.*;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public RegisterUserUseCase registerUserUseCase(UserRepositoryPort userRepo, PasswordEncoderPort encoder) {
        return new RegisterUserUseCaseImpl(userRepo, encoder);
    }

    @Bean
    public LoginUseCase loginUseCase(UserRepositoryPort userRepo, PasswordEncoderPort encoder, TokenProviderPort tokenProvider) {
        return new LoginUseCaseImpl(userRepo, encoder, tokenProvider);
    }

    @Bean
    public CreateProjectUseCase createProjectUseCase(ProjectRepositoryPort projectRepo, CurrentUserPort currentUser) {
        return new CreateProjectUseCaseImpl(projectRepo, currentUser);
    }

    @Bean
    public ActivateProjectUseCase activateProjectUseCase(ProjectRepositoryPort projectRepo, AuditLogPort audit, NotificationPort notify, CurrentUserPort currentUser) {
        return new ActivateProjectUseCaseImpl(projectRepo, audit, notify, currentUser);
    }

    @Bean
    public CreateTaskUseCase createTaskUseCase(TaskRepositoryPort taskRepo, ProjectRepositoryPort projectRepo, CurrentUserPort currentUser) {
        return new CreateTaskUseCaseImpl(taskRepo, projectRepo, currentUser);
    }

    @Bean
    public CompleteTaskUseCase completeTaskUseCase(TaskRepositoryPort taskRepo, AuditLogPort audit, NotificationPort notify) {
        return new CompleteTaskUseCaseImpl(taskRepo, audit, notify);
    }

    @Bean
    public UpdateProjectUseCase updateProjectUseCase(ProjectRepositoryPort port, CurrentUserPort user) {
        return new UpdateProjectUseCaseImpl(port, user);
    }

    @Bean
    public DeleteProjectUseCase deleteProjectUseCase(ProjectRepositoryPort port, CurrentUserPort user) {
        return new DeleteProjectUseCaseImpl(port, user);
    }

    @Bean
    public UpdateTaskUseCase updateTaskUseCase(TaskRepositoryPort tp, ProjectRepositoryPort pp, CurrentUserPort user) {
        return new UpdateTaskUseCaseImpl(tp, pp, user);
    }

    @Bean
    public DeleteTaskUseCase deleteTaskUseCase(TaskRepositoryPort tp, ProjectRepositoryPort pp, CurrentUserPort user) {
        return new DeleteTaskUseCaseImpl(tp, pp, user);
    }

    @Bean
    public GetTasksByProjectUseCase getTasksByProjectUseCase(TaskRepositoryPort tr, ProjectRepositoryPort pr, CurrentUserPort cu) {
        return new GetTasksByProjectUseCaseImpl(tr, pr, cu);
    }
}