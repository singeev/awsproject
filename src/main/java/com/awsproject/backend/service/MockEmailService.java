package com.awsproject.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by singeev on 21/11/2017.
 */
@Slf4j
public class MockEmailService extends AbstractEmailService {

    @Override
    public void sendGenericEmailMessage(SimpleMailMessage message) {
        LOGGER.info("Mocking email sending process...");
        LOGGER.info(message.toString());
        LOGGER.info("Email (fake) send.");
    }
}
