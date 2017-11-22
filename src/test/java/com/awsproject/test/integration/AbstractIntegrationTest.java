package com.awsproject.test.integration;

import com.awsproject.backend.persistence.domain.backend.Plan;
import com.awsproject.backend.persistence.domain.backend.Role;
import com.awsproject.backend.persistence.domain.backend.User;
import com.awsproject.backend.persistence.domain.backend.UserRole;
import com.awsproject.backend.persistence.repositories.PlanRepository;
import com.awsproject.backend.persistence.repositories.RoleRepository;
import com.awsproject.backend.persistence.repositories.UserRepository;
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
public abstract class AbstractIntegrationTest {

    @Autowired
    protected PlanRepository planRepository;

    @Autowired
    protected RoleRepository roleRepository;

    @Autowired
    protected UserRepository userRepository;

    protected User createAndSaveBasicUser(String userName, String email) {
        Plan plan = new Plan(PlansEnum.BASIC);
        planRepository.save(plan);

        User user = UserUtils.createBasicUser(userName, email);
        user.setPlan(plan);

        Role role = new Role(RolesEnum.BASIC);
        roleRepository.save(role);

        Set<UserRole> userRoles = new HashSet<>();
        UserRole userRole = new UserRole(user, role);
        userRoles.add(userRole);

        user.getUserRoles().addAll(userRoles);
        user = userRepository.save(user);
        return user;
    }

    protected User createUser(TestName testName) {
        return createAndSaveBasicUser(testName.getMethodName(), testName.getMethodName() + "@gmail.com");
    }
}
