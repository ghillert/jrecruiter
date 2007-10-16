package org.jrecruiter.web.actions.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jrecruiter.model.User;
import org.jrecruiter.service.UserService;
import org.jrecruiter.service.exceptions.DuplicateUserException;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author Gunnar Hillert
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
public class EditUserAction extends ActionSupport {

	private User user;

    /**
     * The service layer reference.
     */
    private UserService userService;

	/** serialVersionUID. */
	private static final long serialVersionUID = -3422780336408883930L;
	private final Log LOGGER = LogFactory.getLog(EditUserAction.class);

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

    public String cancel() {
        return SUCCESS;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}



}
