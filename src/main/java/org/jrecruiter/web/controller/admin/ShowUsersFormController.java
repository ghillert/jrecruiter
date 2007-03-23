package org.jrecruiter.web.controller.admin;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jrecruiter.model.User;
import org.jrecruiter.service.UserService;
import org.jrecruiter.web.controller.BaseSimpleFormController;
import org.jrecruiter.web.forms.UserForm;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

/**
 * List all the jobs. 
 * 
 * @author Gunnar Hillert
 * @version $Id: JobListAction.java 58 2006-10-16 03:45:45Z ghillert $
 *
 */
public class ShowUsersFormController extends BaseSimpleFormController  {
	
	/**
	 * Logger Declaration.
	 */
    private final Log LOGGER = LogFactory.getLog(ShowUsersFormController.class);
    
    /**
     * The service layer reference.
     */
    private UserService service;
    
    /**
     * Ajax View 
     */
    private String ajaxView;
    
    /**
     * Inject the service layer reference.
     * @param service 
     */
    public void setService(UserService service) {
		this.service = service;
	}

    /**
	 * @param ajaxView the ajaxView to set
	 */
	public void setAjaxView(String ajaxView) {
		this.ajaxView = ajaxView;
	}
  
    /**
     * 
     */
    public ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command,
            BindException errors)
	throws Exception {
		LOGGER.debug("entering 'onSubmit' method...");
        User user = (User) command;

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

        return new ModelAndView("success");
	}

    
    /* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		
        String username;
        if ((request.getParameter("username") != null)
         && (request.isUserInRole("admin"))) {
            username = request.getParameter("username");
        } else {
            username = request.getRemoteUser();
        }

        User user = service.getUser(username);
        UserForm form = new UserForm();
        
        form.setPassword2(user.getPassword());

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        if (user.getRegisterDate() != null) {
        	form.setRegisterDate(user.getRegisterDate());
        }
        if (user.getUpdateDate() != null) {
        	form.setUpdateDate(user.getUpdateDate());
        }
        
        BeanUtils.copyProperties(form, user);
			
		return user;
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
