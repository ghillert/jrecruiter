package org.jrecruiter.test;

import org.junit.Assert;

import org.jrecruiter.spring.ApplicationConfiguration;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Base class for Dao Test Cases.
 *
 * @author Gunnar Hillert
 *
 */
public class CheckSystemTest extends BaseServiceIntegrationTest {

	private final static Logger LOGGER = LoggerFactory.getLogger(CheckSystemTest.class);

	private @Autowired ApplicationConfiguration applicationConfiguration;

	@Test
	public void VerifyJrecruiterHomeExistsTest() {

		Assert.assertNotNull(applicationConfiguration);

		LOGGER.info("AppHomePath: " + applicationConfiguration.getApplicationHome().getAppHomePath());
		LOGGER.info("SpringContextMode: " + applicationConfiguration.getSpringContextMode().name());
	}
}
