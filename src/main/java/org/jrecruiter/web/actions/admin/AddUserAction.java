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

import org.apache.struts2.convention.annotation.Result;
import org.jrecruiter.model.User;
import org.jrecruiter.service.exceptions.DuplicateUserException;
import org.jrecruiter.web.actions.BaseAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Gunnar Hillert
 */

@Result(name="success", location="index", type="redirectAction")
public class AddUserAction extends BaseAction {

	private User user;

	/** serialVersionUID. */
	private static final long serialVersionUID = -3422780336408883930L;
	private final static Logger LOGGER = LoggerFactory.getLogger(AddUserAction.class);

	public String save() {

		try {
		   //FIXME
		   userService.addUser(user);
		} catch (DuplicateUserException e) {

			LOGGER.warn(e.getMessage());
			  addFieldError("username", getText("error.duplicateUsername"));
			  return INPUT;
		}

		addActionMessage(getText("user.add.success", user.getUsername()));
		return SUCCESS;
	}

	public String execute() {
		return INPUT;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
