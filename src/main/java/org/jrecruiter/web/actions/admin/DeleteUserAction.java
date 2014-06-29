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

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.jrecruiter.common.AcegiUtil;
import org.jrecruiter.common.Constants.Roles;
import org.jrecruiter.model.User;
import org.jrecruiter.web.actions.BaseAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Deletes one or more users.
 *
 * @author Gunnar Hillert
 */
@Result(name="success", location="show-users", type="redirectAction")
public class DeleteUserAction extends BaseAction {

	/** serialVersionUID. */
	private static final long serialVersionUID = -2825056640805045453L;

	private List<Long>userIds;

	private final static Logger LOGGER = LoggerFactory.getLogger(DeleteUserAction.class);

	/**
	 * Initialize the edit user form with the registration information of the
	 * currently logged in user.
	 */
	public String execute() {

		if (this.userIds != null && !this.userIds.isEmpty()) {

			int validUserIds = 0;

			for (Long userId : userIds) {

				if (userId != null) {

					User user = userService.getUser(userId);

					if (AcegiUtil.containsRole(user.getAuthorities(), Roles.ADMIN.name())) {
						LOGGER.warn("User tried to delete admin user.");
						super.addActionMessage("Cannot delete admin user.");
					} else {
						validUserIds++;
						userService.deleteUser(user);
					}
				}
			}

			if (validUserIds == 1) {
				super.addActionMessage(validUserIds + " user was deleted.");
			} else {
				super.addActionMessage(validUserIds + " users were deleted.");
			}

		} else {
			super.addActionMessage("No User was deleted.");
		}

		return SUCCESS;
	}

	public List<Long> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<Long> userIds) {
		this.userIds = userIds;
	}

}
