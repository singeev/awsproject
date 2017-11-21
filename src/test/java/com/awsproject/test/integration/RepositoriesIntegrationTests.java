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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by singeev on 21/11/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoriesIntegrationTests {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void init() {
        assertNotNull(planRepository);
        assertNotNull(roleRepository);
        assertNotNull(userRepository);
    }

    @Test
    public void shouldCreateNewPlan() throws Exception {
        Plan basicPlan = new Plan(PlansEnum.BASIC);
        planRepository.save(basicPlan);
        Plan retrievedPlan = planRepository.findOne(PlansEnum.BASIC.getId());
        assertNotNull(retrievedPlan);
        assertEquals(basicPlan.getName(), retrievedPlan.getName());
    }

    @Test
    public void shouldCreateNewRole() throws Exception {
        Role role = new Role(RolesEnum.BASIC);
        roleRepository.save(role);
        Role retrievedRole = roleRepository.findOne(RolesEnum.BASIC.getId());
        assertNotNull(retrievedRole);
        assertEquals(role.getName(), retrievedRole.getName());
    }

    @Test
    public void shouldCreateNewUser() {
        Plan plan = new Plan(PlansEnum.BASIC);
        planRepository.save(plan);

        User user = createBasicUser();
        user.setPlan(plan);

        Role role = new Role(RolesEnum.BASIC);
        Set<UserRole> userRoles = new HashSet<>();
        UserRole userRole = new UserRole(user, role);
        userRoles.add(userRole);
        user.getUserRoles().addAll(userRoles);

        for(UserRole r : userRoles) {
            roleRepository.save(r.getRole());
        }

        user = userRepository.save(user);
        User retrievedUser = userRepository.findOne(user.getId());
        assertNotNull(retrievedUser);
        assertTrue(retrievedUser.getId() != 0);
        assertNotNull(retrievedUser.getPlan());
        assertNotNull(retrievedUser.getPlan().getId());
        Set<UserRole> retrievedUserRoles = retrievedUser.getUserRoles();
        retrievedUserRoles.forEach(ur -> {
            assertNotNull(ur.getRole());
            assertNotNull(ur.getRole().getId());
        });
    }

    private User createBasicUser() {
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
