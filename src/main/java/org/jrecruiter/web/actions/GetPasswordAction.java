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

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.jrecruiter.common.Constants.UserAuthenticationType;
import org.jrecruiter.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.conversion.annotations.Conversion;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

/**
 * Resets the users passwords and emails it back to the user.
 *
 * @author Gunnar Hillert
 */
@Conversion
@Results({@Result(name="success", location="login", type="redirectAction")})
public class GetPasswordAction extends BaseAction {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetPasswordAction.class);

	private User user;

	/** serialVersionUID. */
	private static final long serialVersionUID = -3422780336408883930L;

	public String execute() {
		return INPUT;
	}

	@Validations(
			requiredStrings = {
						@RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.username", trim=true, key = "class.get-password.password_reset_not_possible", message="")
					 }
			)
	public String process() {

		final User userFromDb = userService.getUser(this.user.getUsername());

		if (userFromDb == null) {
			LOGGER.warn("Reset password attempt for non-existing user '" + this.user.getUsername() + "'");
			super.addActionError(super.getText("class.get-password.password_reset_not_possible"));
			return INPUT;
		}

		if (!userFromDb.isEnabled()) {
			LOGGER.warn("Reset password attempt for disabled user '" +  userFromDb.getUsername() + "' (Id: " + userFromDb.getId() + ")");
			super.addActionError(super.getText("class.get-password.password_reset_not_possible"));
			return INPUT;
		}

		if (!UserAuthenticationType.USERNAME_PASSWORD.equals(userFromDb.getUserAuthenticationType())) {
			LOGGER.warn("Reset password not supported for UserAuthenticationType: '" + userFromDb.getUserAuthenticationType().name() + "';"
					  + "User: '" +  userFromDb.getUsername() + "' (Id: " + userFromDb.getId() + ")");
			super.addActionError(super.getText("class.get-password.password_reset_not_possible"));
			return INPUT;
		}

		userService.resetPassword(userFromDb);

		super.addActionMessage(super.getText("class.get-password.success", new String[] {userFromDb.getEmail()}));
		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
