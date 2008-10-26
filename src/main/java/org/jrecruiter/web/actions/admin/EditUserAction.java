package org.jrecruiter.web.actions.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jrecruiter.model.User;
import org.jrecruiter.web.actions.BaseAction;
import org.jrecruiter.web.interceptor.StoreMessages;
import org.springframework.security.context.SecurityContextHolder;
import org.texturemedia.smarturls.Result;

/**
 * @author Gunnar Hillert
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
@Result(name="success", location="index", type="redirectAction")
public class EditUserAction extends BaseAction {

    private User user;

    private Long userId;

    /** serialVersionUID. */
    private static final long serialVersionUID = -3422780336408883930L;
    private final Log LOGGER = LogFactory.getLog(EditUserAction.class);

    /**
     * Save the user.
     * @return
     */
    @StoreMessages
    public String save() {

        final User userFromDb = userService.getUser(this.user.getId());

        if (userFromDb == null) {
            throw new IllegalStateException(
                    "User with id " + user.getId()
                  + " not found but was expected to exist");
        }

        //Let's check whether a username with for a different user id
        //already exists in the system.
        final User duplicateUserFromDb = userService.getUser(user.getUsername());

        if (userFromDb != null && !userFromDb.getId().equals(duplicateUserFromDb.getId())) {
            LOGGER.warn("Duplicate user name ("+ user.getUsername() +") for id "
                    + userFromDb.getId());
            addFieldError("username", getText("error.duplicateUsername"));
            return INPUT;
        }

        userFromDb.setCompany(this.user.getCompany());
        userFromDb.setEmail(this.user.getEmail());
        userFromDb.setFax(this.user.getFax());
        userFromDb.setFirstName(this.user.getFirstName());
        userFromDb.setLastName(this.user.getLastName());
        userFromDb.setPassword(this.user.getPassword());
        userFromDb.setPhone(this.user.getPhone());
        userFromDb.setUsername(this.user.getUsername());

        userService.updateUser(userFromDb);

        addActionMessage(getText("user.edit.success"));

        User securityContextUser =
            (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        securityContextUser.setCompany(this.user.getCompany());
        securityContextUser.setEmail(this.user.getEmail());
        securityContextUser.setFax(this.user.getFax());
        securityContextUser.setFirstName(this.user.getFirstName());
        securityContextUser.setLastName(this.user.getLastName());
        securityContextUser.setPassword(this.user.getPassword());
        securityContextUser.setPhone(this.user.getPhone());
        securityContextUser.setUsername(this.user.getUsername());

        return SUCCESS;
    }

    /**
     * Initialize the edit user form with the registration information of the
     * currently logged in user.
     */
    public String execute() {

        if (this.userId != null) {
            this.user = userService.getUser(this.userId);
        } else {
            this.user = super.getLoggedInUser();
        }
        return INPUT;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


}
