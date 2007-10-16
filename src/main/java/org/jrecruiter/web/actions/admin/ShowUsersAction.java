package org.jrecruiter.web.actions.admin;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.PrincipalAware;
import org.apache.struts2.interceptor.PrincipalProxy;
import org.jrecruiter.model.User;
import org.jrecruiter.service.UserService;
import org.jrecruiter.web.forms.UserForm;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * List all the jobs.
 *
 * @author Gunnar Hillert
 * @version $Id: ShowUsersFormController.java 128 2007-07-27 03:55:54Z ghillert $
 *
 */
public class ShowUsersAction extends ActionSupport implements Preparable, PrincipalAware {

	/** serialVersionUID. */
	private static final long serialVersionUID = -2208374563094039361L;

	/**
	 * Logger Declaration.
	 */
    private final Log LOGGER = LogFactory.getLog(ShowUsersAction.class);

    /**
     * The service layer reference.
     */
    private UserService service;

    PrincipalProxy principalProxy;

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

	public PrincipalProxy getPrincipalProxy() {
		return principalProxy;
	}

	public void setPrincipalProxy(PrincipalProxy principalProxy) {
		this.principalProxy = principalProxy;
	}

	/**
     * Inject the service layer reference.
     * @param service
     */
    public void setService(UserService service) {
		this.service = service;
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

    	String username;
        if ((this.username != null)
         && (principalProxy.isUserInRole("admin"))) {
            username = this.username;
        } else {
            username = principalProxy.getRemoteUser();
        }

        User user = service.getUser(username);
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
