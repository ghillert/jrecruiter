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
package org.jrecruiter.common;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This unit test tests the generation of GoogleMaps urls.
 *
 * @author Gunnar Hillert
 */
public class SystemInformationUtilsUnitTest extends TestCase {

	private final static Logger LOGGER = LoggerFactory.getLogger(SystemInformationUtilsUnitTest.class);

	public void testNewArrayListInstance() {

		Apphome apphome = SystemInformationUtils.retrieveBasicSystemInformation();

		LOGGER.info("Apphome: " + apphome.getAppHomePath());

		assertNotNull(apphome);

	}

	public void testGetAllEnvironmentVariables() {

		final String environmentVariablesAsString = SystemInformationUtils.getAllEnvironmentVariables();
		assertNotNull(environmentVariablesAsString);

	}

	public void testGetAllSystemProperties() {

		final String systemPropertiesAsString = SystemInformationUtils.getAllSystemProperties();
		assertNotNull(systemPropertiesAsString);

	}


}
