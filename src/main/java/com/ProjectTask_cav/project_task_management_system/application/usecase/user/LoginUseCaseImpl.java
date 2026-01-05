package com.ProjectTask_cav.project_task_management_system.application.usecase.user;

import com.ProjectTask_cav.project_task_management_system.domain.model.User;
import com.ProjectTask_cav.project_task_management_system.domain.port.in.user.LoginUseCase;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.repository.UserRepositoryPort;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.security.PasswordEncoderPort;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.security.TokenProviderPort;

public class LoginUseCaseImpl implements LoginUseCase {
    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final TokenProviderPort tokenProviderPort;

    public LoginUseCaseImpl(UserRepositoryPort userRepositoryPort, PasswordEncoderPort passwordEncoderPort, TokenProviderPort tokenProviderPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoderPort = passwordEncoderPort;
        this.tokenProviderPort = tokenProviderPort;
    }

    @Override
    public String login(String username, String password) {
        User user = userRepositoryPort.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("401 Unauthorized: Usuario no encontrado"));

        if (!passwordEncoderPort.matches(password, user.getPassword())) {
            throw new RuntimeException("401 Unauthorized: Credenciales inválidas");
        }

        return tokenProviderPort.generateToken(username);
    }
}