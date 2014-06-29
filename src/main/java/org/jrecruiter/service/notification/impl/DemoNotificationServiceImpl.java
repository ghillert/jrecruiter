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
package org.jrecruiter.service.notification.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.jrecruiter.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gunnar Hillert
 */
@Service("notificationService")
@Transactional
public class DemoNotificationServiceImpl implements NotificationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DemoNotificationServiceImpl.class);

	/** {@inheritDoc} */
	@Override
	public void sendEmail(final String email, final String subject, final Map context, final String templateName) {

		if (subject == null) {
			throw new IllegalArgumentException("Parameter 'tweet' must NOT be null.");
		}

		LOGGER.warn("Running in DEMO Mode - Email with subject '{}' will NOT be send.", subject);

	}

	/** {@inheritDoc} */
	@Override
	public void sendTweetToTwitter(final String tweet) {

		if (tweet == null) {
			throw new IllegalArgumentException("Parameter 'tweet' must NOT be null.");
		}

		LOGGER.warn("Running in DEMO Mode - Tweet '{}' will NOT be tweeted.", tweet);

	}

	/** {@inheritDoc} */
	@Override
	public URI shortenUrl(final String urlAsString) {

		if (urlAsString == null) {
			throw new IllegalArgumentException("Parameter 'urlAsString' must NOT be null.");
		}

		LOGGER.warn("Running in DEMO Mode - Url {} will NOT be shortened.", urlAsString);

		final URI uri;

		try {
			uri = new URI(urlAsString);
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException(
			String.format("Please provide a valid URI - %s is not valid", urlAsString));
		}

		return uri;

	}

}
