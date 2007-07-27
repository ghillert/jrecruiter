package org.jrecruiter.web.controller.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jrecruiter.service.JobService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

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
public class ShowStatisticsController extends MultiActionController {

	/**
	 * Logger Declaration.
	 */
	private final Log LOGGER = LogFactory
			.getLog(ShowStatisticsController.class);

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
			final HttpServletResponse response) throws Exception {

		final List jobs = service.getUsersJobsForStatistics(request
				.getRemoteUser());

		request.setAttribute("jobs", jobs);

		String ajaxCall = request.getParameter("displayAjax");
		if (ajaxCall != null && ajaxCall.equalsIgnoreCase("true")) {
			return new ModelAndView("ajax");
		}

		return new ModelAndView("success");
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
