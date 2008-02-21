package org.jrecruiter.web.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.jrecruiter.model.Job;

/**
 * List all the jobs.
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
public class SearchAction extends BaseAction  {

	/** serialVersionUID. */
	private static final long serialVersionUID = -6920008555687729003L;

	/**
	 * Logger Declaration.
	 */
    private final Log LOGGER = LogFactory.getLog(SearchAction.class);

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
