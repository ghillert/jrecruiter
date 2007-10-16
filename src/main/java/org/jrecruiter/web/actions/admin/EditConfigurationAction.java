package org.jrecruiter.web.actions.admin;

import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jrecruiter.model.Configuration;
import org.jrecruiter.service.JobService;
import org.jrecruiter.web.forms.ConfigurationForm;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * List all the jobs.
 *
 * @author Gunnar Hillert
 * @version $Id: EditConfigurationFormController.java 128 2007-07-27 03:55:54Z ghillert $
 *
 */
public class EditConfigurationAction extends ActionSupport implements Preparable  {

	/** serialVersionUID */
	private static final long serialVersionUID = -2725680709601753520L;

	/**
	 * Logger Declaration.
	 */
    private final Log LOGGER = LogFactory.getLog(EditConfigurationAction.class);

    ConfigurationForm form = new ConfigurationForm();

    /**
     * The service layer reference.
     */
    private JobService service;

    /**
     * Inject the service layer reference.
     * @param service
     */
    public void setService(JobService service) {
		this.service = service;
	}

	public void prepare() throws Exception {

		if (form == null) {
	        form.setMailingListSubject( ((Configuration)service.getJRecruiterSetting("mail.jobposting.subject")).getMessageText());
	        form.setMailingListTemplate(((Configuration)service.getJRecruiterSetting("mail.jobposting.body")).getMessageText());
	        form.setMailingListEmail(   ((Configuration)service.getJRecruiterSetting("mail.jobposting.email")).getMessageText());
	        form.setMailFrom(           ((Configuration)service.getJRecruiterSetting("mail.from")).getMessageText());
	        form.setPasswordSubject(    ((Configuration)service.getJRecruiterSetting("mail.password.subject")).getMessageText());
	        form.setPasswordTemplate(   ((Configuration)service.getJRecruiterSetting("mail.password.body")).getMessageText());
		}
	}

    /**
     *
     */
    public String save() {

		LOGGER.debug("entering 'onSubmit' method...");

        Configuration configuration = new Configuration();
        configuration.setMessageKey("mail.jobposting.subject");
        configuration.setMessageText((String) form.getMailingListSubject());
        configuration.setLastModified(GregorianCalendar.getInstance().getTime());
        service.saveJRecruiterSetting(configuration);

        configuration = new Configuration();
        configuration.setMessageKey("mail.jobposting.body");
        configuration.setMessageText((String) form.getMailingListTemplate());
        configuration.setLastModified(GregorianCalendar.getInstance().getTime());
        service.saveJRecruiterSetting(configuration);

        configuration = new Configuration();
        configuration.setMessageKey("mail.jobposting.email");
        configuration.setMessageText((String) form.getMailingListEmail());
        configuration.setLastModified(GregorianCalendar.getInstance().getTime());
        service.saveJRecruiterSetting(configuration);

        configuration = new Configuration();
        configuration.setMessageKey("mail.from");
        configuration.setMessageText((String) form.getMailFrom());
        configuration.setLastModified(GregorianCalendar.getInstance().getTime());
        service.saveJRecruiterSetting(configuration);

        configuration = new Configuration();
        configuration.setMessageKey("mail.password.subject");
        configuration.setMessageText((String) form.getPasswordSubject());
        configuration.setLastModified(GregorianCalendar.getInstance().getTime());
        service.saveJRecruiterSetting(configuration);

        configuration = new Configuration();
        configuration.setMessageKey("mail.password.body");
        configuration.setMessageText((String) form.getPasswordTemplate());
        configuration.setLastModified(GregorianCalendar.getInstance().getTime());
        service.saveJRecruiterSetting(configuration);

        super.addActionMessage(getText("settings.edit.success"));

        return SUCCESS;

	}

}
