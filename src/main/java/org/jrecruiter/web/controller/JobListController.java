package org.jrecruiter.web.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.displaytag.properties.SortOrderEnum;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.Statistic;
import org.jrecruiter.service.JobService;
import org.jrecruiter.web.JobsForDisplaytagSorting;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * List all the jobs.
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
public class JobListController extends MultiActionController  {

	/**
	 * Logger Declaration.
	 */
    private final Log LOGGER = LogFactory.getLog(JobListController.class);

    /**
     * The service layer reference.
     */
    private JobService service;

    /**
     * Success View
     */
    private String successView;

    /**
     * Success View for showing the job details
     */
    private String successViewShowDetails;

    /**
     * Ajax View
     */
    private String ajaxView;

    /**
     * Inject the service layer reference.
     * @param service
     */
    public void setService(JobService service) {
		this.service = service;
	}

    /**
	 * @param ajaxView the ajaxView to set
	 */
	public void setAjaxView(String ajaxView) {
		this.ajaxView = ajaxView;
	}

	public void setSuccessViewShowDetails(String successViewShowDetails) {
		this.successViewShowDetails = successViewShowDetails;
	}

	/**
	 * @param successView the successView to set
	 */
	public void setSuccessView(String successView) {
		this.successView = successView;
	}


    /**
     * Default view for this controller.
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ModelAndView
     * @throws Exception Let Exceptions bubble up
     */
    public final ModelAndView view(final HttpServletRequest request,
                                   final HttpServletResponse response)
            throws Exception {


	    final JobsForDisplaytagSorting jobsDisplaytag = new JobsForDisplaytagSorting();

	    String sortDirection = null;
	    String sortField = null;
	    Integer page = 1;

	    if (request.getParameter("sort") != null) {
	        sortField = request.getParameter("sort");
	        jobsDisplaytag.setSortCriterion(sortField);
	    }
	    if (request.getParameter("dir") != null) {
	        sortDirection = request.getParameter("dir");

	        if (sortDirection.equalsIgnoreCase("ASC")) {
	            jobsDisplaytag.setSortOrder(SortOrderEnum.ASCENDING);
	        } else if (sortDirection.equalsIgnoreCase("DESC")) {
	            jobsDisplaytag.setSortOrder(SortOrderEnum.DESCENDING);
	        }

	    }
	    if (request.getParameter("page") != null) {
	        page = new Integer(request.getParameter("page"));
	        jobsDisplaytag.setPageNumber(page);
	    }

	    request.getParameterNames();
	    jobsDisplaytag.setFullListSize(service.getJobsCount());

	    LOGGER.info("Retrieving all jobs - "
	                    + ";Total Size: " + jobsDisplaytag.getFullListSize()
	                    + ";Results per Page: " + jobsDisplaytag.getObjectsPerPage()
	                    + ";Page: " + jobsDisplaytag.getPageNumber()
	                    + ";Sort Field: " + jobsDisplaytag.getSortCriterion()
	                    + ";Direction: " + sortDirection);

	    final List < Job > jobs = service.getJobs(
	    						    jobsDisplaytag.getObjectsPerPage(),
	                                jobsDisplaytag.getPageNumber(),
	                                jobsDisplaytag.getSortCriterion(),
	                                sortDirection);
	    jobsDisplaytag.setJobs(jobs);

	    final String ajaxCall = request.getParameter("displayAjax");

	    if (ajaxCall != null && ajaxCall.equalsIgnoreCase("true")) {
	    	return new ModelAndView(ajaxView, "JobList", jobsDisplaytag);
	    }

	    request.setAttribute("JobList", jobsDisplaytag);
        return new ModelAndView(successView);
    }

    /**
     * Default view for this controller.
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ModelAndView
     * @throws Exception Let Exceptions bubble up
     */
    @SuppressWarnings("unchecked")
    public final ModelAndView details(final HttpServletRequest request,
                                   final HttpServletResponse response)
            throws Exception {

            final Long id = Long.valueOf(request.getParameter("id"));
            final Job jobDetail = service.getJobForId(id);

            if (jobDetail==null){

                request.getSession().setAttribute("message",
                        getText("error.jobposting.not.found", id.toString()));

                LOGGER.warn("Requested jobposting with id " + id + " was not found.");

                return new ModelAndView(successView);
            }

            Statistic statistics = jobDetail.getStatistic();

            if (statistics == null) {

                statistics = new Statistic();
                statistics.setJob(jobDetail);
                statistics.setCounter(new Long(0));
                statistics.setUniqueVisits(new Long(0));
                jobDetail.setStatistic(statistics);
            }

            Set < Long > viewedPostings = new HashSet < Long >();

            if (request.getSession().getAttribute("visited") != null){

                viewedPostings = (Set< Long >)request.getSession().getAttribute("visited");

                if (viewedPostings.contains(id)){


                } else {
                    long counter = statistics.getUniqueVisits().longValue() + 1 ;
                    statistics.setUniqueVisits(new Long (counter));
                    viewedPostings.add(id);
                }

            } else {

                long counter;

                if (statistics.getUniqueVisits() != null)
                {
                    counter = statistics.getUniqueVisits().longValue() + 1 ;
                } else {
                    counter = 1;
                }


                statistics.setUniqueVisits(new Long (counter));

                viewedPostings.add(id);
                request.getSession().setAttribute("visited", viewedPostings);

            }

            Long counter = statistics.getCounter().longValue();
            counter ++;

            statistics.setCounter(new Long(counter));
            statistics.setLastAccess(new Date());
            service.updateJob(jobDetail);

            request.setAttribute("jobDetail", jobDetail);

            return new ModelAndView(successViewShowDetails);
        }

    /**
     * Convenient method for getting a i18n key's value with a single
     * string argument.
     *
     * @param msgKey
     * @param arg
     * @return
     */
    public String getText(String msgKey, String arg) {
        return getText(msgKey, new Object[] { arg });
    }

    /**
     * Convenience method for getting a i18n key's value with arguments.
     *
     * @param msgKey
     * @param args
     * @return
     */
    public String getText(String msgKey, Object[] args) {
        return getMessageSourceAccessor().getMessage(msgKey, args);
    }

}
