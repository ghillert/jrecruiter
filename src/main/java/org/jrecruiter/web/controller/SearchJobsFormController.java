package org.jrecruiter.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jrecruiter.service.JobService;
import org.jrecruiter.web.forms.SearchForm;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

/**
 * List all the jobs. 
 * 
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
public class SearchJobsFormController extends BaseSimpleFormController  {
	
	/**
	 * Logger Declaration.
	 */
    private final Log LOGGER = LogFactory.getLog(SearchJobsFormController.class);
    
    /**
     * The service layer reference.
     */
    private JobService service;
    
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
    public ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command,
            BindException errors)
	throws Exception {
		LOGGER.debug("entering 'onSubmit' method...");

        final SearchForm form = (SearchForm) command;

        List jobs = service.searchByKeyword(form.getKeyword().toLowerCase());

        request.setAttribute("JobList", jobs);
        return new ModelAndView(getSuccessView());

	}

}
