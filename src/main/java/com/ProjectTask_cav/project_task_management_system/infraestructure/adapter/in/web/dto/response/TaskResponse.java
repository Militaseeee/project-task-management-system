package com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {
    private UUID id;
    private UUID projectId;
    private String title;
    private boolean completed;
}