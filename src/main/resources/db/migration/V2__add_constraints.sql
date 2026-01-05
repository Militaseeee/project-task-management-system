ALTER TABLE projects
    ADD CONSTRAINT fk_projects_owner
    FOREIGN KEY (owner_id) REFERENCES users(id);

ALTER TABLE tasks
    ADD CONSTRAINT fk_tasks_project
    FOREIGN KEY (project_id) REFERENCES projects(id);

-- Índices para mejorar la velocidad de búsqueda
CREATE INDEX idx_projects_owner ON projects(owner_id);
CREATE INDEX idx_tasks_project ON tasks(project_id);