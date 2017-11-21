package com.awsproject.config;

import com.awsproject.backend.service.EmailService;
import com.awsproject.backend.service.MockEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by singeev on 21/11/2017.
 */

@Configuration
@Profile("dev")
@PropertySource("file:///${user.home}/.awsproject/application-dev.properties")
public class DevelopmentConfig {

    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }
}
