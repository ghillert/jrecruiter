/**
 * 
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
