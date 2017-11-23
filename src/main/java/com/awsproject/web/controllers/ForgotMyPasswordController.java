package com.awsproject.web.controllers;

import com.awsproject.backend.persistence.domain.backend.PasswordResetToken;
import com.awsproject.backend.persistence.domain.backend.User;
import com.awsproject.backend.service.EmailService;
import com.awsproject.backend.service.I18nService;
import com.awsproject.backend.service.PasswordResetTokenService;
import com.awsproject.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by singeev on 23/11/2017.
 */
@Controller
@Slf4j
public class ForgotMyPasswordController {

    public static final String EMAIL_ADDRESS_VIEW_NAME = "forgotmypassword/emailForm";
    public static final String FORGOT_PASSWORD_URL_MAPPING = "/forgotmypassword";
    public static final String MAIL_SENT_KEY = "mailSent";
    public static final String CHANGE_PASSWORD_URL_PATH = "/changeuserpassword";
    public static final String EMAIL_MESSAGE_TEXT_PROPERTY_NAME = "forgotmypassword.email.text";

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    @Autowired
    private I18nService i18nService;

    @Autowired
    private EmailService emailService;

    @Value("${webmaster.email}")
    private String webmasterEmail;

    @RequestMapping(value = FORGOT_PASSWORD_URL_MAPPING, method = RequestMethod.GET)
    public String showForgetPasswordPage() {
        return EMAIL_ADDRESS_VIEW_NAME;
    }

    @RequestMapping(value = FORGOT_PASSWORD_URL_MAPPING, method = RequestMethod.POST)
    public String processForgotMyPassword(HttpServletRequest request,
                                          @RequestParam("email") String email,
                                          ModelMap modelMap) {

        PasswordResetToken passwordResetToken = passwordResetTokenService.createPasswordResetTokenForEmail(email);
        if (passwordResetToken == null) {
            LOGGER.warn("Can't create password reset token for email {}", email);
        } else {
            User user = passwordResetToken.getUser();
            String token = passwordResetToken.getToken();
            String passwordResetUrl = UserUtils.createPasswordResetUrl(request, user.getId(), token);
            LOGGER.info("Password reset URL {}", passwordResetUrl);
            sendPasswordResetEmail(request, user, passwordResetUrl);
        }
        modelMap.addAttribute(MAIL_SENT_KEY, "true");
        return EMAIL_ADDRESS_VIEW_NAME;
    }

    private void sendPasswordResetEmail(HttpServletRequest request, User user, String passwordResetUrl) {
        String emailText = i18nService.getMessage(EMAIL_MESSAGE_TEXT_PROPERTY_NAME, request.getLocale());
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("[AWS Project]: How to reset your password");
        mailMessage.setText(emailText + "\r\n" + passwordResetUrl);
        mailMessage.setFrom(webmasterEmail);
        emailService.sendGenericEmailMessage(mailMessage);
    }
}
