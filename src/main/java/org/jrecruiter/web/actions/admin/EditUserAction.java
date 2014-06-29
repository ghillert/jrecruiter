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

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.jasypt.digest.StringDigester;
import org.jrecruiter.model.User;
import org.jrecruiter.web.actions.BaseAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Gunnar Hillert
 */
@Result(name="success", location="show-users", type="redirectAction")
public class EditUserAction extends BaseAction {

	private User user;
	private String password;
	private String password2;
	private boolean changePassword;
	private @Autowired StringDigester stringDigester;

	private Long userId;

	/** serialVersionUID. */
	private static final long serialVersionUID = -3422780336408883930L;
	private final static Logger LOGGER = LoggerFactory.getLogger(EditUserAction.class);

	public void validateSave() {

		if (changePassword) {

			if (password != null && password2 != null) {
				if (!password.trim().equals(password2.trim())) {
					addFieldError("password2", "The passwords do not match.");
				}
			} else {
				if (StringUtils.isBlank(this.password)) {
					super.addFieldError("password", "Please enter a password.");
				}
				if (StringUtils.isBlank(this.password2)) {
					super.addFieldError("password", "Please re-type the password.");
				}
			}
		}
	}
	/**
	 * Save the user.
	 * @return
	 */
	public String save() {

		final User userFromDb = userService.getUser(this.user.getId());

		if (userFromDb == null) {
			throw new IllegalStateException(
					"User with id " + user.getId()
				  + " not found but was expected to exist");
		}

		//Let's check whether a username with for a different user id
		//already exists in the system.
		final User duplicateUserFromDb = userService.getUser(user.getUsername());

		if (duplicateUserFromDb != null && !userFromDb.getId().equals(duplicateUserFromDb.getId())) {
			LOGGER.warn("Duplicate user name ("+ user.getUsername() +") for id "
					+ userFromDb.getId());
			addFieldError("username", getText("error.duplicateUsername"));
			return INPUT;
		}

		if (!StringUtils.isBlank(this.password)) {
			userFromDb.setPassword(this.stringDigester.digest(this.password));
		} else {
			addActionMessage("Your password has not been changed.");
		}

		userFromDb.setCompany(this.user.getCompany());
		userFromDb.setEmail(this.user.getEmail());
		userFromDb.setUsername(this.user.getEmail());

		userFromDb.setFax(this.user.getFax());
		userFromDb.setFirstName(this.user.getFirstName());
		userFromDb.setLastName(this.user.getLastName());
		userFromDb.setPhone(this.user.getPhone());

		userService.updateUser(userFromDb);

		addActionMessage(getText("class.EditUserAcion.success"));

		User securityContextUser =
			(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		securityContextUser.setCompany(this.user.getCompany());
		securityContextUser.setEmail(this.user.getEmail());
		securityContextUser.setFax(this.user.getFax());
		securityContextUser.setFirstName(this.user.getFirstName());
		securityContextUser.setLastName(this.user.getLastName());
		securityContextUser.setPassword(this.user.getPassword());
		securityContextUser.setPhone(this.user.getPhone());
		securityContextUser.setUsername(this.user.getUsername());

		return SUCCESS;
	}

	/**
	 * Initialize the edit user form with the registration information of the
	 * currently logged in user.
	 */
	public String execute() {

		if (this.userId != null) {
			this.user = userService.getUser(this.userId);
		} else {
			this.user = super.getLoggedInUser();
		}
		return INPUT;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public boolean isChangePassword() {
		return changePassword;
	}

	public void setChangePassword(boolean changePassword) {
		this.changePassword = changePassword;
	}


}
