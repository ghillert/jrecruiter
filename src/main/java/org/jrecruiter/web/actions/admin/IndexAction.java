package org.jrecruiter.web.actions.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.config.Namespace;
import org.jrecruiter.web.actions.BaseAction;

import com.opensymphony.xwork2.ActionSupport;

/**
 * List all the jobs.
 *
 * @author Gunnar Hillert
 * @version $Id: AddJobFormController.java 128 2007-07-27 03:55:54Z ghillert $
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
    public String execute() {
            return SUCCESS;
	}
}
