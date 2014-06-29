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


/**
 * Logs out from the application.
 *
 * @author Gunnar Hillert
 *
 */
public class LoginAction extends BaseAction {

	/** serialVersionUID. */
	private static final long serialVersionUID = -7129460964433090813L;

	private String status;

	/**
	 * Let's log out - Invalidate the session as well as the ACEGI security
	 * context.
	 */
	public String execute () {
		return SUCCESS;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
