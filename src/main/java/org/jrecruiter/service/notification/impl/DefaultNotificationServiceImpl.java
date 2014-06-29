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

import static com.rosaloves.bitlyj.Bitly.as;
import static com.rosaloves.bitlyj.Bitly.shorten;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.jrecruiter.common.ApiKeysHolder;
import org.jrecruiter.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rosaloves.bitlyj.Url;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author Gunnar Hillert
 */
@Service("notificationService")
@Transactional
public class DefaultNotificationServiceImpl implements NotificationService {

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
	@Override
	public void sendEmail(final String email, final String subject, final Map context, final String templateName) {

		final MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws MessagingException, IOException {

				final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				message.setFrom("no_reply@jrecruiter.org");
				message.setTo(email);
				message.setSubject(subject);

				final Locale locale = LocaleContextHolder.getLocale();

				final Template textTemplate = freemarkerConfiguration.getTemplate(templateName + "-text.ftl", locale);

				final StringWriter textWriter = new StringWriter();
				try {
					textTemplate.process(context, textWriter);
				} catch (TemplateException e) {
					throw new MailPreparationException("Can't generate email body.", e);
				}

				final Template htmlTemplate = freemarkerConfiguration.getTemplate(templateName + "-html.ftl", locale);

				final StringWriter htmlWriter = new StringWriter();
				try {
					htmlTemplate.process(context, htmlWriter);
				} catch (TemplateException e) {
					throw new MailPreparationException("Can't generate email body.", e);
				}

				message.setText(textWriter.toString(), htmlWriter.toString());

			}
		};

		mailSender.send(preparator);
	}

	/** {@inheritDoc} */
	@Override
	public void sendTweetToTwitter(final String tweet) {
		//not used
	}

	/** {@inheritDoc} */
	@Override
	public URI shortenUrl(final String urlAsString) {

		//FIXME Handle this better
		Url url = as(apiKeysHolder.getBitlyUsername(),
					apiKeysHolder.getBitlyPassword())
				.call(shorten(urlAsString));
		try {
			return new URI(url.getShortUrl());
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException(
					String.format("Please provide a valid URI - %s is not valid", urlAsString));
		}

	}

}
