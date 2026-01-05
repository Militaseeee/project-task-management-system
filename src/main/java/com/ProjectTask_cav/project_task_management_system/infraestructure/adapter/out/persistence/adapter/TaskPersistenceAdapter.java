package com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.persistence.adapter;

import com.ProjectTask_cav.project_task_management_system.domain.model.Task;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.repository.TaskRepositoryPort;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.persistence.entity.TaskEntity;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.persistence.mapper.TaskDboMapper;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.persistence.repository.TaskJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskPersistenceAdapter implements TaskRepositoryPort {

    private final TaskJpaRepository jpaRepository;
    private final TaskDboMapper mapper;

    @Override
    public Task save(Task task) {
        if (task.getId() == null) task.setId(UUID.randomUUID());

        TaskEntity entity = mapper.toEntity(task);
        TaskEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Task> findById(UUID id) {
//        return jpaRepository.findById(id).map(mapper::toDomain);
        return jpaRepository.findByIdAndDeletedFalse(id).map(mapper::toDomain);
    }

    @Override
    public List<Task> findByProjectId(UUID projectId) {
        return jpaRepository.findByProjectIdAndDeletedFalse(projectId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

}