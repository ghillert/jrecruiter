package org.jrecruiter.web.actions.admin;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jrecruiter.model.User;
import org.jrecruiter.web.actions.BaseAction;

import com.opensymphony.xwork2.Preparable;

/**
 * List all the users.
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
public class ShowUsersAction extends BaseAction implements Preparable {

    /** serialVersionUID. */
    private static final long serialVersionUID = -2208374563094039361L;

    /**
     * Logger Declaration.
     */
    private final Log LOGGER = LogFactory.getLog(ShowUsersAction.class);

    private List<User>users;

    /**
     *
     */
    public String execute() {

        this.users = userService.getAllUsers();
        return SUCCESS;

    }

    public void prepare() throws Exception {

    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
