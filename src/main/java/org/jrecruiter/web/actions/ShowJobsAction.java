package org.jrecruiter.web.actions;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.displaytag.properties.SortOrderEnum;
import org.jrecruiter.model.Job;
import org.jrecruiter.web.JobsForDisplaytagSorting;
import org.texturemedia.smarturls.ActionName;
import org.texturemedia.smarturls.ActionNames;
import org.texturemedia.smarturls.Result;
import org.texturemedia.smarturls.Results;

/**
 * Show a list of jobs.
 *
 * @author Gunnar Hillert
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
@Results(
		{
			@Result(name="ajax",    location="/WEB-INF/jsp/jobsTable.jsp"),
			@Result(name="success", location="/WEB-INF/jsp/show-jobs.jsp")
		}
	)
@ActionNames({
  @ActionName(name="show-jobs"),
  @ActionName(name="show-jobs-ajax")
})
public class ShowJobsAction extends BaseAction {


	/** serialVersionUID. */
	private static final long serialVersionUID = 369806210598096582L;

	/**
	 * Logger Declaration.
	 */
    private final Log LOGGER = LogFactory.getLog(ShowJobsAction.class);

    private String dir;
    private String sort;
    private Integer page                            = 1;
    private String displayAjax;
    private JobsForDisplaytagSorting jobs           = new JobsForDisplaytagSorting();

    /* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {

    if (this.sort != null) {
    	jobs.setSortCriterion(sort);
    }
    if (this.dir != null) {

        if (dir.equalsIgnoreCase("ASC")) {
        	jobs.setSortOrder(SortOrderEnum.ASCENDING);
        } else if (dir.equalsIgnoreCase("DESC")) {
        	jobs.setSortOrder(SortOrderEnum.DESCENDING);
        }

    }

    if (this.page != null) {
    	jobs.setPageNumber(page);
    }

    jobs.setFullListSize(jobService.getJobsCount());

    LOGGER.info("Retrieving all jobs - "
                    + ";Total Size: " + jobs.getFullListSize()
                    + ";Results per Page: " + jobs.getObjectsPerPage()
                    + ";Page: " + jobs.getPageNumber()
                    + ";Sort Field: " + jobs.getSortCriterion()
                    + ";Direction: " + dir);

    final List < Job > jobList = jobService.getJobs(
    		                    jobs.getObjectsPerPage(),
    		                    jobs.getPageNumber(),
    		                    jobs.getSortCriterion(),
                                dir);
    jobs.setJobs(jobList);

    if (displayAjax != null && displayAjax.equalsIgnoreCase("true")) {
    	return "ajax";
    }

    return SUCCESS;
    }

	//~~~~~Getters and Setters~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getDisplayAjax() {
		return displayAjax;
	}

	public void setDisplayAjax(String displayAjax) {
		this.displayAjax = displayAjax;
	}

	public JobsForDisplaytagSorting getJobs() {
		return jobs;
	}

	public void setJobs(JobsForDisplaytagSorting jobs) {
		this.jobs = jobs;
	}


}
