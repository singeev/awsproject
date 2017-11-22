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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by singeev on 22/11/2017.
 */
@Component
public class TestHelper {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public User createAndSaveBasicUser(String userName, String email) {
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
}
