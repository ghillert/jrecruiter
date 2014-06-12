package org.jrecruiter.web.controller;

import org.jrecruiter.web.controller.BaseFormController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Responsible for the login page.
 *
 * @author Gunnar Hillert
 * @since 3.0
 *
 */
@Controller
public class LoginAction extends BaseFormController {

	@RequestMapping("/s/login")
	public String login(ModelMap model) {
		return "/login";
	}

}
