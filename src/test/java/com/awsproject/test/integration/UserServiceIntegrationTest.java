package com.awsproject.test.integration;

import com.awsproject.backend.persistence.domain.backend.User;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by singeev on 21/11/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIntegrationTest extends AbstractServiceIntegrationTest {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Rule
    public TestName testName = new TestName();

    @Test
    public void createNewUserTest() {
        User user = createUser(testName);
        assertNotNull(user);
        assertNotNull(user.getId());
    }

    @Test
    public void shouldUpdateUserPasswordTest() {
        User user = createUser(testName);
        assertNotNull(user);
        assertNotNull(user.getId());
        String newPassword = UUID.randomUUID().toString();
        userService.updateUserPassword(user.getId(), newPassword);
        user = userRepository.findOne(user.getId());
        assertTrue(passwordEncoder.matches(newPassword, user.getPassword()));
    }
}
