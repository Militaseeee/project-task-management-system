package com.ProjectTask_cav.project_task_management_system.application.usecase;

import com.ProjectTask_cav.project_task_management_system.application.usecase.user.RegisterUserUseCaseImpl;
import com.ProjectTask_cav.project_task_management_system.domain.model.User;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.repository.UserRepositoryPort;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.security.PasswordEncoderPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterUserUseCaseTest {

    @Mock
    private UserRepositoryPort userRepository;

    @Mock
    private PasswordEncoderPort passwordEncoder;

    private RegisterUserUseCaseImpl registerUserUseCase;

    @BeforeEach
    void setUp() {
        registerUserUseCase = new RegisterUserUseCaseImpl(userRepository, passwordEncoder);
    }

    @Test
    void register_ShouldEncodePasswordAndSaveUser() {
        // Arrange
        User user = new User(null, "Camila", "camila@test.com", "123456");

        when(passwordEncoder.encode("123456")).thenReturn("encoded_password");
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        // Act
        User result = registerUserUseCase.register(user);

        // Assert
        assertNotNull(result);
        assertEquals("encoded_password", result.getPassword());
        assertEquals("camila@test.com", result.getEmail());
        verify(passwordEncoder).encode("123456");
        verify(userRepository).save(any(User.class));
    }
}