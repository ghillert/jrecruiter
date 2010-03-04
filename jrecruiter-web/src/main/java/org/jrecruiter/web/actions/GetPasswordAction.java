package org.jrecruiter.web.actions;

import org.apache.struts2.convention.annotation.Result;
import org.jrecruiter.common.Constants.UserAuthenticationType;
import org.jrecruiter.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

/**
 * Resets the users passwords and emails it back to the user.
 *
 * @author Gunnar Hillert
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
@Result(name="success", location="login", type="redirectAction")
public class GetPasswordAction extends BaseAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetPasswordAction.class);

    private User user;

    /** serialVersionUID. */
    private static final long serialVersionUID = -3422780336408883930L;

    public String execute() {
        return INPUT;
    }

    @Validations(
            requiredStrings = {
                        @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.username", trim=true, key = "class.get-password.password_reset_not_possible")
                     }
            )
    public String process() {

        final User userFromDb = userService.getUser(this.user.getUsername());

        if (userFromDb == null) {
            LOGGER.warn("Reset password attempt for non-existing user '" + this.user.getUsername() + "'");
            super.addActionError(super.getText("class.get-password.password_reset_not_possible"));
            return INPUT;
        }

        if (!userFromDb.isEnabled()) {
            LOGGER.warn("Reset password attempt for disabled user '" +  userFromDb.getUsername() + "' (Id: " + userFromDb.getId() + ")");
            super.addActionError(super.getText("class.get-password.password_reset_not_possible"));
            return INPUT;
        }

        if (!UserAuthenticationType.USERNAME_PASSWORD.equals(userFromDb.getUserAuthenticationType())) {
            LOGGER.warn("Reset password not supported for UserAuthenticationType: '" + userFromDb.getUserAuthenticationType().name() + "';"
                      + "User: '" +  userFromDb.getUsername() + "' (Id: " + userFromDb.getId() + ")");
            super.addActionError(super.getText("class.get-password.password_reset_not_possible"));
            return INPUT;
        }

        userService.resetPassword(userFromDb);

        super.addActionMessage(super.getText("class.get-password.success", new String[] {userFromDb.getEmail()}));
        return SUCCESS;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
