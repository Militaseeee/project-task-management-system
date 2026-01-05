package com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.security.adapter;

import com.ProjectTask_cav.project_task_management_system.domain.port.out.security.CurrentUserPort;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.persistence.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CurrentUserAdapter implements CurrentUserPort {

    private final UserJpaRepository userRepository;

    @Override
    public UUID getCurrentUserId() {
        // Obtenemos el username que guardamos en el JwtAuthenticationFilter
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByUsername(username)
                .map(user -> user.getId())
                .orElseThrow(() -> new RuntimeException("Usuario autenticado no encontrado en BD"));
    }
}