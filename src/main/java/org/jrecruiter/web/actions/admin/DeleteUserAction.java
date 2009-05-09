/*
*	http://www.jrecruiter.org
*
*	Disclaimer of Warranty.
*
*	Unless required by applicable law or agreed to in writing, Licensor provides
*	the Work (and each Contributor provides its Contributions) on an "AS IS" BASIS,
*	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied,
*	including, without limitation, any warranties or conditions of TITLE,
*	NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE. You are
*	solely responsible for determining the appropriateness of using or
*	redistributing the Work and assume any risks associated with Your exercise of
*	permissions under this License.
*
*/
package org.jrecruiter.web.actions.admin;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.jrecruiter.model.User;
import org.jrecruiter.web.actions.BaseAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Deletes one or more users.
 *
 * @author Gunnar Hillert
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
@Result(name="success", location="show-users", type="redirectAction")
public class DeleteUserAction extends BaseAction {

    /** serialVersionUID. */
    private static final long serialVersionUID = -2825056640805045453L;

    private List<Long>userIds;

    private final static Logger LOGGER = LoggerFactory.getLogger(DeleteUserAction.class);

    /**
     * Initialize the edit user form with the registration information of the
     * currently logged in user.
     */
    public String execute() {

        if (this.userIds != null && !this.userIds.isEmpty()) {

            int validUserIds = 0;

            for (Long userId : userIds) {

                if (userId != null) {

                    User user = userService.getUser(userId);

                    if (user.getId() == -1) {
                        LOGGER.warn("User tried to delete admin user.");
                        super.addActionMessage("Cannot delete admin user.");
                    } else {
                        validUserIds++;
                        userService.deleteUser(user);
                    }
                }
            }

            if (validUserIds == 1) {
                super.addActionMessage(validUserIds + " user was deleted.");
            } else {
                super.addActionMessage(validUserIds + " users were deleted.");
            }

        } else {
            super.addActionMessage("No User was deleted.");
        }

        return SUCCESS;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

}
