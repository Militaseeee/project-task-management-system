package com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskRequest {
    @NotBlank(message = "El título de la tarea es obligatorio")
    private String title;
}