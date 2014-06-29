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
package org.jrecruiter.web.actions;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.jrecruiter.common.AcegiUtil;
import org.jrecruiter.model.User;
import org.jrecruiter.service.JobService;
import org.jrecruiter.service.UserService;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Gunnar Hillert
 */
public abstract class BaseAction extends ActionSupport {

	/** serialVersionUID. */
	private static final long serialVersionUID = -2918623405168805282L;

	//~~~~~Services~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * User related services.
	 */
	protected UserService userService;
	protected MessageSource messageSource;

	private String backTo;

	/**
	 * Job related services.
	 */
	protected JobService jobService;


	//~~~~~Getters and Setters~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}

	@SkipValidation
	public String cancel() {

		if (this.backTo != null) {
			return "backTo";
		}

		return SUCCESS;
	}

	@Override
	public String getText(final String code) {
		return this.messageSource.getMessage(code, null, super.getLocale());
	}

	//~~~~~Security Methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	protected SecurityContext getSecurityContext() {
		return SecurityContextHolder.getContext();
	}

	protected Authentication getAuthentication() {
		SecurityContext securityContext = getSecurityContext();
		if (securityContext != null) {
			return securityContext.getAuthentication();
		} else {
			return null;
		}
	}

	protected User getLoggedInUser() {
		Authentication authentication = getAuthentication();
		if (authentication != null) {
			return (User) authentication.getPrincipal();
		} else {
			return null;
		}
	}

	protected Boolean hasRole(String role) {
		User getLoggedInUser = getLoggedInUser();
		if (getLoggedInUser != null) {
			return AcegiUtil.hasRole(role);
		} else {
			return Boolean.FALSE;
		}
	}

	public String getBackTo() {
		return backTo;
	}

	public void setBackTo(String backTo) {
		this.backTo = backTo;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

}
