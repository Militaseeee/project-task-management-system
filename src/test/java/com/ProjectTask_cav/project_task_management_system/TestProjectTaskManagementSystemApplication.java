package com.ProjectTask_cav.project_task_management_system;

import org.springframework.boot.SpringApplication;

public class TestProjectTaskManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.from(ProjectTaskManagementSystemApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
