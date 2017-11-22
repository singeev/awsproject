package com.awsproject.utils;

import com.awsproject.backend.persistence.domain.backend.User;

/**
 * Created by singeev on 21/11/2017.
 */
public class UserUtils {

    private UserUtils() {
        throw new AssertionError("Non instantiable");
    }

    /**
     * Creates a user basic attributes set.
     * @param userName The userName
     * @param email The email
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
}
