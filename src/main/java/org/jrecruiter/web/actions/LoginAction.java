package org.jrecruiter.web.actions;

import java.util.Map;

import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.SessionAware;
import org.texturemedia.smarturls.Result;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Logs out from the application.
 *
 * @author Gunnar Hillert
 * @version $Id: LogOutController.java 128 2007-07-27 03:55:54Z ghillert $
 *
 */
@SuppressWarnings("unchecked")
public class LoginAction extends ActionSupport {

	/** serialVersionUID. */
	private static final long serialVersionUID = -7129460964433090813L;

	/**
	 * Logger Declaration.
	 */
    private final Log LOGGER = LogFactory.getLog(LoginAction.class);

	/**
     * Let's log out - Invalidate the session as well as the ACEGI security
     * context.
     */
    public String execute () {
        return SUCCESS;
    }

}
