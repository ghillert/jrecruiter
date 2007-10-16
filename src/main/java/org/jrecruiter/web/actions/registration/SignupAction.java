package org.jrecruiter.web.actions.registration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jrecruiter.model.User;
import org.jrecruiter.service.UserService;
import org.jrecruiter.service.exceptions.DuplicateUserException;
import org.jrecruiter.web.actions.BaseAction;
import org.jrecruiter.web.interceptor.StoreMessages;
import org.texturemedia.smarturls.Result;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

/**
 * @author Gunnar Hillert
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */

@Validation
@Result(name="success", location="index", type="redirectAction")
public class SignupAction extends BaseAction {

	private User user;

	/** serialVersionUID. */
	private static final long serialVersionUID = -3422780336408883930L;
	private final Log LOGGER = LogFactory.getLog(SignupAction.class);

	@StoreMessages
    public String save() {

        try {
           //FIXME
           userService.addUser(user);
        } catch (DuplicateUserException e) {

            LOGGER.warn(e.getMessage());
              addFieldError("username", getText("error.duplicateUsername"));
			  return INPUT;
        }

        addActionMessage(getText("user.add.success", user.getUsername()));
        return SUCCESS;
    }

    public String execute() {
        return INPUT;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
