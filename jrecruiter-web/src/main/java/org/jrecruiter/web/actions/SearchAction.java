package org.jrecruiter.web.actions;

import java.util.ArrayList;
import java.util.List;

import org.jrecruiter.model.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private final static Logger LOGGER = LoggerFactory.getLogger(SearchAction.class);

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
