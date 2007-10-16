package org.jrecruiter.web.actions.admin;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.PrincipalAware;
import org.apache.struts2.interceptor.PrincipalProxy;
import org.jrecruiter.model.Job;
import org.jrecruiter.service.JobService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * List all the jobs.
 *
 * @author Gunnar Hillert
 * @version $Id: ShowJobsFormController.java 131 2007-08-07 03:37:02Z ghillert $
 *
 */
public class ShowJobsAction extends ActionSupport implements Preparable, PrincipalAware {

	/** serialVersionUID. */
	private static final long serialVersionUID = -6536348867574805926L;

	/**
	 * Logger Declaration.
	 */
    private final Log LOGGER = LogFactory.getLog(ShowJobsAction.class);

    List<Job> jobs;

    Boolean displayAjax = Boolean.FALSE;

    PrincipalProxy principalProxy;

    /**
     * The service layer reference.
     */
    private JobService service;


    public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	/**
     * Inject the service layer reference.
     * @param service
     */
    public void setService(JobService service) {
		this.service = service;
	}

    /**
     *
     */
    public String execute() {
		return SUCCESS;
	}

    public String ajaxCall() {
    	jobs = service.
        getUsersJobs(principalProxy.getRemoteUser());

		if (displayAjax) {
		    return "ajax";
		}

		return null;
    }

    public void prepare() throws Exception {

    	ajaxCall();

	}

	public Boolean getDisplayAjax() {
		return displayAjax;
	}

	public void setDisplayAjax(Boolean displayAjax) {
		this.displayAjax = displayAjax;
	}

	public void setPrincipalProxy(PrincipalProxy principalProxy) {
		this.principalProxy = principalProxy;
	}
}
