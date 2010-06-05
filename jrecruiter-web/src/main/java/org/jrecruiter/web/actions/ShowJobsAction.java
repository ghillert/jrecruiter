package org.jrecruiter.web.actions;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.jmesa.facade.TableFacade;
import org.jmesa.facade.TableFacadeFactory;
import org.jmesa.limit.Filter;
import org.jmesa.limit.FilterSet;
import org.jmesa.limit.Limit;
import org.jmesa.limit.Sort;
import org.jmesa.limit.SortSet;
import org.jrecruiter.common.CollectionUtils;
import org.jrecruiter.model.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

public class ShowJobsAction extends BaseAction implements ServletRequestAware {


    /** serialVersionUID. */
    private static final long serialVersionUID = 369806210598096582L;

    /**
     * Logger Declaration.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(ShowJobsAction.class);

    private transient HttpServletRequest request;

    private List<Job> jobs           = CollectionUtils.getArrayList();
    private Limit limit;
    private boolean ajax = false;

    /* (non-Javadoc)
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    @Override
    @Actions({
          @Action("show-jobs"),
          @Action("show-jobs-ajax")
        })
    public String execute() throws Exception {

        final TableFacade tableFacade = TableFacadeFactory.createTableFacade("jobsTable", request);
        tableFacade.setStateAttr("restore");
        limit                     = tableFacade.getLimit();
        final FilterSet filterSet = limit.getFilterSet();
        final SortSet sortSet     = limit.getSortSet();


        final int totalRows = jobService.getJobsCount().intValue();
        tableFacade.setTotalRows(totalRows);

        int page     = limit.getRowSelect().getPage();
        int maxRows  = limit.getRowSelect().getMaxRows();

        final Map<String, String> sortOrders = CollectionUtils.getHashMap();
        final Map<String, String> jobFilters = CollectionUtils.getHashMap();

        if (sortSet.isSorted()) {
            for (Sort sort : sortSet.getSorts()) {
                sortOrders.put(sort.getProperty(), sort.getOrder().name());
            }
        }

        if (sortOrders.isEmpty()) {
            sortOrders.put("updateDate", "DESC");
        }

        if (filterSet.isFiltered()) {
            for (Filter filter : filterSet.getFilters()) {
                jobFilters.put(filter.getProperty(), filter.getValue());
            }
        }

        LOGGER.info("Retrieving all jobs - "
                        + ";Total Size: " + totalRows
                        + ";Results per Page: " + maxRows
                        + ";Page: " + page);

        this.jobs = jobService.getJobs(maxRows, page, sortOrders, jobFilters);

        if (this.ajax) {
            return "ajax";
        }

        return SUCCESS;
    }

    //~~~~~Getters and Setters~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public Limit getLimit() {
        return limit;
    }

    public void setLimit(Limit limit) {
        this.limit = limit;
    }

    public boolean isAjax() {
        return ajax;
    }

    public void setAjax(boolean ajax) {
        this.ajax = ajax;
    }

}
