package com.ProjectTask_cav.project_task_management_system.infraestructure.adapter.in.web.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntime(RuntimeException ex) {
        // Si el mensaje contiene "403", devolvemos Forbidden
        if (ex.getMessage().contains("403")) {
            return ResponseEntity.status(403).body(Map.of("message", ex.getMessage()));
        }
        // Para lo demás, devolvemos un 400 Bad Request con el mensaje
        return ResponseEntity.badRequest().body(Map.of("message", ex.getMessage()));
    }
}