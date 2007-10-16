package org.jrecruiter.web.actions;

import org.jrecruiter.service.JobService;
import org.jrecruiter.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Gunnar Hillert
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
public abstract class BaseAction extends ActionSupport {

	//~~~~~Services~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * User related services.
     */
	protected UserService userService;

    /**
     * Job related services.
     */
	protected JobService jobService;

	//~~~~~Getters and Setters~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}

    public String cancel() {
        return SUCCESS;
    }
}
