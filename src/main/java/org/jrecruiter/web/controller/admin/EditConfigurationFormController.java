package org.jrecruiter.web.controller.admin;

import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jrecruiter.model.Configuration;
import org.jrecruiter.service.JobService;
import org.jrecruiter.web.controller.BaseSimpleFormController;
import org.jrecruiter.web.forms.ConfigurationForm;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

/**
 * List all the jobs. 
 * 
 * @author Gunnar Hillert
 * @version $Id: JobListAction.java 58 2006-10-16 03:45:45Z ghillert $
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

        form.setMailingListSubject( ((Configuration)service.getJRecruiterSetting("mail.jobposting.subject")).getText());
        form.setMailingListTemplate(((Configuration)service.getJRecruiterSetting("mail.jobposting.body")).getText());
        form.setMailingListEmail(   ((Configuration)service.getJRecruiterSetting("mail.jobposting.email")).getText());
        form.setMailFrom(           ((Configuration)service.getJRecruiterSetting("mail.from")).getText());
        form.setPasswordSubject(    ((Configuration)service.getJRecruiterSetting("mail.password.subject")).getText());
        form.setPasswordTemplate(   ((Configuration)service.getJRecruiterSetting("mail.password.body")).getText());

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
        configuration.setKey("mail.jobposting.subject");
        configuration.setText((String) settingsForm.getMailingListSubject());
        configuration.setLastModified(GregorianCalendar.getInstance().getTime());
        service.saveJRecruiterSetting(configuration);

        configuration = new Configuration();
        configuration.setKey("mail.jobposting.body");
        configuration.setText((String) settingsForm.getMailingListTemplate());
        configuration.setLastModified(GregorianCalendar.getInstance().getTime());
        service.saveJRecruiterSetting(configuration);

        configuration = new Configuration();
        configuration.setKey("mail.jobposting.email");
        configuration.setText((String) settingsForm.getMailingListEmail());
        configuration.setLastModified(GregorianCalendar.getInstance().getTime());
        service.saveJRecruiterSetting(configuration);

        configuration = new Configuration();
        configuration.setKey("mail.from");
        configuration.setText((String) settingsForm.getMailFrom());
        configuration.setLastModified(GregorianCalendar.getInstance().getTime());
        service.saveJRecruiterSetting(configuration);

        configuration = new Configuration();
        configuration.setKey("mail.password.subject");
        configuration.setText((String) settingsForm.getPasswordSubject());
        configuration.setLastModified(GregorianCalendar.getInstance().getTime());
        service.saveJRecruiterSetting(configuration);

        configuration = new Configuration();
        configuration.setKey("mail.password.body");
        configuration.setText((String) settingsForm.getPasswordTemplate());
        configuration.setLastModified(GregorianCalendar.getInstance().getTime());
        service.saveJRecruiterSetting(configuration);

        request.getSession().setAttribute("message",
                getText("settings.edit.success", ""));

        return new ModelAndView(getSuccessView());

	}

}
