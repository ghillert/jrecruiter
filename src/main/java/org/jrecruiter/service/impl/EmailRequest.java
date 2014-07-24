package org.jrecruiter.service.impl;

import java.util.Map;

public class EmailRequest {

	private final String email;
	private final String subject;
	private final Map<String, Object> context;
	private final String templatePrefix;
	
	public EmailRequest(String email, String subject,
			Map<String, Object> context, String templatePrefix) {
		super();
		this.email = email;
		this.subject = subject;
		this.context = context;
		this.templatePrefix = templatePrefix;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @return the context
	 */
	public Map<String, Object> getContext() {
		return context;
	}

	/**
	 * @return the templatePrefix
	 */
	public String getTemplatePrefix() {
		return templatePrefix;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmailRequest [email=" + email + ", subject=" + subject
				+ ", context=" + context + ", templatePrefix=" + templatePrefix
				+ "]";
	}
	
}
