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
package org.jrecruiter.mock;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;

/**
 * @author Gunnar Hillert
 */
public class MockJavaMailSender extends JavaMailSenderImpl {

	public MockJavaMailSender() {
		super();
	}

	/**
	 * Logger for this class.
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(MockJavaMailSender.class);

	private MimeMessage message;

	@Override
	public void send(MimeMessagePreparator preparator) throws MailException {

		message = new MimeMessage((Session) null);
		try {
			preparator.prepare(message);
		} catch (Exception e) {
			throw new IllegalStateException("Error while preparing message.", e);
		}

		LOGGER.info("send() - Mock message successfully sent.");

	}

	@Override
	public void send(SimpleMailMessage simpleMessage) throws MailException {
		LOGGER.info("Sending...");
	}

	@Override
	public void send(MimeMessage msg) throws MailException {
		message = msg;
	}

}
