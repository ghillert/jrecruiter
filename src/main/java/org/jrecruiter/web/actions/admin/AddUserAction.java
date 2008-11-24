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

import org.jrecruiter.model.User;
import org.jrecruiter.service.exceptions.DuplicateUserException;
import org.jrecruiter.web.actions.BaseAction;
import org.jrecruiter.web.interceptor.StoreMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.texturemedia.smarturls.Result;

import com.opensymphony.xwork2.validator.annotations.Validation;

/**
 * @author Gunnar Hillert
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */

@Validation
@Result(name="success", location="index", type="redirectAction")
public class AddUserAction extends BaseAction {

    private User user;

    /** serialVersionUID. */
    private static final long serialVersionUID = -3422780336408883930L;
    private final static Logger LOGGER = LoggerFactory.getLogger(AddUserAction.class);

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
