package com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Entity
@Table(name = "tasks")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class TaskEntity {
    @Id
    private UUID id;
    private String title;
    private boolean completed;
    private boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private ProjectEntity project;
}