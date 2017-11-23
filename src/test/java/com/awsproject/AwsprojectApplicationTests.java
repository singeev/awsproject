package com.awsproject;

import com.awsproject.backend.service.I18nService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AwsprojectApplicationTests {

	@Autowired
	private I18nService i18nService;

	@Test
	public void testMessageTestRetrieving() throws Exception {
		String expectedText = "Bootstrap starter template";
		String messageId = "index.main.callout";
		String actualText = i18nService.getMessage(messageId);
		assertEquals(expectedText, actualText);
	}
}
