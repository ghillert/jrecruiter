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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Servlet Filter implementation class LoggingFilter4Logback
 */
public class LoggingFilter4Logback implements Filter {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoggingFilter4Logback.class);

    public void destroy() {
    }

    public void doFilter(final ServletRequest req,
            final ServletResponse res,
            final FilterChain chain) throws IOException, ServletException {

        final HttpServletResponse response = (HttpServletResponse) res;
        final HttpServletRequest  request  = (HttpServletRequest) req;

        ErrorAwareHttpServletResponseWrapper errorAwareResponse = new ErrorAwareHttpServletResponseWrapper(response);


        final String username;

        if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) {
            username = SecurityContextHolder.getContext().getAuthentication().getName();
        } else {
            username = null;
        }

        final String sessionId    = request.getSession().getId();
        final String requestUrl   = request.getRequestURL().toString();

        MDC.put("username",   username);
        MDC.put("sessionId",  sessionId);
        MDC.put("requestUrl", requestUrl);

        try {

            chain.doFilter(request, errorAwareResponse);

            if (HttpServletResponse.SC_NOT_FOUND == errorAwareResponse.getErrorCode()) {
                 LOGGER.error("Page '" + request.getServletPath() + "' was not found.");
            }


        } finally {
            MDC.remove("username");
            MDC.remove("sessionId");
            MDC.remove("requestUrl");

        }
    }

    public void init(FilterConfig arg0) throws ServletException {
    }

}