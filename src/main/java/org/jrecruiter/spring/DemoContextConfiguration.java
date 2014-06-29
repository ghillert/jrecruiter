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

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.subethamail.smtp.TooMuchDataException;
import org.subethamail.smtp.helper.SimpleMessageListener;
import org.subethamail.smtp.helper.SimpleMessageListenerAdapter;
import org.subethamail.smtp.server.SMTPServer;

/**
 *
 * FIXME Need to refactor this class
 *
 * @author Gunnar Hillert
 *
 */
@Configuration
public class DemoContextConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(DemoContextConfiguration.class);

//    public @Bean(destroyMethod="stop") @Scope(value="singleton") Server startEmbeddedDatabase() throws SQLException {

	 //   Server database = org.h2.tools.Server.createTcpServer("-tcp,-tcpAllowOthers,true,-tcpPort,8043");
	 //   database.start();
	 //   return database;

//  }

//  public @Bean(destroyMethod="stop") @Scope(value="singleton") Server startH2WebServer() throws SQLException {

//        Server webserver = org.h2.tools.Server.createWebServer("-web,-webPort,8082 -trace");
//      webserver.start();webserver.isRunning(true);
	//    return webserver;

//    }

	public @Bean @Scope(value="singleton") SMTPServer startSMTPServer() {
		final SMTPServer smtpServer = new SMTPServer(new SimpleMessageListenerAdapter(
				new SimpleMessageListener() {

					@Override
					public void deliver(String from, String recipient, InputStream data)
							throws TooMuchDataException, IOException {

						LOGGER.info("From:" + from + "; To: " + recipient);

						ByteArrayOutputStream out = new ByteArrayOutputStream();
						data = new BufferedInputStream(data);

						// read the data from the stream
						int current;
						while ((current = data.read()) >= 0) {
								out.write(current);
						}

						byte[] bytes = out.toByteArray();

						try {
						MimeMessage message = new MimeMessage(Session.getDefaultInstance(new Properties()), new ByteArrayInputStream(bytes));

						MimeMultipart mm = (MimeMultipart) message.getContent();
						mm.getBodyPart(0);
						mm.getBodyPart(1);

						LOGGER.info(message.getContent().toString());

						} catch (MessagingException e) {
							e.printStackTrace();
						}



					}

					@Override
					public boolean accept(String from, String recipient) {
						// TODO Auto-generated method stub
						return true;
					}
				}
		));
		System.out.println("Starting Wiser...");
		smtpServer.setPort(2525);
		smtpServer.start();

		return smtpServer;
	}

}
