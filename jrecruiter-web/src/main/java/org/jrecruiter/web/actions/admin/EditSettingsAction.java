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

import java.util.GregorianCalendar;

import org.apache.struts2.convention.annotation.Result;
import org.jrecruiter.model.Configuration;
import org.jrecruiter.web.actions.BaseAction;
import org.jrecruiter.web.forms.ConfigurationForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * List all the jobs.
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
@Result(name="success", location="index", type="redirectAction")
public class EditSettingsAction extends BaseAction implements Preparable, ModelDriven<ConfigurationForm>  {

    /** serialVersionUID */
    private static final long serialVersionUID = -2725680709601753520L;

    /**
     * Logger Declaration.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(EditSettingsAction.class);

    ConfigurationForm form = new ConfigurationForm();

    public void prepare() throws Exception { }

    public String execute() {
        form.setMailingListSubject( ((Configuration)jobService.getJRecruiterSetting("mail.jobposting.subject")).getMessageText());
        form.setMailingListEmail(   ((Configuration)jobService.getJRecruiterSetting("mail.jobposting.email")).getMessageText());
        form.setMailFrom(           ((Configuration)jobService.getJRecruiterSetting("mail.from")).getMessageText());
        return INPUT;
    }

    /**
     *
     */
    public String save() {

        LOGGER.debug("entering 'onSubmit' method...");

        Configuration configuration = new Configuration();
        configuration.setMessageKey("mail.jobposting.subject");
        configuration.setMessageText(form.getMailingListSubject());
        configuration.setLastModified(GregorianCalendar.getInstance().getTime());
        jobService.saveJRecruiterSetting(configuration);

        configuration = new Configuration();
        configuration.setMessageKey("mail.jobposting.email");
        configuration.setMessageText(form.getMailingListEmail());
        configuration.setLastModified(GregorianCalendar.getInstance().getTime());
        jobService.saveJRecruiterSetting(configuration);

        configuration = new Configuration();
        configuration.setMessageKey("mail.from");
        configuration.setMessageText(form.getMailFrom());
        configuration.setLastModified(GregorianCalendar.getInstance().getTime());
        jobService.saveJRecruiterSetting(configuration);

        super.addActionMessage(getText("class.admin.EditSettingsAction.success"));

        return SUCCESS;

    }

    public ConfigurationForm getModel() {
        return form;
    }

    public void setModel(ConfigurationForm form) {
        this.form = form;
    }
}
