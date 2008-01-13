package org.jrecruiter.web.actions;

import java.util.Map;

import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.SessionAware;
import org.texturemedia.smarturls.Result;

/**
 * Logs out from the application.
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
@Result(name="success", location="/",  type="redirect")
@SuppressWarnings("unchecked")
public class LogoutAction extends BaseAction implements SessionAware  {

	/** serialVersionUID. */
	private static final long serialVersionUID = -7129460964433090813L;

	private Map<String, Object> session = null;

	public void setSession(final Map session) {
		this.session = session;
	}

	/**
	 * Logger Declaration.
	 */
    private final Log LOGGER = LogFactory.getLog(LogoutAction.class);

	/**
     * Let's log out - Invalidate the session as well as the ACEGI security
     * context.
     */
    public String execute () {

    	final SecurityContext context = SecurityContextHolder.getContext();

    	if (context.getAuthentication() != null) {
    		LOGGER.info("Logging out user..." + context.getAuthentication().getName());
    	} else {
    		LOGGER.warn("User not logged in.");
    	}
    	if (session instanceof org.apache.struts2.dispatcher.SessionMap) {
    	    try {
    	        ((org.apache.struts2.dispatcher.SessionMap) session).invalidate();
    	    } catch (IllegalStateException e) {
    	    	LOGGER.error("You are attempting to invalidate an already invalid session", e);
    	    }
    	}

        context.setAuthentication(null);

        return SUCCESS;

    }

}
