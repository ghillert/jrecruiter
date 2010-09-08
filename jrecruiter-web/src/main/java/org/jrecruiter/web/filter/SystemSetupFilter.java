package org.jrecruiter.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.jrecruiter.common.ApiKeysHolder;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 *
 * @author Gunnnar Hillert
 * @version $Id$
 */
public class SystemSetupFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpServletResponse httpResponse = (HttpServletResponse) response;

		if (httpRequest.getSession().getServletContext().getAttribute("areApiKeysSetup") == null) {
			httpResponse.sendRedirect("/s/set-api-keys");
		} else {

			final Boolean areApiKeysSetup = (Boolean) httpRequest.getSession().getServletContext().getAttribute("areApiKeysSetup");

			if (!areApiKeysSetup) {
				httpResponse.sendRedirect("/s/set-api-keys");
			}

		}
	}

	@Override
	public void init(FilterConfig filteConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
