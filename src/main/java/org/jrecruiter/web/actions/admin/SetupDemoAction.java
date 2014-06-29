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
import org.jrecruiter.service.SystemSetupService;
import org.jrecruiter.service.UserService;
import org.jrecruiter.web.actions.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Gunnar Hillert
 */

@Result(name="success", location="index", type="redirectAction")
public class SetupDemoAction extends BaseAction {

	/** serialVersionUID. */
	private static final long serialVersionUID = -3422780336408883930L;

	private transient @Autowired SystemSetupService systemSetupService;
	private transient @Autowired UserService userService;

	public String execute() {

		final User user = userService.getUser(1L);
		systemSetupService.createDemoJobs(user, 300);

		addActionMessage("300 demo jobs have been created.");
		return SUCCESS;
	}

}
