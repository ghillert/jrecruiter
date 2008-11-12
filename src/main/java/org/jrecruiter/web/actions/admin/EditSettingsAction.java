package org.jrecruiter.web.actions.admin;

import java.util.GregorianCalendar;

import org.jrecruiter.model.Configuration;
import org.jrecruiter.web.actions.BaseAction;
import org.jrecruiter.web.forms.ConfigurationForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.texturemedia.smarturls.Result;

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
        form.setMailingListTemplate(((Configuration)jobService.getJRecruiterSetting("mail.jobposting.body")).getMessageText());
        form.setMailingListEmail(   ((Configuration)jobService.getJRecruiterSetting("mail.jobposting.email")).getMessageText());
        form.setMailFrom(           ((Configuration)jobService.getJRecruiterSetting("mail.from")).getMessageText());
        form.setPasswordSubject(    ((Configuration)jobService.getJRecruiterSetting("mail.password.subject")).getMessageText());
        form.setPasswordTemplate(   ((Configuration)jobService.getJRecruiterSetting("mail.password.body")).getMessageText());
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
        configuration.setMessageKey("mail.jobposting.body");
        configuration.setMessageText(form.getMailingListTemplate());
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

        configuration = new Configuration();
        configuration.setMessageKey("mail.password.subject");
        configuration.setMessageText(form.getPasswordSubject());
        configuration.setLastModified(GregorianCalendar.getInstance().getTime());
        jobService.saveJRecruiterSetting(configuration);

        configuration = new Configuration();
        configuration.setMessageKey("mail.password.body");
        configuration.setMessageText(form.getPasswordTemplate());
        configuration.setLastModified(GregorianCalendar.getInstance().getTime());
        jobService.saveJRecruiterSetting(configuration);

        super.addActionMessage(getText("settings.edit.success"));

        return SUCCESS;

    }

    public ConfigurationForm getModel() {
        return form;
    }

    public void setModel(ConfigurationForm form) {
        this.form = form;
    }
}
