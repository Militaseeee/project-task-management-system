package com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.security.adapter;

import com.ProjectTask_cav.project_task_management_system.domain.port.out.security.PasswordEncoderPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptAdapter implements PasswordEncoderPort {

    // Usamos la implementación oficial de Spring Security
    private final PasswordEncoder passwordEncoder;

    public BCryptAdapter() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        // Compara el password que llega del login con el que está en la BD
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}