package org.jrecruiter.web.actions.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jrecruiter.web.actions.BaseAction;
import org.jrecruiter.web.interceptor.RetrieveMessages;

/**
 * List all the jobs.
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
public class IndexAction extends BaseAction {

    /** serialVersionUID. */
    private static final long serialVersionUID = 4614516114027504626L;

    /**
     * Logger Declaration.
     */
    private final Log LOGGER = LogFactory.getLog(IndexAction.class);

    /**
     *
     */
    @Override
    @RetrieveMessages
    public String execute() {
            super.getLoggedInUser();
            return SUCCESS;
    }
}
