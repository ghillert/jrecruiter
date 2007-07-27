package org.jrecruiter.web.controller.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jrecruiter.service.JobService;
import org.jrecruiter.web.controller.BaseSimpleFormController;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * List all the jobs. 
 * 
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
public class ShowJobsFormController extends BaseSimpleFormController  {
	
	/**
	 * Logger Declaration.
	 */
    private final Log LOGGER = LogFactory.getLog(ShowJobsFormController.class);
    
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

		
		return new ModelAndView(getSuccessView());
	}

    
    /* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

            List jobs = service.
                    getUsersJobs(request.getRemoteUser());

            request.setAttribute("JobList", jobs);

            String ajaxCall = request.getParameter("displayAjax");
            
//            if (ajaxCall != null && ajaxCall.equalsIgnoreCase("true")) {
//                return mapping.findForward("ajax");
//            }
            
            return jobs;
	}

}
