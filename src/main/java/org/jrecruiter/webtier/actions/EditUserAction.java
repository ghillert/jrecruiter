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
package org.jrecruiter.webtier.actions;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.*;
import org.jrecruiter.model.User;
import org.jrecruiter.service.UserServiceInterface;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

/**
 * @author Dorota Puchala
 * @version $Revision: 1.5 $, $Date: 2006/03/19 22:00:40 $, $Author: ghillert $
 */
public class EditUserAction extends Action {
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionMessages messages = new ActionMessages();

        if (isCancelled(request)) {

            messages.add("info", new ActionMessage("errors.cancel"));
            saveMessages(request, messages);

            return mapping.findForward("cancel");

        }

        String username;
        if ((request.getParameter("username") != null)
         && (request.isUserInRole("admin"))) {
            username = request.getParameter("username");
        } else {
            username = request.getRemoteUser();
        }
        
        ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(servlet.getServletContext());
        UserServiceInterface userService = (UserServiceInterface) context.getBean("userService");

        User user = userService.getUser(username);
        DynaActionForm dynaForm = (DynaActionForm) form;
        dynaForm.set("password2", user.getPassword());
        dynaForm.set("update", true);

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        if (user.getRegisterDate() != null) {
            dynaForm.set("registerDateDF", formatter.format(user
                    .getRegisterDate()));
        }
        if (user.getUpdateDate() != null) {
            dynaForm.set("updateDateDF", formatter.format(user
                    .getUpdateDate()));
        }
        BeanUtils.copyProperties(dynaForm, user);
        return mapping.findForward("success");
    }
}
