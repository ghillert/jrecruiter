/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrecruiter.web.actions.registration;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.Validator;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.jasypt.digest.StringDigester;
import org.jrecruiter.common.Constants.UserAuthenticationType;
import org.jrecruiter.model.User;
import org.jrecruiter.service.UserService;
import org.jrecruiter.service.exceptions.DuplicateUserException;
import org.jrecruiter.web.controller.BaseFormController;
import org.jrecruiter.web.forms.UserForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Responsible for registering job posters.
 *
 * @author Gunnar Hillert
 * @version 3.0
 */
@Controller
public class SignupController extends BaseFormController {

	@Autowired private Validator validator;
	@Autowired private ConfigurableEnvironment environment;
	@Autowired private UserService userService;
	@Autowired private ReCaptcha reCaptcha;

	private final static Logger LOGGER = LoggerFactory.getLogger(SignupController.class);

	private String password;
	private String password2;

	private @Autowired StringDigester stringDigester;

	/** serialVersionUID. */
	private static final long serialVersionUID = -3422780336408883930L;

	@RequestMapping(value="/registration/signup.html", method=RequestMethod.POST)
	public String save(@Valid UserForm userForm,
			BindingResult bindingResult,
			ModelMap model,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		final String reCaptchaEnabled = environment.getProperty("recaptcha.enabled");
		final String recaptchaPrivateKey = environment.getProperty("recaptcha.privateKey");

		if (Boolean.valueOf(reCaptchaEnabled)) {
			String remoteAddr = request.getRemoteAddr();
			ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
			reCaptcha.setPrivateKey(recaptchaPrivateKey);

			String challenge = request.getParameter("recaptcha_challenge_field");
			String uresponse = request.getParameter("recaptcha_response_field");
			ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);

			if (!reCaptchaResponse.isValid()) {
				ObjectError error = new ObjectError("error","Please insert the correct CAPTCHA.");
				bindingResult.addError(error);
				return "/registration/signup";
			}
		}

		if (userForm.getPassword() != null && userForm.getPassword2() != null) {
			if (!password.trim().equals(password2.trim())) {
				ObjectError error = new ObjectError("password2", "The passwords do not match.");
				bindingResult.addError(error);
			}
		}

		final User user = new User();
		user.setCompany(userForm.getCompany());
		user.setEmail(userForm.getEmail());
		user.setEnabled(false);
		user.setFirstName(userForm.getFirstName());
		user.setLastName(userForm.getLastName());
		user.setPassword(this.stringDigester.digest(this.password));
		user.setPhone(userForm.getPhone());
		user.setUserAuthenticationType(UserAuthenticationType.USERNAME_PASSWORD);

		final Locale locale = request.getLocale();

		try {
			userService.addUser(user, Boolean.TRUE);
		} catch (DuplicateUserException e) {
			LOGGER.warn(e.getMessage());
			bindingResult.addError(new FieldError("username", getText("class._ALL.error.duplicateEmail", locale), "default"));
			return "/registration/signup";
		}

		//addActionMessage(super.getText("class.SignupAction.success", locale));
		return "redirect:/registration/signup-success";
	}

	public String saveForOpenId() {

//		if (session.get("OpenIdUserObject") == null) {
//			addActionError(getText("class.SignupAction.error.no_openid_token_found"));
//		}
//
//		final User openIdUser = (User) session.get("OpenIdUserObject");
//
//		user.setUsername(openIdUser.getUsername());
//		user.setUserAuthenticationType(UserAuthenticationType.OPEN_ID);
//
//		final ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(ServletActionContext.getRequest().getRemoteHost(), recaptcha_challenge_field, recaptcha_response_field);
//
//		if (!reCaptchaResponse.isValid()) {
//			addActionError(super.getText("class.SignupAction.error.not.a.good.captcha"));
//			return INPUT;
//		}
//
//		user.setPassword(stringDigester.digest(PasswordGenerator.generatePassword()));
//		try {
//		   userService.addUser(user, Boolean.TRUE);
//		} catch (DuplicateUserException e) {
//
//			LOGGER.warn(e.getMessage());
//			  addFieldError("username", getText("class._ALL.error.duplicateEmail"));
//			  return INPUT;
//		}
//
//		addActionMessage(getText("class.SignupAction.success"));
//		return SUCCESS;
		return "";
	}

	@RequestMapping(value="/registration/signup.html", method=RequestMethod.GET)
	public String execute(ModelMap model) {

//		if (session.get("OpenIdUserObject") != null) {
//
//			final User openIdUser = (User) session.get("OpenIdUserObject");
//			this.user = new User();
//			this.user.setEmail(openIdUser.getEmail());
//			this.user.setFirstName(openIdUser.getFirstName());
//			this.user.setLastName(openIdUser.getLastName());
//			this.user.setUserAuthenticationType(UserAuthenticationType.OPEN_ID);
//
//		} else {
			final UserForm userForm = new UserForm();
			userForm.setUserAuthenticationType(UserAuthenticationType.USERNAME_PASSWORD);
			model.addAttribute("userForm", userForm);
		//}

		return "/registration/signup";
	}


}
