package com.awsproject.test.unit;

import com.awsproject.backend.persistence.domain.backend.User;
import com.awsproject.utils.UserUtils;
import com.awsproject.web.controllers.ForgotMyPasswordController;
import com.awsproject.web.domain.frontend.BasicAccountPayload;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by singeev on 23/11/2017.
 */
public class UserUtilsUnitTest {

    private MockHttpServletRequest mockHttpServletRequest;
    private PodamFactory podamFactory;

    @Before
    public void init() {
        mockHttpServletRequest = new MockHttpServletRequest();
        podamFactory = new PodamFactoryImpl();
    }

    @Test
    public void shouldConstructPasswordResetUrlForEmail() {
        mockHttpServletRequest.setServerPort(8080);
        String token = UUID.randomUUID().toString();
        long userId = 1234;
        String expectedUrl = "http://localhost:8080" +
                ForgotMyPasswordController.CHANGE_PASSWORD_URL_PATH +
                "?id=" + userId +
                "&token=" + token;
        String createdUrl = UserUtils.createPasswordResetUrl(mockHttpServletRequest, userId, token);
        assertEquals(expectedUrl, createdUrl);
    }

    @Test
    public void mapWebUserToDomainUser() {
        BasicAccountPayload webUser = podamFactory.manufacturePojoWithFullData(BasicAccountPayload.class);
        webUser.setEmail("me@example.com");

        User user = UserUtils.fromWebUserToDomainUser(webUser);

        assertNotNull(user);
        assertEquals(webUser.getUsername(), user.getUsername());
        assertEquals(webUser.getPassword(), user.getPassword());
        assertEquals(webUser.getCountry(), user.getCountry());
        assertEquals(webUser.getDescription(), user.getDescription());
        assertEquals(webUser.getEmail(), user.getEmail());
        assertEquals(webUser.getFirstName(), user.getFirstName());
        assertEquals(webUser.getLastName(), user.getLastName());
        assertEquals(webUser.getPhoneNumber(), user.getPhoneNumber());
    }
}
