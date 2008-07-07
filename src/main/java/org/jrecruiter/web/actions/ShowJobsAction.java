package org.jrecruiter.web.actions;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.SessionAware;
import org.displaytag.properties.SortOrderEnum;
import org.jrecruiter.model.Job;
import org.jrecruiter.web.DisplaytagPaginatedList;
import org.jrecruiter.web.interceptor.RetrieveMessages;
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
public class ShowJobsAction extends BaseAction implements SessionAware {


    /** serialVersionUID. */
    private static final long serialVersionUID = 369806210598096582L;

    /**
     * Logger Declaration.
     */
    private final Log LOGGER = LogFactory.getLog(ShowJobsAction.class);

    private String dir;
    private String sort;
    private Integer page;
    private String displayAjax;
    private DisplaytagPaginatedList<Job> jobs           = new DisplaytagPaginatedList<Job>();

    private Map<String, Object>session;

    @SuppressWarnings("unchecked")
    public void setSession(Map session) {
        this.session = session;
    }

    /* (non-Javadoc)
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    @SuppressWarnings("unchecked")
    @RetrieveMessages
    @Override
    public String execute() throws Exception {

    final DisplaytagPaginatedList<Job> state;

    if (session.get("jobsTableState") != null) {
        state = (DisplaytagPaginatedList<Job>)session.get("jobsTableState");
    } else {
        state = new DisplaytagPaginatedList<Job>();
        session.put("jobsTableState",state);
    }

    this.jobs.setPageNumber(state.getPageNumber());
    this.jobs.setSortCriterion(state.getSortCriterion());
    this.jobs.setSortOrder(state.getSortOrder());

    if (this.sort != null) {
        jobs.setSortCriterion(sort);
        state.setSortCriterion(sort);
    }

    if (this.dir != null) {

        if (dir.equalsIgnoreCase("ASC")) {
            jobs.setSortOrder(SortOrderEnum.ASCENDING);
            state.setSortOrder(SortOrderEnum.ASCENDING);
        } else if (dir.equalsIgnoreCase("DESC")) {
            jobs.setSortOrder(SortOrderEnum.DESCENDING);
            state.setSortOrder(SortOrderEnum.DESCENDING);
        }

    }

    if (this.page != null) {
        jobs.setPageNumber(page);
        state.setPageNumber(page);
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
    jobs.setRecords(jobList);

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

    public DisplaytagPaginatedList<Job> getJobs() {
        return jobs;
    }

    public void setJobs(DisplaytagPaginatedList<Job> jobs) {
        this.jobs = jobs;
    }


}
