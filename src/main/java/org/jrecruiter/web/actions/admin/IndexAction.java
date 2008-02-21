package org.jrecruiter.web.actions.admin;

import org.jrecruiter.web.actions.BaseAction;
import org.jrecruiter.web.interceptor.RetrieveMessages;

/**
 * Show the main index page of the admin screens.
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
public class IndexAction extends BaseAction {

    /** serialVersionUID. */
    private static final long serialVersionUID = 4614516114027504626L;

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
