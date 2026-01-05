package com.ProjectTask_cav.project_task_management_system.application.usecase;

import com.ProjectTask_cav.project_task_management_system.application.usecase.user.LoginUseCaseImpl;
import com.ProjectTask_cav.project_task_management_system.domain.model.User;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.repository.UserRepositoryPort;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.security.PasswordEncoderPort;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.security.TokenProviderPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginUseCaseTest {

    @Mock
    private UserRepositoryPort userRepository;

    @Mock
    private PasswordEncoderPort passwordEncoder;

    @Mock
    private TokenProviderPort tokenProvider;

    private LoginUseCaseImpl loginUseCase;

    @BeforeEach
    void setUp() {
        loginUseCase = new LoginUseCaseImpl(userRepository, passwordEncoder, tokenProvider);
    }

    @Test
    void login_WithValidCredentials_ShouldReturnToken() {
        // Arrange
        String email = "camila@test.com";
        String password = "password123";
        User user = new User(UUID.randomUUID(), "Camila", email, "encoded_pass");

        when(userRepository.findByUsername(email)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(password, "encoded_pass")).thenReturn(true);
        when(tokenProvider.generateToken(email)).thenReturn("fake-jwt-token");

        // Act
        String token = loginUseCase.login(email, password);

        // Assert
        assertEquals("fake-jwt-token", token);
        verify(tokenProvider).generateToken(email);
    }

    @Test
    void login_WithInvalidPassword_ShouldThrowException() {
        // Arrange
        String email = "camila@test.com";
        User user = new User(UUID.randomUUID(), "Camila", email, "encoded_pass");

        when(userRepository.findByUsername(email)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrong_pass", "encoded_pass")).thenReturn(false);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            loginUseCase.login(email, "wrong_pass");
        });
    }
}