/*
 * Copyright 2006-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
