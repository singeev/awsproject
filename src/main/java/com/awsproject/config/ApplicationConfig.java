package com.awsproject.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by singeev on 21/11/2017.
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.awsproject.backend.persistence.repositories")
@EntityScan(basePackages = "com.awsproject.backend.persistence.domain.backend")
@EnableTransactionManagement
public class ApplicationConfig {
}
