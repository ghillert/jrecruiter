package org.jrecruiter.web.actions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jrecruiter.web.interceptor.RetrieveMessages;

/**
 * Logs out from the application.
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
public class IndexAction extends BaseAction {

	/** serialVersionUID. */
	private static final long serialVersionUID = -7129460964433090813L;

	/**
	 * Logger Declaration.
	 */
    private final Log LOGGER = LogFactory.getLog(IndexAction.class);

	/**
     * Let's log out - Invalidate the session as well as the ACEGI security
     * context.
     */
    @RetrieveMessages
    public String execute () {
        return SUCCESS;
    }

}
