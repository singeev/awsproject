package com.awsproject.utils;

import com.awsproject.backend.persistence.domain.backend.User;
import com.awsproject.web.controllers.ForgotMyPasswordController;
import com.awsproject.web.domain.frontend.BasicAccountPayload;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by singeev on 21/11/2017.
 */
public class UserUtils {

    private UserUtils() {
        throw new AssertionError("Non instantiable");
    }

    /**
     * Creates a user basic attributes set.
     *
     * @param userName The userName
     * @param email    The email
     * @return A User entity
     */
    public static User createBasicUser(String userName, String email) {
        User user = new User();
        user.setUsername(userName);
        user.setPassword("password");
        user.setEmail(email);
        user.setFirstName("Big");
        user.setLastName("Green");
        user.setPhoneNumber("9871098234");
        user.setCountry("USA");
        user.setEnabled(true);
        user.setDescription("A test user");
        user.setProfileImageUrl("https://sldkf.image.com/hulk");
        return user;
    }

    public static String createPasswordResetUrl(HttpServletRequest request, long userId, String token) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +
                request.getContextPath() + ForgotMyPasswordController.CHANGE_PASSWORD_URL_PATH +
                "?id=" + userId + "&token=" + token;
    }

    public static <T extends BasicAccountPayload> User fromWebUserToDomainUser(T webUser) {
        return User.builder()
                .firstName(webUser.getFirstName())
                .lastName(webUser.getLastName())
                .password(webUser.getPassword())
                .username(webUser.getUsername())
                .email(webUser.getEmail())
                .phoneNumber(webUser.getPhoneNumber())
                .description(webUser.getDescription())
                .country(webUser.getCountry())
                .build();
    }
}
