package com.ProjectTask_cav.project_task_management_system.application.usecase.user;

import com.ProjectTask_cav.project_task_management_system.domain.model.User;
import com.ProjectTask_cav.project_task_management_system.domain.port.in.user.RegisterUserUseCase;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.repository.UserRepositoryPort;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.security.PasswordEncoderPort;

import java.util.UUID;

public class RegisterUserUseCaseImpl implements RegisterUserUseCase {
    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;

    public RegisterUserUseCaseImpl(UserRepositoryPort userRepositoryPort, PasswordEncoderPort passwordEncoderPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public User register(User user) {
        user.setPassword(passwordEncoderPort.encode(user.getPassword()));
        if (user.getId() == null) user.setId(UUID.randomUUID());
        return userRepositoryPort.save(user);
    }
}