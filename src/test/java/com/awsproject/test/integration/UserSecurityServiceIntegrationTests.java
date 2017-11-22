package com.awsproject.test.integration;

import com.awsproject.backend.persistence.domain.backend.User;
import com.awsproject.backend.service.UserSecurityService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by singeev on 22/11/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserSecurityServiceIntegrationTests extends AbstractIntegrationTest {

    @Autowired
    private UserSecurityService userSecurityService;

    @Rule
    public TestName testName = new TestName();

    @Before
    public void init() {
        assertNotNull(userSecurityService);
    }

    @Test
    public void shouldCreateUserAndRetrieveThemByUserName() {
        User user = createUser(testName);
        assertTrue(user.getId() != 0);
        UserDetails retrievedUser = userSecurityService.loadUserByUsername(testName.getMethodName());
        assertEquals(user, retrievedUser);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void shouldThowTheExceptionWhenUserNameNotFound() {
        UserDetails retrievedUser = userSecurityService.loadUserByUsername("wrongName");
    }
}
