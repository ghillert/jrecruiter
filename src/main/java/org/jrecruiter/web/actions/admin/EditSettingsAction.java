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
package org.jrecruiter.web.actions.admin;

import java.util.GregorianCalendar;

import org.apache.struts2.convention.annotation.Result;
import org.jrecruiter.model.Configuration;
import org.jrecruiter.web.actions.BaseAction;
import org.jrecruiter.web.forms.ConfigurationForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * List all the jobs.
 *
 * @author Gunnar Hillert
 *
 */
@Result(name="success", location="index", type="redirectAction")
public class EditSettingsAction extends BaseAction implements Preparable, ModelDriven<ConfigurationForm>  {

	/** serialVersionUID */
	private static final long serialVersionUID = -2725680709601753520L;

	/**
	 * Logger Declaration.
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(EditSettingsAction.class);

	ConfigurationForm form = new ConfigurationForm();

	public void prepare() throws Exception { }

	public String execute() {

		final Configuration mailingListSubject = jobService.getJRecruiterSetting("mail.jobposting.subject");
		final Configuration mailingListEmail   = jobService.getJRecruiterSetting("mail.jobposting.email");
		final Configuration mailingListFrom    = jobService.getJRecruiterSetting("mail.from");

		if (mailingListSubject != null) {
			form.setMailingListSubject(mailingListSubject.getMessageText());
		} else {
			form.setMailingListSubject("");
		}

		if (mailingListEmail != null) {
			form.setMailingListEmail(mailingListEmail.getMessageText());
		} else {
			form.setMailingListEmail("");
		}

		if (mailingListFrom != null) {
			form.setMailFrom(mailingListFrom.getMessageText());
		} else {
			form.setMailFrom("");
		}

		return INPUT;
	}

	/**
	 *
	 */
	public String save() {

		LOGGER.debug("entering 'onSubmit' method...");

		Configuration configuration = new Configuration();
		configuration.setMessageKey("mail.jobposting.subject");
		configuration.setMessageText(form.getMailingListSubject());
		configuration.setLastModified(GregorianCalendar.getInstance().getTime());
		jobService.saveJRecruiterSetting(configuration);

		configuration = new Configuration();
		configuration.setMessageKey("mail.jobposting.email");
		configuration.setMessageText(form.getMailingListEmail());
		configuration.setLastModified(GregorianCalendar.getInstance().getTime());
		jobService.saveJRecruiterSetting(configuration);

		configuration = new Configuration();
		configuration.setMessageKey("mail.from");
		configuration.setMessageText(form.getMailFrom());
		configuration.setLastModified(GregorianCalendar.getInstance().getTime());
		jobService.saveJRecruiterSetting(configuration);

		super.addActionMessage(getText("class.admin.EditSettingsAction.success"));

		return SUCCESS;

	}

	public ConfigurationForm getModel() {
		return form;
	}

	public void setModel(ConfigurationForm form) {
		this.form = form;
	}
}
