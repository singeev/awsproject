package com.awsproject;

import com.awsproject.backend.persistence.domain.backend.Role;
import com.awsproject.backend.persistence.domain.backend.User;
import com.awsproject.backend.persistence.domain.backend.UserRole;
import com.awsproject.backend.service.PlanService;
import com.awsproject.backend.service.UserService;
import com.awsproject.enums.PlansEnum;
import com.awsproject.enums.RolesEnum;
import com.awsproject.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@Slf4j
public class AwsprojectApplication implements CommandLineRunner{

	@Autowired
	private UserService userService;

	@Autowired
	private PlanService planService;

	@Value("${webmaster.username}")
	private String webmasterUserName;

	@Value("${webmaster.password}")
	private String webmasterPassword;

	@Value("${webmaster.email}")
	private String webmasterEmail;

	public static void main(String[] args) {
		SpringApplication.run(AwsprojectApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		LOGGER.info("Creating Basic and Pro plans in a DB");
		planService.createPlan(PlansEnum.BASIC.getId());
		planService.createPlan(PlansEnum.PRO.getId());

		User user = UserUtils.createBasicUser(webmasterUserName, webmasterEmail);
		user.setPassword(webmasterPassword);
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user, new Role(RolesEnum.ADMIN)));
		LOGGER.debug("Creating user with username {}", user.getUsername());
		userService.createUser(user, PlansEnum.PRO, userRoles);
		LOGGER.debug("User {} created", user.getUsername());
	}
}
