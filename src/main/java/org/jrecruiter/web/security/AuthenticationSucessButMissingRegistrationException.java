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
package org.jrecruiter.web.security;

import org.springframework.security.core.AuthenticationException;

/**
 *
 * @author Gunnar Hillert
 *
 */
public class AuthenticationSucessButMissingRegistrationException extends
		AuthenticationException {

	/** serialVersionUID. */
	private static final long serialVersionUID = 4334367188877843649L;

	public AuthenticationSucessButMissingRegistrationException(String msg,
			Object extraInformation) {
		super(msg, extraInformation);
	}

	public AuthenticationSucessButMissingRegistrationException(String msg,
			Throwable t) {
		super(msg, t);
	}

	public AuthenticationSucessButMissingRegistrationException(String msg) {
		super(msg);
	}

}
