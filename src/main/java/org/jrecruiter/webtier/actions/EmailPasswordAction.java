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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.jrecruiter.model.User;
import org.jrecruiter.service.UserServiceInterface;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author Dorota Puchala
 * @version $Revision: 1.4 $, $Date: 2006/03/19 22:00:40 $, $Author: ghillert $
 */
public class EmailPasswordAction extends Action {

    /**
     *   Initialize Logging.
     */
    public static final Logger LOGGER = Logger.getLogger(EmailPasswordAction.class);

    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        if (isCancelled(request)) {
            ActionMessages messages = new ActionMessages();
            messages.add("info", new ActionMessage("errors.cancel"));
            saveMessages(request, messages);
            return mapping.findForward("cancel");
        }

        DynaActionForm dynaForm = (DynaActionForm) form;

        ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(servlet.getServletContext());
        UserServiceInterface userService = (UserServiceInterface) context.getBean("userService");

        User user = userService.getUser((String) dynaForm.get("username"));
        if (user == null) {
            ActionMessages errors = new ActionMessages();
            errors.add("usernameNotFound", new ActionMessage(
                    "error.usernameNotFound"));
            saveErrors(request, errors);
            return mapping.getInputForward();
        } else {
            userService.sendPassword(user);
        }
        return mapping.findForward("success");
    }

}
