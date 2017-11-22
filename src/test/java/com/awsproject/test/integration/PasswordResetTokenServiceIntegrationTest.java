package com.awsproject.test.integration;

import com.awsproject.backend.persistence.domain.backend.PasswordResetToken;
import com.awsproject.backend.persistence.domain.backend.User;
import com.awsproject.backend.service.PasswordResetTokenService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by singeev on 22/11/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordResetTokenServiceIntegrationTest extends AbstractServiceIntegrationTest {

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    @Rule
    public TestName testName = new TestName();

    @Test
    public void shouldCreateTokenForUserByEmail() {
        User user = createUser(testName);
        PasswordResetToken passwordResetToken =
                passwordResetTokenService.createPasswordResetTokenForEmail(user.getEmail());
        assertNotNull(passwordResetToken);
        assertNotNull(passwordResetToken.getToken());
    }

    @Test
    public void shouldRetrieveToken() {
        User user = createUser(testName);
        PasswordResetToken passwordResetToken =
                passwordResetTokenService.createPasswordResetTokenForEmail(user.getEmail());
        assertNotNull(passwordResetToken);
        assertNotNull(passwordResetToken.getToken());
        PasswordResetToken token = passwordResetTokenService.findByToken(passwordResetToken.getToken());
        assertNotNull(token);
    }
}
