package com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.persistence.entity;

import com.ProjectTask_cav.project_task_management_system.domain.model.ProjectStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "projects")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ProjectEntity {
    @Id
    private UUID id;
    private UUID ownerId;
    private String name;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    private boolean deleted;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TaskEntity> tasks;
}