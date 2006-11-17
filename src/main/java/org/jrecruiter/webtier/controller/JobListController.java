package org.jrecruiter.webtier.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.displaytag.properties.SortOrderEnum;
import org.jrecruiter.model.Job;
import org.jrecruiter.service.JobServiceInterface;
import org.jrecruiter.webtier.JobsForDisplaytagSorting;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * List all the jobs. 
 * 
 * @author Gunnar Hillert
 * @version $Id: JobListAction.java 58 2006-10-16 03:45:45Z ghillert $
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
    private JobServiceInterface service;

    /**
     * Success View
     */
    private String successView;
    
    /**
     * Ajax View 
     */
    private String ajaxView;
    
    /**
     * Inject the service layer reference.
     * @param service 
     */
    public void setService(JobServiceInterface service) {
		this.service = service;
	}

    
    
    /**
	 * @param ajaxView the ajaxView to set
	 */
	public void setAjaxView(String ajaxView) {
		this.ajaxView = ajaxView;
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
}
