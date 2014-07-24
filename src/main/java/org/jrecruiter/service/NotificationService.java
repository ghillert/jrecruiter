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

import java.util.Map;

import org.jrecruiter.service.impl.EmailRequest;

/**
 * Responsible for handling any type of notifications e.g. password reminder email,
 * emails containing job posting information etc.
 *
 * @author Gunnar Hillert
 */
public interface NotificationService {

	/**
	 * Method for sending emails.
	 *
	 * @param email The email address
	 * @param subject
	 * @param context Map that contains data that needs to be rendered in the email
	 * @param templatePrefix Freemarker template
	 */
	void sendEmail(EmailRequest emailRequest);

	/**
	 * Sends a tweet to twitter using the global twitter
	 * account setup in the system.
	 *
	 * */
	void sendTweetToTwitter(String tweet);

}
