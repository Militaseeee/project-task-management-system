package com.ProjectTask_cav.project_task_management_system.domain.port.out.security;

public interface TokenProviderPort {
    String generateToken(String username);
}