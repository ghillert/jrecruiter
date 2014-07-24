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

import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.jrecruiter.common.ApiKeysHolder;
import org.jrecruiter.service.impl.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author Gunnar Hillert
 */
public class NotificationServiceActivator {

	/**
	 * Mailsender.
	 */
	private @Autowired  JavaMailSender  mailSender;

	private @Autowired Configuration freemarkerConfiguration;

	/**
	 * Holder object for access information to external web services such as Twitter.
	 */
	private @Autowired ApiKeysHolder apiKeysHolder;

	/** {@inheritDoc} */
	public void sendEmail(final EmailRequest request) {

		final MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException, IOException {

				final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				message.setFrom("no_reply@jrecruiter.org");
				message.setTo(request.getEmail());
				message.setSubject(request.getSubject());

				final Locale locale = LocaleContextHolder.getLocale();

				final Template textTemplate = freemarkerConfiguration.getTemplate(request.getTemplatePrefix() + "-text.ftl", locale);

				final StringWriter textWriter = new StringWriter();
				try {
					textTemplate.process(request.getContext(), textWriter);
				} catch (TemplateException e) {
					throw new MailPreparationException("Can't generate email body.", e);
				}

				final Template htmlTemplate = freemarkerConfiguration.getTemplate(request.getTemplatePrefix() + "-html.ftl", locale);

				final StringWriter htmlWriter = new StringWriter();
				try {
					htmlTemplate.process(request.getContext(), htmlWriter);
				} catch (TemplateException e) {
					throw new MailPreparationException("Can't generate email body.", e);
				}

				message.setText(textWriter.toString(), htmlWriter.toString());

			}
		};

		mailSender.send(preparator);
	}

}
