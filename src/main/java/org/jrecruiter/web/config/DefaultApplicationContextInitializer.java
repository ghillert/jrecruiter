/*
 * Copyright 2014 the original author or authors.
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
package org.jrecruiter.web.config;

import java.io.File;
import java.io.IOException;

import org.jrecruiter.common.Apphome;
import org.jrecruiter.common.SpringContextMode;
import org.jrecruiter.common.SystemInformationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.support.ResourcePropertySource;

public class DefaultApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultApplicationContextInitializer.class);
	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {

		final String profile = System.getProperty("jrecruiter-spring-profile");

		if (profile == null) {
			LOGGER.info("System property 'jrecruiter-spring-profile' not set. Setting active profile to '{}'.", SpringContextMode.DemoContextConfiguration.getCode());
			applicationContext.getEnvironment().setActiveProfiles(SpringContextMode.DemoContextConfiguration.getCode());
		}
		else {
			applicationContext.getEnvironment().setActiveProfiles(profile);
		}

		final ConfigurableEnvironment environment = applicationContext.getEnvironment();

		if (environment.acceptsProfiles("standalone")) {
			String tingHome = environment.getProperty(Apphome.APP_HOME_DIRECTORY);
			try {
				ResourcePropertySource source = new ResourcePropertySource("file:" + tingHome + File.separator + SystemInformationUtils.JRECRUITER_CONFIG_FILENAME);
				environment.getPropertySources().addFirst(source);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Properties for standalone mode loaded");
		}
		else {
			try {
				environment.getPropertySources().addFirst(new ResourcePropertySource("classpath:jrecruiter-demo.properties"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Properties for demo mode loaded");
		}

		final Boolean twitterEnabled = environment.getProperty("twitter.enabled", Boolean.class, Boolean.FALSE);

		if (twitterEnabled) {
			applicationContext.getEnvironment().addActiveProfile("twitter-enabled");
		}
		System.out.println("::Twitter enabled: " + twitterEnabled);

		final Boolean mailEnabled = environment.getProperty("mail.enabled", Boolean.class, Boolean.FALSE);

		if (mailEnabled) {
			applicationContext.getEnvironment().addActiveProfile("mail-enabled");
		}
		System.out.println("::Mail enabled: " + mailEnabled);

		final Boolean bitlyEnabled = environment.getProperty("mail.enabled", Boolean.class, Boolean.FALSE);

		if (bitlyEnabled) {
			applicationContext.getEnvironment().addActiveProfile("bitly-enabled");
		}
		System.out.println("::Bitly enabled: " + bitlyEnabled);
	}

}
