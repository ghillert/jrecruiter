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

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.LazyDynaBean;
import org.apache.struts.action.*;
import org.jrecruiter.service.UserServiceInterface;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dorota Puchala
 * @version $Revision: 1.4 $, $Date: 2006/03/19 22:00:40 $, $Author: ghillert $
 */
public class DeleteUserAction extends Action {
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        ActionMessages messages = new ActionMessages();
        if (isCancelled(request)) {

            messages.add("info", new ActionMessage("errors.cancel"));
            saveMessages(request, messages);

            return mapping.findForward("cancel");
        }

        DynaBean dynaForm = (DynaBean) form;

        List usernames = (List) dynaForm.get("UserListForm");
        if (usernames != null) {
            List<Object> usernameList = new ArrayList<Object>();
            for (int i = 0; i < usernames.size(); i++) {
                LazyDynaBean userDel = (LazyDynaBean) usernames.get(i);
                String delete = (String) userDel.get("delete");
                if (delete != null) {
                    usernameList.add(delete);
                }
            }
            String[] usernamerArray = (String[]) usernameList.toArray(new String[usernameList.size()]);

            ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(servlet.getServletContext());
            UserServiceInterface userService = (UserServiceInterface) context.getBean("userService");

            userService.deleteUser(usernamerArray);
        }

        messages.add("info", new ActionMessage("userList.delete.success"));
        saveMessages(request, messages);

        return mapping.findForward("success");
    }
}
