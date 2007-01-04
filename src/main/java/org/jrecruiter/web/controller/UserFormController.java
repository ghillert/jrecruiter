package org.jrecruiter.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jrecruiter.model.User;
import org.jrecruiter.service.UserServiceInterface;
import org.jrecruiter.service.exceptions.DuplicateUserException;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * List all the jobs. 
 * 
 * @author Gunnar Hillert
 * @version $Id: JobListAction.java 58 2006-10-16 03:45:45Z ghillert $
 *
 */
public class UserFormController extends SimpleFormController  {
	
	/**
	 * Logger Declaration.
	 */
    private final Log LOGGER = LogFactory.getLog(UserFormController.class);
    
    /**
     * The service layer reference.
     */
    private UserServiceInterface service;
    
    /**
     * Ajax View 
     */
    private String ajaxView;
    
    /**
     * Inject the service layer reference.
     * @param service 
     */
    public void setService(UserServiceInterface service) {
		this.service = service;
	}

    /**
	 * @param ajaxView the ajaxView to set
	 */
	public void setAjaxView(String ajaxView) {
		this.ajaxView = ajaxView;
	}

	/**
	 * 
	 */
    public ModelAndView processFormSubmission(HttpServletRequest request,
            HttpServletResponse response,
            Object command,
            BindException errors)  
			throws Exception {
    	
			if (request.getParameter("cancel") != null) {
				return new ModelAndView(getSuccessView());
			}
			
			return super.processFormSubmission(request, response, command, errors);
	}
    
    /**
     * 
     */
    public ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command,
            BindException errors)
	throws Exception {
		LOGGER.debug("entering 'onSubmit' method...");
		
		User user = (User) command;
		
        try {
           service.addUser(user);
        } catch (DuplicateUserException e) {

            LOGGER.warn(e.getMessage());

			  errors.rejectValue("username", "error.duplicateUsername",
			  null,
			  "bundle error.duplicateUsername");
			  return showForm(request, errors, getFormView());

        }

        request.getSession().setAttribute("message",
        		getText("user.add.success", user.getUsername()));
		
		return new ModelAndView(getSuccessView());
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
