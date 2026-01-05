package com.ProjectTask_cav.project_task_management_system.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Project {

    private UUID id;
    private UUID ownerId;
    private String name;
    private ProjectStatus status; // DRAFT, ACTIVE
    private boolean deleted;
    private List<Task> tasks = new ArrayList<>();;

    public Project() {
    }

    public Project(UUID id, UUID ownerId, String name, ProjectStatus status, boolean deleted, List<Task> tasks) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.status = status;
        this.deleted = deleted;
        this.tasks = tasks;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
