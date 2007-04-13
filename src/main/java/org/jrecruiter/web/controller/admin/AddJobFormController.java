package org.jrecruiter.web.controller.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.User;
import org.jrecruiter.service.JobService;
import org.jrecruiter.web.controller.BaseSimpleFormController;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * List all the jobs. 
 * 
 * @author Gunnar Hillert
 * @version $Id: JobListAction.java 58 2006-10-16 03:45:45Z ghillert $
 *
 */
public class AddJobFormController extends BaseSimpleFormController  {
	
	/**
	 * Logger Declaration.
	 */
    private final Log LOGGER = LogFactory.getLog(AddJobFormController.class);
    
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
     * Set up a custom property editor for converting Longs
     */
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) {

        // convert java.lang.Long
        binder.registerCustomEditor(Double.class, null,
                new CustomNumberEditor(Double.class, null, true));
    }

    /**
     * 
     */
    @Override
    public ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command,
            BindException errors)
	throws Exception {
			
    	    LOGGER.debug("Entering 'onSubmit' method - Adding Job.");

            final Job job = (Job) command;

            //TODO better handling required.
            
            job.setStatus(1);
            job.setUsername(request.getRemoteUser());
            job.setRegisterDate(new Date());
            job.setUpdateDate(new Date());

            User owner = new User();
            owner.setUsername(job.getUsername());
            job.setOwner(owner);
            service.addJob(job);
            service.sendJobPostingToMailingList(job);
            
            request.getSession().setAttribute("message",
                    getText("job.add.success", ""));

            return new ModelAndView(getSuccessView());

	}

}
