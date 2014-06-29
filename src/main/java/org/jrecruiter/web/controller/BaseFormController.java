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
package org.jrecruiter.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jrecruiter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.validation.Validator;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

/**
 * Implementation of <strong>SimpleFormController</strong> that contains
 * convenience methods for subclasses.  For example, getting the current
 * user and saving messages/errors. This class is intended to
 * be a base class for all Form controllers.
 *
 * <p><a href="BaseFormController.java.html"><i>View Source</i></a></p>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class BaseFormController implements ServletContextAware {
	public static final String MESSAGES_KEY = "successMessages";
	public static final String ERRORS_MESSAGES_KEY = "errors";
	protected final transient Log log = LogFactory.getLog(getClass());
	private UserService userService = null;

	protected String cancelView;
	protected String successView;

	private MessageSourceAccessor messages;
	private ServletContext servletContext;

	@Autowired(required = false)
	Validator validator;

	@Autowired
	public void setMessages(MessageSource messageSource) {
		messages = new MessageSourceAccessor(messageSource);
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserService getUserService() {
		return this.userService;
	}

	@SuppressWarnings("unchecked")
	public void saveError(HttpServletRequest request, String error) {
		List errors = (List) request.getSession().getAttribute(ERRORS_MESSAGES_KEY);
		if (errors == null) {
			errors = new ArrayList();
		}
		errors.add(error);
		request.getSession().setAttribute(ERRORS_MESSAGES_KEY, errors);
	}

	@SuppressWarnings("unchecked")
	public void saveMessage(HttpServletRequest request, String msg) {
		List messages = (List) request.getSession().getAttribute(MESSAGES_KEY);

		if (messages == null) {
			messages = new ArrayList();
		}

		messages.add(msg);
		request.getSession().setAttribute(MESSAGES_KEY, messages);
	}

	/**
	 * Convenience method for getting a i18n key's value.  Calling
	 * getMessageSourceAccessor() is used because the RequestContext variable
	 * is not set in unit tests b/c there's no DispatchServlet Request.
	 *
	 * @param msgKey
	 * @param locale the current locale
	 * @return
	 */
	public String getText(String msgKey, Locale locale) {
		return messages.getMessage(msgKey, locale);
	}

	/**
	 * Convenient method for getting a i18n key's value with a single
	 * string argument.
	 *
	 * @param msgKey
	 * @param arg
	 * @param locale the current locale
	 * @return
	 */
	public String getText(String msgKey, String arg, Locale locale) {
		return getText(msgKey, new Object[] { arg }, locale);
	}

	/**
	 * Convenience method for getting a i18n key's value with arguments.
	 *
	 * @param msgKey
	 * @param args
	 * @param locale the current locale
	 * @return
	 */
	public String getText(String msgKey, Object[] args, Locale locale) {
		return messages.getMessage(msgKey, args, locale);
	}

	/**
	 * Set up a custom property editor for converting form inputs to real objects
	 * @param request the current request
	 * @param binder the data binder
	 */
	@InitBinder
	protected void initBinder(HttpServletRequest request,
							ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Integer.class, null,
									new CustomNumberEditor(Integer.class, null, true));
		binder.registerCustomEditor(Long.class, null,
									new CustomNumberEditor(Long.class, null, true));
		binder.registerCustomEditor(byte[].class,
									new ByteArrayMultipartFileEditor());
		SimpleDateFormat dateFormat =
			new SimpleDateFormat(getText("date.format", request.getLocale()));
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, null,
									new CustomDateEditor(dateFormat, true));
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	protected ServletContext getServletContext() {
		return servletContext;
	}
}
