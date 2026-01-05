package com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.notification;

import com.ProjectTask_cav.project_task_management_system.domain.port.out.notification.NotificationPort;
import org.springframework.stereotype.Component;

@Component
public class ConsoleNotificationAdapter implements NotificationPort {
    @Override
    public void notify(String message) {
        System.out.println("[NOTIFICATION] " + message);
    }
}