package org.jrecruiter.web.actions;

import org.jrecruiter.common.Constants.SpringContextMode;


/**
 * Controller for the first initial page of the application.
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
public class IndexAction extends BaseAction {

    /** serialVersionUID. */
    private static final long serialVersionUID = -7129460964433090813L;

    public SpringContextMode springContextMode;

    /**
     * Let's log out - Invalidate the session as well as the ACEGI security
     * context.
     */
    public String execute () {

        this.springContextMode = super.applicationConfiguration.getSpringContextMode();

        return SUCCESS;
    }

}
