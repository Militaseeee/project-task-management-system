package com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.controller;

import com.ProjectTask_cav.project_task_management_system.domain.port.in.user.LoginUseCase;
import com.ProjectTask_cav.project_task_management_system.domain.port.in.user.RegisterUserUseCase;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.dto.request.LoginRequest;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.dto.request.RegisterRequest;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.dto.response.TokenResponse;
import com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.mapper.AuthWebMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegisterUserUseCase registerUseCase;
    private final LoginUseCase loginUseCase;
    private final AuthWebMapper mapper;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        registerUseCase.register(mapper.toDomain(request));
        return ResponseEntity.ok("Usuario registrado con éxito");
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        String token = loginUseCase.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(new TokenResponse(token));
    }
}