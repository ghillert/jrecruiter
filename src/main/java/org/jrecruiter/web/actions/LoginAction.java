package org.jrecruiter.web.actions;

import org.jrecruiter.web.interceptor.RetrieveMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logs out from the application.
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
@SuppressWarnings("unchecked")
public class LoginAction extends BaseAction {

    /** serialVersionUID. */
    private static final long serialVersionUID = -7129460964433090813L;

    /**
     * Logger Declaration.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(LoginAction.class);

    private String status;

    /**
     * Let's log out - Invalidate the session as well as the ACEGI security
     * context.
     */
    @RetrieveMessages
    public String execute () {
        return SUCCESS;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
