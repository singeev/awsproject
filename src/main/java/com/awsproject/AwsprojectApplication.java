package com.awsproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.awsproject.backend.persistence.repositories")
public class AwsprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsprojectApplication.class, args);
	}
}
