package org.jrecruiter.web.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jrecruiter.model.Job;
import org.jrecruiter.service.JobService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * List all the jobs.
 *
 * @author Gunnar Hillert
 * @version $Id: SearchJobsFormController.java 131 2007-08-07 03:37:02Z ghillert $
 *
 */
public class SearchJobsAction extends BaseAction  {

	/** serialVersionUID. */
	private static final long serialVersionUID = -6920008555687729003L;

	/**
	 * Logger Declaration.
	 */
    private final Log LOGGER = LogFactory.getLog(SearchJobsAction.class);

    private String keyword;
    private List<Job>jobs = new ArrayList<Job>();

    public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

    /**
     *
     */
    public String search() {

		LOGGER.debug("entering 'onSubmit' method...");

        this.jobs = jobService.searchByKeyword(this.keyword.toLowerCase());

        return SUCCESS;

	}

}
