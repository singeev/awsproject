package com.awsproject.backend.service;

import com.awsproject.web.domain.frontend.FeedbackPojo;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by singeev on 21/11/2017.
 */
public interface EmailService {

    /**
     * Sends an email with the content in the FeedbackPojo.
     * @param feedbackPojo
     */
    public void sendFeedbackEmail(FeedbackPojo feedbackPojo);

    /**
     * Sends an email with the content of the SimpleMailMessage object.
     * @param message
     */
    public void sendGenericEmailMessage(SimpleMailMessage message);
}

