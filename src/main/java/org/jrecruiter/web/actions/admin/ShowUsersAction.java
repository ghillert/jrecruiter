package org.jrecruiter.web.actions.admin;

import java.text.SimpleDateFormat;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jrecruiter.model.User;
import org.jrecruiter.web.actions.BaseAction;
import org.jrecruiter.web.forms.UserForm;

import com.opensymphony.xwork2.Preparable;

/**
 * List all the jobs.
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

    String username;

    private User user;

    public String getUsername() {
		return username;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setUsername(String username) {
		this.username = username;
	}

    /**
     *
     */
    public String execute() {

//        DynaBean dynaForm = (DynaBean) form;
//
//        List usernames = (List) dynaForm.get("UserListForm");
//        if (usernames != null) {
//            List<Object> usernameList = new ArrayList<Object>();
//            for (int i = 0; i < usernames.size(); i++) {
//                LazyDynaBean userDel = (LazyDynaBean) usernames.get(i);
//                String delete = (String) userDel.get("delete");
//                if (delete != null) {
//                    usernameList.add(delete);
//                }
//            }
//            String[] usernamerArray = (String[]) usernameList.toArray(new String[usernameList.size()]);
//
//            ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(servlet.getServletContext());
//            UserService userService = (UserService) context.getBean("userService");
//
//            userService.deleteUser(usernamerArray);
//        }
//
//        request.getSession().setAttribute("message",
//                getText("userList.delete.success", ""));

        return SUCCESS;
	}

    public void prepare() throws Exception {

        User user = userService.getUser(super.getLoggedInUser().getUsername());
        UserForm form = new UserForm();

        form.setPassword2(user.getPassword());

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        if (user.getRegistrationDate() != null) {
        	form.setRegistrationDate(user.getRegistrationDate());
        }
        if (user.getUpdateDate() != null) {
        	form.setUpdateDate(user.getUpdateDate());
        }

        BeanUtils.copyProperties(form, user);

		this.user = user;

	}


//    List users = userService.getAllUsers();
//
//    request.setAttribute("userList", users);
//
//    String ajaxCall = request.getParameter("displayAjax");
//    if (ajaxCall != null && ajaxCall.equalsIgnoreCase("true")) {
//        return mapping.findForward("ajax");
//    }
//    return mapping.findForward("success");
//}

}
