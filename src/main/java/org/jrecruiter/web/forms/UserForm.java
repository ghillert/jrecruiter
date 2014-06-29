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
package org.jrecruiter.web.forms;

import org.jrecruiter.model.User;

/**
 * The user form extends the the user. This is necessary on some
 * forms the user must enter the password twice.
 *
 * @author  Gunnar Hillert
 *
 */
public class UserForm extends User {

	/** serialVersionUID. */
	private static final long serialVersionUID = -2648285589713561993L;

	private String password2;

	/**
	 *  Constructor.
	 */
	public UserForm() {
	}

	/**
	 * @return the password2
	 */
	public String getPassword2() {
		return password2;
	}

	/**
	 * @param password2 the password2 to set
	 */
	public void setPassword2(String password2) {
		this.password2 = password2;
	}

}
