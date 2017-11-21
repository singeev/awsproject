package com.awsproject.config;

import com.awsproject.backend.service.EmailService;
import com.awsproject.backend.service.SMTPMailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by singeev on 21/11/2017.
 */

@Configuration
@Profile("prod")
@PropertySource("file:///${user.home}/.awsproject/application-prod.properties")
public class ProductionConfig {

    @Bean
    public EmailService emailService() {
        return new SMTPMailService();
    }
}
