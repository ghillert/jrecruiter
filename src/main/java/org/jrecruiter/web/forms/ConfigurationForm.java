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
package org.jrecruiter.web.forms;

import java.io.Serializable;

/**
 * @author Gunnar Hillert
 *
 */
public class ConfigurationForm implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 7593027618105623364L;

	private String mailingListSubject;
	private String mailingListTemplate;
	private String mailingListEmail;
	private String mailFrom;
	private String passwordSubject;
	private String passwordTemplate;

	/**
	 * @return the mailFrom
	 */
	public String getMailFrom() {
		return mailFrom;
	}
	/**
	 * @param mailFrom the mailFrom to set
	 */
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}
	/**
	 * @return the mailingListEmail
	 */
	public String getMailingListEmail() {
		return mailingListEmail;
	}
	/**
	 * @param mailingListEmail the mailingListEmail to set
	 */
	public void setMailingListEmail(String mailingListEmail) {
		this.mailingListEmail = mailingListEmail;
	}
	/**
	 * @return the mailingListSubject
	 */
	public String getMailingListSubject() {
		return mailingListSubject;
	}
	/**
	 * @param mailingListSubject the mailingListSubject to set
	 */
	public void setMailingListSubject(String mailingListSubject) {
		this.mailingListSubject = mailingListSubject;
	}
	/**
	 * @return the mailingListTemplate
	 */
	public String getMailingListTemplate() {
		return mailingListTemplate;
	}
	/**
	 * @param mailingListTemplate the mailingListTemplate to set
	 */
	public void setMailingListTemplate(String mailingListTemplate) {
		this.mailingListTemplate = mailingListTemplate;
	}
	/**
	 * @return the passwordSubject
	 */
	public String getPasswordSubject() {
		return passwordSubject;
	}
	/**
	 * @param passwordSubject the passwordSubject to set
	 */
	public void setPasswordSubject(String passwordSubject) {
		this.passwordSubject = passwordSubject;
	}
	/**
	 * @return the passwordTemplate
	 */
	public String getPasswordTemplate() {
		return passwordTemplate;
	}
	/**
	 * @param passwordTemplate the passwordTemplate to set
	 */
	public void setPasswordTemplate(String passwordTemplate) {
		this.passwordTemplate = passwordTemplate;
	}





}
