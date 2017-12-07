package com.awsproject.test.integration;

import com.awsproject.backend.persistence.domain.backend.Role;
import com.awsproject.backend.persistence.domain.backend.User;
import com.awsproject.backend.persistence.domain.backend.UserRole;
import com.awsproject.backend.persistence.repositories.UserRepository;
import com.awsproject.backend.service.UserService;
import com.awsproject.enums.PlansEnum;
import com.awsproject.enums.RolesEnum;
import com.awsproject.utils.UserUtils;
import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by singeev on 22/11/2017.
 */
public class AbstractServiceIntegrationTest {

    @Autowired
    protected UserService userService;

    @Autowired
    protected UserRepository userRepository;

    protected User createUser(TestName testName) {
        String userName = testName.getMethodName();
        String email = userName + "@gmail.com";

        Set<UserRole> userRoles = new HashSet<>();
        User basicUser = UserUtils.createBasicUser(userName, email);
        userRoles.add(new UserRole(basicUser, new Role(RolesEnum.BASIC)));

        return userService.createUser(basicUser, PlansEnum.BASIC, userRoles);
    }
}
