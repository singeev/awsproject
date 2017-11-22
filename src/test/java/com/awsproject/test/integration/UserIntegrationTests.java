package com.awsproject.test.integration;

import com.awsproject.backend.persistence.domain.backend.Plan;
import com.awsproject.backend.persistence.domain.backend.Role;
import com.awsproject.backend.persistence.domain.backend.User;
import com.awsproject.backend.persistence.domain.backend.UserRole;
import com.awsproject.enums.PlansEnum;
import com.awsproject.enums.RolesEnum;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by singeev on 21/11/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserIntegrationTests extends AbstractIntegrationTest {

    @Rule
    public TestName testName = new TestName();

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
        User user = createUser(testName);
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

    @Test
    public void shouldDeleteUser() {
        long userId = createUser(testName).getId();
        assertTrue(userId != 0);
        userRepository.delete(userId);
        assertFalse(userRepository.exists(userId));
    }
}
