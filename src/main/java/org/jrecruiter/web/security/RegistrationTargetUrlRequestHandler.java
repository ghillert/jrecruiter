/**
 * 
 */
package org.jrecruiter.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;

/**
 * @author hillert
 *
 */
public class RegistrationTargetUrlRequestHandler extends
		AbstractAuthenticationTargetUrlRequestHandler {

	@Override
	protected void handle(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		super.handle(request, response, authentication);
	}

}
