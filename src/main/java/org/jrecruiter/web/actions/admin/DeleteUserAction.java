package org.jrecruiter.web.actions.admin;

import java.util.HashSet;
import java.util.Set;

import org.jrecruiter.model.User;
import org.jrecruiter.web.actions.BaseAction;
import org.jrecruiter.web.interceptor.StoreMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.texturemedia.smarturls.Result;

/**
 * Deletes one or more users.
 *
 * @author Gunnar Hillert
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
@Result(name="success", location="index", type="redirectAction")
public class DeleteUserAction extends BaseAction {

    /** serialVersionUID. */
    private static final long serialVersionUID = -2825056640805045453L;

    private Set<Long>userIds = new HashSet<Long>(0);

    private final static Logger LOGGER = LoggerFactory.getLogger(DeleteUserAction.class);

    /**
     * Initialize the edit user form with the registration information of the
     * currently logged in user.
     */
    @StoreMessages
    public String execute() {

        if (this.userIds != null && !this.userIds.isEmpty()) {

            for (Long userId : userIds) {
                User user = userService.getUser(userId);

                if (user.getId() == -1) {
                    LOGGER.warn("User tried to delete admin user.");
                    super.addActionMessage("Cannot delete admin user.");
                } else {
                    userService.deleteUser(user);
                }
            }

            if (this.userIds.size() == 1) {
                super.addActionMessage(this.userIds.size() + " user was deleted.");
            } else {
                super.addActionMessage(this.userIds.size() + " users were deleted.");
            }

        } else {
            super.addActionMessage("No User was deleted.");
        }

        return SUCCESS;
    }

    public Set<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(Set<Long> userIds) {
        this.userIds = userIds;
    }

}
