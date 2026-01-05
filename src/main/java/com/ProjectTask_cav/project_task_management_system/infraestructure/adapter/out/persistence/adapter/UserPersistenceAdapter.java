package com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.persistence.adapter;

import com.ProjectTask_cav.project_task_management_system.domain.model.User;
import com.ProjectTask_cav.project_task_management_system.domain.port.out.repository.UserRepositoryPort;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.persistence.entity.UserEntity;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.persistence.mapper.UserDboMapper;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.out.persistence.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final UserJpaRepository jpaRepository;
    private final UserDboMapper mapper;

    @Override
    public User save(User user) {
        UserEntity entity = mapper.toEntity(user);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jpaRepository.findByUsername(username).map(mapper::toDomain);
    }
}