package com.awsproject.test.integration;

import com.awsproject.backend.persistence.domain.backend.Plan;
import com.awsproject.backend.persistence.domain.backend.Role;
import com.awsproject.backend.persistence.domain.backend.User;
import com.awsproject.backend.persistence.domain.backend.UserRole;
import com.awsproject.backend.persistence.repositories.PlanRepository;
import com.awsproject.backend.persistence.repositories.RoleRepository;
import com.awsproject.backend.persistence.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by singeev on 21/11/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoriesIntegrationTests {

    private static final Integer BASIC_PLAN_ID = 37;
    private static final Integer BASIC_ROLE_ID = 41;

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
        Plan basicPlan = createBasicPlan();
        planRepository.save(basicPlan);
        Plan retrievedPlan = planRepository.findOne(BASIC_PLAN_ID);
        assertNotNull(retrievedPlan);
        assertEquals(basicPlan.getName(), retrievedPlan.getName());
    }

    @Test
    public void shouldCreateNewRole() throws Exception {
        Role role = createBasicRole();
        roleRepository.save(role);
        Role retrievedRole = roleRepository.findOne(BASIC_ROLE_ID);
        assertNotNull(retrievedRole);
        assertEquals(role.getName(), retrievedRole.getName());
    }

    @Test
    public void shouldCreateNewUser() {
        Plan plan = createBasicPlan();
        planRepository.save(plan);

        User user = createBasicUser();
        user.setPlan(plan);

        Role role = createBasicRole();
        Set<UserRole> userRoles = new HashSet<>();
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
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

    private Role createBasicRole() {
        Role role = new Role();
        role.setId(BASIC_ROLE_ID);
        role.setName("RoleName");
        return role;
    }

    private Plan createBasicPlan() {
        Plan plan = new Plan();
        plan.setId(BASIC_PLAN_ID);
        plan.setName("Basic");
        return plan;
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
