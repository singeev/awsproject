package com.awsproject.test.unit;

import com.awsproject.utils.UserUtils;
import com.awsproject.web.controllers.ForgotMyPasswordController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * Created by singeev on 23/11/2017.
 */
public class UserUtilsUnitTest {

    private MockHttpServletRequest mockHttpServletRequest;

    @Before
    public void init() {mockHttpServletRequest = new MockHttpServletRequest();}

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
}
