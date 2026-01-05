package com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.dto.response;

import lombok.Data;
import java.util.UUID;

@Data
public class ProjectResponse {
    private UUID id;
    private String name;
    private String status;
}