package org.jrecruiter.web.actions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Logs out from the application.
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
@Controller
public class LogoutController extends BaseAction {

	/** serialVersionUID. */
	private static final long serialVersionUID = -7129460964433090813L;

	/**
	 * Logger Declaration.
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(LogoutController.class);

	/**
	 * Let's log out - Invalidate the session as well as the ACEGI security
	 * context.
	 */
	@RequestMapping({"/admin/logout.html"})
	public String execute () {

		final SecurityContext context = SecurityContextHolder.getContext();

		if (context.getAuthentication() != null) {
			LOGGER.info("Logging out user..." + context.getAuthentication().getName());
		} else {
			LOGGER.warn("User not logged in.");
		}

		context.setAuthentication(null);

		super.addActionMessage("You logged out successfully.");
		return "redirect:/";
	}
}
