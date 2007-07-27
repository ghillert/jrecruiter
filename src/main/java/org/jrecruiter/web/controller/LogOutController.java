package org.jrecruiter.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * List all the jobs. 
 * 
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
public class LogOutController extends MultiActionController  {
	
	/**
	 * Logger Declaration.
	 */
    private final Log LOGGER = LogFactory.getLog(LogOutController.class);
    
    /**
     * Success View
     */
    private String successView;

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

    	LOGGER.info("Logging out...");
    	
        HttpSession session = request.getSession();
        session.invalidate();

        final SecurityContext context = SecurityContextHolder.getContext();

        context.setAuthentication(null);
        
        return new ModelAndView(successView);
    	
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
