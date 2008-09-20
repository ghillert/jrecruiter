package org.jrecruiter.web.actions.registration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasypt.digest.StringDigester;
import org.jrecruiter.model.User;
import org.jrecruiter.service.exceptions.DuplicateUserException;
import org.jrecruiter.web.actions.BaseAction;
import org.jrecruiter.web.interceptor.RetrieveMessages;
import org.jrecruiter.web.interceptor.StoreMessages;
import org.texturemedia.smarturls.Result;

import com.opensymphony.xwork2.conversion.annotations.Conversion;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

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
    private String password2;

    private StringDigester stringDigester;

    /** serialVersionUID. */
    private static final long serialVersionUID = -3422780336408883930L;
    private final Log LOGGER = LogFactory.getLog(SignupAction.class);

    @RetrieveMessages
    @StoreMessages
    @Validations(
            requiredStrings = {
                        @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.username",  trim=true, message = "You must enter a username."),
                        @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "password",       trim=true, message = "You must enter a password."),
                        @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.firstName", trim=true, message = "You must enter a first name."),
                        @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.lastName",  trim=true, message = "You must enter a last name."),
                        @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "user.company",   trim=true, message = "You must enter a company.")
                     },
             requiredFields = {
                     @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "user.email",  message = "You must enter an email address.")
                  },
            emails =
                    { @EmailValidator(type = ValidatorType.SIMPLE, fieldName = "user.email", message = "You must enter a valid email address.")},
            stringLengthFields =
                    {
                    @StringLengthFieldValidator(type = ValidatorType.SIMPLE, trim = true, maxLength = "50", fieldName = "user.username",  message = "The user name must be shorter than ${maxLength} characters."),
                    @StringLengthFieldValidator(type = ValidatorType.SIMPLE, trim = true, minLength = "6",  fieldName = "password",       message = "The password must be at least ${minLength} characters."),
                    @StringLengthFieldValidator(type = ValidatorType.SIMPLE, trim = true, maxLength = "50", fieldName = "user.firstName", message = "The first name must be shorter than ${maxLength} characters."),
                    @StringLengthFieldValidator(type = ValidatorType.SIMPLE, trim = true, maxLength = "50", fieldName = "user.lastName",  message = "The last name must be shorter than ${maxLength} characters."),
                    @StringLengthFieldValidator(type = ValidatorType.SIMPLE, trim = true, maxLength = "50", fieldName = "user.company",   message = "The company name must be shorter than ${maxLength} characters."),
                    @StringLengthFieldValidator(type = ValidatorType.SIMPLE, trim = true, maxLength = "50", fieldName = "user.email",     message = "The email address must be shorter than ${maxLength} characters."),
                    @StringLengthFieldValidator(type = ValidatorType.SIMPLE, trim = true, maxLength = "25", fieldName = "user.phone",     message = "The phone number must be shorter than ${maxLength} characters."),
                    @StringLengthFieldValidator(type = ValidatorType.SIMPLE, trim = true, maxLength = "25", fieldName = "user.fax",       message = "The fax number must be shorter than ${maxLength} characters.")
                    }
            )
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

    /**
     *
     */
    public void validate() {

        if (password != null && password != null) {

            if (!password.trim().equals(password2.trim())) {
                addFieldError("password2", "The passwords do not match.");
            }

        }

    }

    @RetrieveMessages
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

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public void setStringDigester(StringDigester stringDigester) {
        this.stringDigester = stringDigester;
    }

}
