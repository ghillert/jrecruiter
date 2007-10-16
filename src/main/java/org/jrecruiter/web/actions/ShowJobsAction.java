package org.jrecruiter.web.actions;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.displaytag.properties.SortOrderEnum;
import org.jrecruiter.model.Job;
import org.jrecruiter.web.JobsForDisplaytagSorting;

/**
 * @author Gunnar Hillert
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
public class ShowJobsAction extends BaseAction {


	/** serialVersionUID. */
	private static final long serialVersionUID = 369806210598096582L;

	/**
	 * Logger Declaration.
	 */
    private final Log LOGGER = LogFactory.getLog(ShowJobsAction.class);

    /* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {

    final JobsForDisplaytagSorting jobsDisplaytag = new JobsForDisplaytagSorting();

    String sortDirection = null;
    String sortField = null;
    Integer page = 1;

    if (ServletActionContext.getRequest().getParameter("sort") != null) {
        sortField = ServletActionContext.getRequest().getParameter("sort");
        jobsDisplaytag.setSortCriterion(sortField);
    }
    if (ServletActionContext.getRequest().getParameter("dir") != null) {
        sortDirection = ServletActionContext.getRequest().getParameter("dir");

        if (sortDirection.equalsIgnoreCase("ASC")) {
            jobsDisplaytag.setSortOrder(SortOrderEnum.ASCENDING);
        } else if (sortDirection.equalsIgnoreCase("DESC")) {
            jobsDisplaytag.setSortOrder(SortOrderEnum.DESCENDING);
        }

    }
    if (ServletActionContext.getRequest().getParameter("page") != null) {
        page = new Integer(ServletActionContext.getRequest().getParameter("page"));
        jobsDisplaytag.setPageNumber(page);
    }

    ServletActionContext.getRequest().getParameterNames();
    jobsDisplaytag.setFullListSize(jobService.getJobsCount());

    LOGGER.info("Retrieving all jobs - "
                    + ";Total Size: " + jobsDisplaytag.getFullListSize()
                    + ";Results per Page: " + jobsDisplaytag.getObjectsPerPage()
                    + ";Page: " + jobsDisplaytag.getPageNumber()
                    + ";Sort Field: " + jobsDisplaytag.getSortCriterion()
                    + ";Direction: " + sortDirection);

    final List < Job > jobs = jobService.getJobs(
    						    jobsDisplaytag.getObjectsPerPage(),
                                jobsDisplaytag.getPageNumber(),
                                jobsDisplaytag.getSortCriterion(),
                                sortDirection);
    jobsDisplaytag.setJobs(jobs);
    ServletActionContext.getRequest().setAttribute("JobList", jobsDisplaytag);

    final String ajaxCall = ServletActionContext.getRequest().getParameter("displayAjax");

    if (ajaxCall != null && ajaxCall.equalsIgnoreCase("true")) {
    	return "ajax";
    }

    ServletActionContext.getRequest().setAttribute("JobList", jobsDisplaytag);
    return SUCCESS;
    }
}
