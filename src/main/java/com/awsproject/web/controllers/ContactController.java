package com.awsproject.web.controllers;

import com.awsproject.backend.service.EmailService;
import com.awsproject.web.domain.frontend.FeedbackPojo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by singeev on 21/11/2017.
 */

@Controller
@Slf4j
public class ContactController {

    private static final String FEEDBACK_MODEL_KEY = "feedback";
    private static final String CONTACT_US_VIEW_NAME = "contact/contact";

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contactGet(ModelMap model) {
        FeedbackPojo feedbackPojo = new FeedbackPojo();
        model.addAttribute(ContactController.FEEDBACK_MODEL_KEY, feedbackPojo);
        return ContactController.CONTACT_US_VIEW_NAME;
    }

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public String processFeedback(@ModelAttribute(FEEDBACK_MODEL_KEY) FeedbackPojo feedbackPojo) {
        LOGGER.info("Got feedback: {}", feedbackPojo.toString());
        emailService.sendFeedbackEmail(feedbackPojo);
        return ContactController.CONTACT_US_VIEW_NAME;
    }
}
