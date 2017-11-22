package com.awsproject.backend.service;

import com.awsproject.backend.persistence.domain.backend.PasswordResetToken;
import com.awsproject.backend.persistence.domain.backend.User;
import com.awsproject.backend.persistence.repositories.PasswordResetTokenRepository;
import com.awsproject.backend.persistence.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by singeev on 22/11/2017.
 */
@Service
@Transactional(readOnly = true)
@Slf4j
public class PasswordResetTokenService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Value("${token.expiration.length.minutes}")
    private int tokenExpirationInMinutes;

    public PasswordResetToken findByToken(String token) {return passwordResetTokenRepository.findByToken(token);}

    @Transactional
    public PasswordResetToken createPasswordResetTokenForEmail(String email) {
        PasswordResetToken passwordResetToken = null;
        User user = userRepository.findByEmail(email);
        if (user != null) {
            String token = UUID.randomUUID().toString();
            LocalDateTime now = LocalDateTime.now(Clock.systemUTC());
            passwordResetToken = new PasswordResetToken(token, user, now, tokenExpirationInMinutes);
            passwordResetToken = passwordResetTokenRepository.save(passwordResetToken);
            LOGGER.debug("Successfully created token {} for user {}", token, user.getUsername());
        } else {
            LOGGER.warn("Can't find a user with email {}", email);
        }
        return passwordResetToken;
    }
}
