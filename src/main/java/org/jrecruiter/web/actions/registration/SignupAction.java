package org.jrecruiter.web.actions.registration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasypt.digest.StringDigester;
import org.jrecruiter.model.User;
import org.jrecruiter.service.exceptions.DuplicateUserException;
import org.jrecruiter.web.actions.BaseAction;
import org.jrecruiter.web.interceptor.StoreMessages;
import org.texturemedia.smarturls.Result;

import com.opensymphony.xwork2.conversion.annotations.Conversion;
import com.opensymphony.xwork2.validator.annotations.Validation;

/**
 * @author Gunnar Hillert
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
@Conversion
@Validation
@Result(name="success", location="index", type="redirectAction")
public class SignupAction extends BaseAction {


    private User user;
    private String password;

    private StringDigester stringDigester;

    /** serialVersionUID. */
    private static final long serialVersionUID = -3422780336408883930L;
    private final Log LOGGER = LogFactory.getLog(SignupAction.class);

    @StoreMessages
    public String save() {

        this.user.setPassword(this.stringDigester.digest(this.password));

        try {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setStringDigester(StringDigester stringDigester) {
        this.stringDigester = stringDigester;
    }

}
