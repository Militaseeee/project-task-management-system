package com.ProjectTask_cav.project_task_management_system.domain.port.out.audit;

import java.util.UUID;

public interface AuditLogPort {
    void register(String action, UUID entityId);
}