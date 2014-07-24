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
package org.jrecruiter.web.actions.registration;

import org.apache.struts2.convention.annotation.Result;
import org.jrecruiter.model.User;
import org.jrecruiter.service.UserService;
import org.jrecruiter.web.actions.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Retrieves all jobs and returns an XML document. The structure conforms to the layout
 * defined by Indeed.com
 *
 * @author Gunnar Hillert
 */
@Controller
@Result(name="success", location="index", type="redirectAction", params={"namespace", ""})
public class AccountVerificationAction extends BaseAction {

	private @Autowired UserService userService;

	private String key;

	/** serialVersionUID. */
	private static final long serialVersionUID = -3422780336408883930L;

	public String execute() {

		if (this.key != null && this.key.length() != 0) {
			return process();
		}

		return INPUT;

	}

	public String process() {

		final User userFromDb = userService.getUserByVerificationKey(this.key);

		if (userFromDb == null) {
			super.addFieldError("key", super.getText("class.AccountVerificationAction.field.required.key"));
			return INPUT;
		}

		if (userFromDb.isEnabled()) {
			super.addActionMessage(super.getText("class.AccountVerificationAction.already.enabled"));
		} else {
			userFromDb.setEnabled(Boolean.TRUE);
			userService.updateUser(userFromDb);
			super.addActionMessage(super.getText("class.AccountVerificationAction.success"));
		}

		return SUCCESS;
	}

	public void validate() {
		if (this.key == null || this.key.trim().length() == 0) {
			super.addFieldError("key", super.getText("class.AccountVerificationAction.field.required.key"));
		}
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
