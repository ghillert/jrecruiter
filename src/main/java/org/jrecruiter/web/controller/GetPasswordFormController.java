package org.jrecruiter.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jrecruiter.model.User;
import org.jrecruiter.service.UserService;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * List all the jobs. 
 * 
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
public class GetPasswordFormController extends SimpleFormController  {
	
	/**
	 * Logger Declaration.
	 */
    private final Log LOGGER = LogFactory.getLog(GetPasswordFormController.class);
    
    /**
     * The service layer reference.
     */
    private UserService service;
    
    /**
     * Inject the service layer reference.
     * @param service 
     */
    public void setService(UserService service) {
		this.service = service;
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

		User form = (User) command;
		User userFromDb = service.getUser(form.getUsername());
        
		if (userFromDb == null) {
            errors.addError(new ObjectError("username", new String[]{"error.usernameNotFound"}, null, "key error.usernameNotFound not found"));
            return new ModelAndView(getSuccessView());
        } else {
            service.sendPassword(userFromDb);
            request.getSession().setAttribute("message",
            		getText("user.add.success", userFromDb.getUsername()));
        }
		
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
