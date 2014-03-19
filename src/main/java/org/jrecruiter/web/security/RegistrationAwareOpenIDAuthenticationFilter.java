/**
 * 
 */
package org.jrecruiter.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jrecruiter.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.openid.OpenIDAuthenticationFilter;

/**
 * @author hillert
 *
 */
public class RegistrationAwareOpenIDAuthenticationFilter extends
		OpenIDAuthenticationFilter {

	private RegistrationTargetUrlRequestHandler registrationTargetUrlRequestHandler;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException,
			IOException {
		
		try {
			return super.attemptAuthentication(request, response);
		} catch (AuthenticationSucessButMissingRegistrationException e) {
			
			final User user = (User) e.getExtraInformation();
			
			try {
		
			//TODO Need to handle this better
				
	        final HttpSession session = request.getSession(false);	
			
	        session.setAttribute("OpenIdUserObject", user);
	        
			registrationTargetUrlRequestHandler.handle(request, response, null);
			
			} catch (ServletException se) {
				throw new IllegalStateException(se);
			}
			
			return null;
		} 
	
	}

	public void setRegistrationTargetUrlRequestHandler(
			RegistrationTargetUrlRequestHandler registrationTargetUrlRequestHandler) {
		this.registrationTargetUrlRequestHandler = registrationTargetUrlRequestHandler;
	}



}
