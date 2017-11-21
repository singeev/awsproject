package com.awsproject.backend.service;

import com.awsproject.web.domain.frontend.FeedbackPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by singeev on 21/11/2017.
 */
public abstract class AbstractEmailService implements EmailService{

    @Value("${default.to.address}")
    private String defaultToAddress;

    /**
     * Creates a SimpleMailMessage from a FeedbackPojo.
     * @param feedbackPojo The FeedbackPojo
     */
    protected SimpleMailMessage convertFeedbackPojoToSimpleMailMessage(FeedbackPojo feedbackPojo) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(defaultToAddress);
        message.setFrom(feedbackPojo.getEmail());
        message.setSubject("[AWS Project]: Feedback received from " +
                feedbackPojo.getFirstName() + " " + feedbackPojo.getLastName() + "!");
        message.setText(feedbackPojo.getFeedback());
        return message;
    }

    @Override
    public void sendFeedbackEmail(FeedbackPojo feedbackPojo) {
        sendGenericEmailMessage(convertFeedbackPojoToSimpleMailMessage(feedbackPojo));
    }
}
