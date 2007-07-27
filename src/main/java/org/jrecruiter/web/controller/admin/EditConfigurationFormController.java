package org.jrecruiter.web.controller.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jrecruiter.model.Configuration;
import org.jrecruiter.service.JobService;
import org.jrecruiter.web.controller.BaseSimpleFormController;
import org.jrecruiter.web.forms.ConfigurationForm;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.GregorianCalendar;

/**
 * List all the jobs.
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
public class EditConfigurationFormController extends BaseSimpleFormController  {

	/**
	 * Logger Declaration.
	 */
    private final Log LOGGER = LogFactory.getLog(EditConfigurationFormController.class);

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


	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Object formBackingObject(HttpServletRequest arg0) throws Exception {

        final ConfigurationForm form = new ConfigurationForm();

        form.setMailingListSubject( ((Configuration)service.getJRecruiterSetting("mail.jobposting.subject")).getMessageText());
        form.setMailingListTemplate(((Configuration)service.getJRecruiterSetting("mail.jobposting.body")).getMessageText());
        form.setMailingListEmail(   ((Configuration)service.getJRecruiterSetting("mail.jobposting.email")).getMessageText());
        form.setMailFrom(           ((Configuration)service.getJRecruiterSetting("mail.from")).getMessageText());
        form.setPasswordSubject(    ((Configuration)service.getJRecruiterSetting("mail.password.subject")).getMessageText());
        form.setPasswordTemplate(   ((Configuration)service.getJRecruiterSetting("mail.password.body")).getMessageText());

        return form;
	}

    /**
     *
     */
    public ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command,
            BindException errors)
	throws Exception {
		LOGGER.debug("entering 'onSubmit' method...");

		ConfigurationForm settingsForm = (ConfigurationForm) command;

        Configuration configuration = new Configuration();
        configuration.setMessageKey("mail.jobposting.subject");
        configuration.setMessageText((String) settingsForm.getMailingListSubject());
        configuration.setLastModified(GregorianCalendar.getInstance().getTime());
        service.saveJRecruiterSetting(configuration);

        configuration = new Configuration();
        configuration.setMessageKey("mail.jobposting.body");
        configuration.setMessageText((String) settingsForm.getMailingListTemplate());
        configuration.setLastModified(GregorianCalendar.getInstance().getTime());
        service.saveJRecruiterSetting(configuration);

        configuration = new Configuration();
        configuration.setMessageKey("mail.jobposting.email");
        configuration.setMessageText((String) settingsForm.getMailingListEmail());
        configuration.setLastModified(GregorianCalendar.getInstance().getTime());
        service.saveJRecruiterSetting(configuration);

        configuration = new Configuration();
        configuration.setMessageKey("mail.from");
        configuration.setMessageText((String) settingsForm.getMailFrom());
        configuration.setLastModified(GregorianCalendar.getInstance().getTime());
        service.saveJRecruiterSetting(configuration);

        configuration = new Configuration();
        configuration.setMessageKey("mail.password.subject");
        configuration.setMessageText((String) settingsForm.getPasswordSubject());
        configuration.setLastModified(GregorianCalendar.getInstance().getTime());
        service.saveJRecruiterSetting(configuration);

        configuration = new Configuration();
        configuration.setMessageKey("mail.password.body");
        configuration.setMessageText((String) settingsForm.getPasswordTemplate());
        configuration.setLastModified(GregorianCalendar.getInstance().getTime());
        service.saveJRecruiterSetting(configuration);

        request.getSession().setAttribute("message",
                getText("settings.edit.success", ""));

        return new ModelAndView(getSuccessView());

	}

}
