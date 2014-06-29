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
package org.jrecruiter.spring;

import java.io.Serializable;

import org.jrecruiter.common.Apphome;
import org.jrecruiter.common.SpringContextMode;
import org.jrecruiter.common.SystemInformationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class represents central jRecruiter configuration settings that can be
 * used by other parts of the application either at startup and or runtime.
 *
 * This classes data is retrieved at application startup and is not stored in the
 * database.
 *
 * @author Gunnar Hillert
 */
public class ApplicationConfiguration implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 2371390826931311420L;

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfiguration.class);

	// Fields
	private SpringContextMode springContextMode;
	private Apphome applicationHome;

	// Constructors

	/** default constructor */
	public ApplicationConfiguration() {

		final Apphome apphome = SystemInformationUtils.retrieveBasicSystemInformation();

		if (apphome == null) {
			throw new IllegalStateException("apphome is null");
		}

		final SpringContextMode springContextMode  = SystemInformationUtils.getSpringContextMode(apphome.getAppHomePath());

		this.applicationHome   = apphome;
		this.springContextMode = springContextMode;

		LOGGER.info("AppHomePath: " + apphome.getAppHomePath());
		LOGGER.info("SpringContextMode: " + springContextMode.name());
	}

	//~~~~~Getters~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * @return the springContextMode
	 */
	public SpringContextMode getSpringContextMode() {
		return springContextMode;
	}

	/**
	 * @return the applicationHome
	 */
	public Apphome getApplicationHome() {
		return applicationHome;
	}

}

