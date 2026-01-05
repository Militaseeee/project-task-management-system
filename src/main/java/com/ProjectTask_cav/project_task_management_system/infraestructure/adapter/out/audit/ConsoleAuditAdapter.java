package com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.audit;

import com.ProjectTask_cav.project_task_management_system.domain.port.out.audit.AuditLogPort;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class ConsoleAuditAdapter implements AuditLogPort {
    @Override
    public void register(String action, UUID entityId) {
        System.out.println("[AUDIT LOG] Action: " + action + " on Entity ID: " + entityId);
    }
}