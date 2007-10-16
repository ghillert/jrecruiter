package org.jrecruiter.web.actions.admin;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.PrincipalAware;
import org.apache.struts2.interceptor.PrincipalProxy;
import org.jrecruiter.Constants.JobStatus;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.User;
import org.jrecruiter.service.JobService;
import org.jrecruiter.web.actions.BaseAction;

import com.opensymphony.xwork2.ActionSupport;

/**
 * List all the jobs.
 *
 * @author Gunnar Hillert
 * @version $Id: AddJobFormController.java 128 2007-07-27 03:55:54Z ghillert $
 *
 */
public class AddJobAction extends BaseAction implements PrincipalAware {

	/** serialVersionUID. */
	private static final long serialVersionUID = 4614516114027504626L;

	/**
	 * Logger Declaration.
	 */
    private final Log LOGGER = LogFactory.getLog(AddJobAction.class);

    private Job job;

    PrincipalProxy principalProxy;

    /**
     *
     */
    @Override
    public String execute() {

    	    LOGGER.debug("Entering 'onSubmit' method - Adding Job.");

            //TODO better handling required.
    	    job = new Job();
            return SUCCESS;
	}

    public String cancel() {

        return SUCCESS;
    }

    public String save() {

	    LOGGER.debug("Adding Job...");

        job.setStatus(JobStatus.ACTIVE);
        job.setUser((User)principalProxy.getUserPrincipal());
        job.setRegistrationDate(new Date());
        job.setUpdateDate(new Date());

        jobService.addJob(job);
        jobService.sendJobPostingToMailingList(job);

        super.addActionMessage(getText("job.add.success"));

        return SUCCESS;
    }

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public PrincipalProxy getPrincipalProxy() {
		return principalProxy;
	}

	public void setPrincipalProxy(PrincipalProxy principalProxy) {
		this.principalProxy = principalProxy;
	}

}
