/*
 * Copyright 2002-2014 the original author or authors.
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

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.jrecruiter.common.Constants.AppHomeSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Gunnar Hillert
 * @version 2.0
 *
 */
public final class SystemInformationUtils {

	private final static Logger LOGGER = LoggerFactory.getLogger(SystemInformationUtils.class);

	public static int    CONSOLE_SPACER_WIDTH     = 40;
	public static String CONSOLE_SPACER_CHARACTER = ".";
	public static String JRECRUITER_CONFIG_FILENAME = "jrecruiter.properties";

	/**
	 *
	 */
	private SystemInformationUtils() {
		// private utility class constructor
	}

	/**
	 *
	 * @return
	 */
	public static Apphome retrieveBasicSystemInformation() {

		final Apphome apphome = new Apphome();

		if (StringUtils.isNotBlank(System.getProperty(Apphome.APP_HOME_DIRECTORY))) {

			apphome.setAppHomePath(System.getProperty(Apphome.APP_HOME_DIRECTORY));
			apphome.setAppHomeSource(AppHomeSource.SYSTEM_PROPERTY);

		 } else if (StringUtils.isNotBlank(System.getenv(Apphome.APP_HOME_DIRECTORY))) {

			apphome.setAppHomePath(System.getenv(Apphome.APP_HOME_DIRECTORY));
			apphome.setAppHomeSource(AppHomeSource.ENVIRONMENT_VARIABLE);

		 } else {

			 final String userHomeDirectiory = System.getProperty("user.home");

			 apphome.setAppHomePath(userHomeDirectiory + File.separator + ".jrecruiter");
			 apphome.setAppHomeSource(AppHomeSource.USER_DIRECTORY);

		 }

		return apphome;
	}

	/**
	 *
	 * @param filePath
	 * @return
	 */
	public static boolean existsConfigFile(final String filePath) {

		final File configFile = new File(filePath + File.separator + JRECRUITER_CONFIG_FILENAME);

		return configFile.exists() && configFile.isFile();

	}

	public static SpringContextMode getSpringContextMode(final String filePath) {

		if (existsConfigFile(filePath)) {
			return SpringContextMode.ProductionContextConfiguration;
		} else {
			return SpringContextMode.DemoContextConfiguration;
		}

	}

	/**
	 *
	 * @return
	 */
	public static String getAllEnvironmentVariables() {

		final StringBuilder environmentVariables = new StringBuilder();

		final Map<String, String> environmentVariablesAsMap = System.getenv();

		for (final Entry<String, String> entry : environmentVariablesAsMap.entrySet()) {

			final String label = StringUtils.rightPad(entry.getKey(), CONSOLE_SPACER_WIDTH, CONSOLE_SPACER_CHARACTER);
			environmentVariables.append(label + ": " + entry.getValue() + "\n");

		}

		return environmentVariables.toString();
	}

	/**
	 *
	 * @return
	 */
	public static String getAllSystemProperties() {

		final StringBuilder systemProperties = new StringBuilder();

		for (SystemPropertyInformation property : SystemPropertyInformation.getSystemPropertyValuesAsList()) {

			final String label = StringUtils.rightPad(property.getPropertyKey(), CONSOLE_SPACER_WIDTH, CONSOLE_SPACER_CHARACTER);
			systemProperties.append( label + ": " + property.getPropertyValue()).append("\n");
		}
		return systemProperties.toString();
	}

}

