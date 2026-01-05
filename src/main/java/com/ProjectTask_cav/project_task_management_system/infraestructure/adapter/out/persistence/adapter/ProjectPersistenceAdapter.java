package com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.persistence.adapter;

import com.ProjectTask_cav.project_task_management_system.domain.model.Project;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.repository.ProjectRepositoryPort;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.persistence.entity.ProjectEntity;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.persistence.mapper.ProjectDboMapper;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.persistence.repository.ProjectJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProjectPersistenceAdapter implements ProjectRepositoryPort {

    private final ProjectJpaRepository jpaRepository;
    private final ProjectDboMapper mapper;

    @Override
    public Project save(Project project) {
        if (project.getId() == null) project.setId(UUID.randomUUID());

        ProjectEntity entity = mapper.toEntity(project);
        ProjectEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Project> findById(UUID id) {
//        return jpaRepository.findById(id).map(mapper::toDomain);
        return jpaRepository.findByIdAndDeletedFalse(id).map(mapper::toDomain);
    }

    @Override
    public List<Project> findByOwnerId(UUID ownerId) {
        return jpaRepository.findByOwnerIdAndDeletedFalse(ownerId)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Project> findAllActive() {
//        return jpaRepository.findAll().stream()
//                .map(mapper::toDomain)
//                .collect(Collectors.toList());
        return jpaRepository.findAll().stream()
                .filter(entity -> !entity.isDeleted())
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

}