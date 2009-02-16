package org.jrecruiter.web.actions;

import org.apache.struts2.convention.annotation.Result;
import org.jrecruiter.web.interceptor.StoreMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;

/**
 * Logs out from the application.
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
@Result(name="success", location="/",  type="redirect")
public class LogoutAction extends BaseAction {

    /** serialVersionUID. */
    private static final long serialVersionUID = -7129460964433090813L;

    /**
     * Logger Declaration.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(LogoutAction.class);

    /**
     * Let's log out - Invalidate the session as well as the ACEGI security
     * context.
     */
    @StoreMessages
    public String execute () {

        final SecurityContext context = SecurityContextHolder.getContext();

        if (context.getAuthentication() != null) {
            LOGGER.info("Logging out user..." + context.getAuthentication().getName());
        } else {
            LOGGER.warn("User not logged in.");
        }

        context.setAuthentication(null);

        super.addActionMessage("You logged out successfully.");
        return SUCCESS;

    }

}
