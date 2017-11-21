package com.awsproject.utils;

import com.awsproject.backend.persistence.domain.backend.User;

/**
 * Created by singeev on 21/11/2017.
 */
public class UsersUtils {

    private UsersUtils() {
        throw new AssertionError("Non instantiable");
    }

    /**
     * Creates a user basic attributes set.
     * @return A User entity
     */
    public static User createBasicUser() {
        User user = new User();
        user.setUsername("Hulk");
        user.setPassword("hsdfoiew");
        user.setEmail("hulk@gmail.com");
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
