package com.awsproject.backend.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by singeev on 21/11/2017.
 */
@Slf4j
public class SMTPMailService extends AbstractEmailService{

    @Autowired
    private MailSender mailSender;

    @Override
    public void sendGenericEmailMessage(SimpleMailMessage message) {
        LOGGER.debug("Sending email message: {}", message);
        mailSender.send(message);
        LOGGER.info("Email message sent: {}", message);
    }
}
