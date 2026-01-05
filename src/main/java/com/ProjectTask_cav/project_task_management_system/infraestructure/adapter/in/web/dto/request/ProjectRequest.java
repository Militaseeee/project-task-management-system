package com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProjectRequest {
    @NotBlank(message = "El nombre del proyecto es obligatorio")
    private String name;
}