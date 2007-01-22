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

import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.validator.DynaValidatorForm;
import org.jrecruiter.model.Configuration;
import org.jrecruiter.service.JobService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Struts action class for handling general settings of the application.
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
public final class SettingsAction extends DispatchAction {

    /**
     * The logger declaration.
     */
    public static final Logger logger = Logger.getLogger(SettingsAction.class);

    /**
     * Initializes the edit settings page.
     *
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward openEditSettings(final ActionMapping mapping,
                                          final ActionForm form,
                                          final HttpServletRequest request,
                                          final HttpServletResponse response)
                            throws Exception {

        if (request.getUserPrincipal() != null) {

            ApplicationContext context = WebApplicationContextUtils.
            getRequiredWebApplicationContext(servlet.getServletContext());

            JobService service = (JobService) context.
            getBean("jobService");

            DynaValidatorForm settingsForm = (DynaValidatorForm)form;

            settingsForm.set("mailingListSubject",  ((Configuration)service.getJRecruiterSetting("mail.jobposting.subject")).getText());
            settingsForm.set("mailingListTemplate", ((Configuration)service.getJRecruiterSetting("mail.jobposting.body")).getText());
            settingsForm.set("mailingListEmail",    ((Configuration)service.getJRecruiterSetting("mail.jobposting.email")).getText());
            settingsForm.set("mailFrom",            ((Configuration)service.getJRecruiterSetting("mail.from")).getText());
            settingsForm.set("passwordSubject",     ((Configuration)service.getJRecruiterSetting("mail.password.subject")).getText());
            settingsForm.set("passwordTemplate",    ((Configuration)service.getJRecruiterSetting("mail.password.body")).getText());

            return mapping.findForward("openEditSuccess");

        } else {
            return mapping.findForward("sessionTimeout");
        }
    }

    public ActionForward editSettings(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ActionMessages messages = new ActionMessages();

        if (isCancelled(request)) {

            messages.add("info", new ActionMessage("errors.cancel"));
            saveMessages(request, messages);
            return mapping.findForward("cancel");

        }

        if (request.getUserPrincipal() != null) {

            ApplicationContext context = WebApplicationContextUtils.
            getRequiredWebApplicationContext(servlet.getServletContext());

            JobService service = (JobService) context.
            getBean("jobService");

            DynaValidatorForm settingsForm = (DynaValidatorForm) form;

            Configuration configuration = new Configuration();
            configuration.setKey("mail.jobposting.subject");
            configuration.setText((String) settingsForm.get("mailingListSubject"));
            configuration.setLastModified(GregorianCalendar.getInstance().getTime());
            service.saveJRecruiterSetting(configuration);

            configuration = new Configuration();
            configuration.setKey("mail.jobposting.body");
            configuration.setText((String) settingsForm.get("mailingListTemplate"));
            configuration.setLastModified(GregorianCalendar.getInstance().getTime());
            service.saveJRecruiterSetting(configuration);

            configuration = new Configuration();
            configuration.setKey("mail.jobposting.email");
            configuration.setText((String) settingsForm.get("mailingListEmail"));
            configuration.setLastModified(GregorianCalendar.getInstance().getTime());
            service.saveJRecruiterSetting(configuration);

            configuration = new Configuration();
            configuration.setKey("mail.from");
            configuration.setText((String) settingsForm.get("mailFrom"));
            configuration.setLastModified(GregorianCalendar.getInstance().getTime());
            service.saveJRecruiterSetting(configuration);

            configuration = new Configuration();
            configuration.setKey("mail.password.subject");
            configuration.setText((String) settingsForm.get("passwordSubject"));
            configuration.setLastModified(GregorianCalendar.getInstance().getTime());
            service.saveJRecruiterSetting(configuration);

            configuration = new Configuration();
            configuration.setKey("mail.password.body");
            configuration.setText((String) settingsForm.get("passwordTemplate"));
            configuration.setLastModified(GregorianCalendar.getInstance().getTime());
            service.saveJRecruiterSetting(configuration);

            messages.add("info", new ActionMessage("settings.edit.success"));
            saveMessages(request, messages);

            return mapping.findForward("success");
        }
        return mapping.findForward("sessionTimeout");
    }

}
