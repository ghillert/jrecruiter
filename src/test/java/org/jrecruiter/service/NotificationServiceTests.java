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
package org.jrecruiter.service;

import org.jrecruiter.web.config.DefaultApplicationContextInitializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Gunnar Hillert
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		initializers=DefaultApplicationContextInitializer.class,
		locations={ "classpath:org/jrecruiter/core/spring/applicationContext-notifications.xml"})
public class NotificationServiceTests {

	@Autowired
	private NotificationService notificationService;

	@Test
	public void sendTweet() throws InterruptedException {
		System.out.println("Send Tweet");
		notificationService.sendTweetToTwitter("test");
	}
}
