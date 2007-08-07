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
package org.jrecruiter.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.jrecruiter.Constants.Roles;
import org.jrecruiter.Constants.StatsMode;
import org.jrecruiter.dao.JobDao;
import org.jrecruiter.dao.ConfigurationDao;
import org.jrecruiter.dao.UserDao;
import org.jrecruiter.model.Configuration;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.User;
import org.jrecruiter.service.JobService;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
public class JobServiceImpl implements JobService {

    /**
     *   Initialize Logging.
     */
    private static final Logger LOGGER = Logger.getLogger(JobServiceImpl.class);

    /**
     *   Used for creating the Apache-Velocity-based Email template.
     */
    private VelocityEngine velocityEngine;

    /**
     * Mailsender.
     */
    private MailSender mailSender;

    /**
     * Email message.
     */
    private SimpleMailMessage message;

    /**
     * Job Dao.
     */
    private JobDao jobDao;

    /**
     * User Dao.
     */
    private UserDao userDao;

    /**
     * Settings Dao.
     */
    private ConfigurationDao configurationDao;

    /**
     * @param jobDao The jobDao to set.
     */
    public void setJobDao(final JobDao jobDao) {
        this.jobDao = jobDao;
    }

    /**
     * @param settingsDao The settingsDao to set.
     */
    public final void setConfigurationDao(final ConfigurationDao configurationDao) {
        this.configurationDao = configurationDao;
    }

    /**
     * Sets the mail sender.
     * @param mailSender
     */
    public void setMailSender(final MailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     *
     * Sets the Email message.
     *
     * @param message
     */
    public void setMessage(SimpleMailMessage message) {
        this.message = message;
    }

    /**
     * Sets the VelocityEngine.
     *
     * @param velocityEngine
     */
    public void setVelocityEngine(final VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    /* (non-Javadoc)
     * @see org.ajug.service.JobServiceInterface#getJobs()
     */
    public List getJobs() {
        return jobDao.getAllJobs();
    }

    /* (non-Javadoc)
     * @see org.jrecruiter.service.JobService#getJobs(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public List<Job> getJobs(Integer pageSize, Integer pageNumber, String fieldSorted, String sortOrder) {
        return jobDao.getJobs(pageSize, pageNumber, fieldSorted, sortOrder);
    }

    /* (non-Javadoc)
     * @see org.jrecruiter.service.JobService#getJobsCount()
     */
    public Integer getJobsCount() {
        return jobDao.getJobsCount();
    }

    /* (non-Javadoc)
     * @see org.ajug.service.JobServiceInterface#getUsersJobs(java.lang.String)
     */
    public List getUsersJobs(final String username) {

    	List <Job> jobs = null;
        User user = userDao.getUser(username);

        boolean administrator = false;

        int index = Arrays.binarySearch(user.getAuthorities(), Roles.ROLE_ADMIN.name());

        if (index >= 0) {
        	administrator = true;
        }

        if (administrator) {
            jobs = jobDao.getAllJobs();
        } else {
        	jobs = jobDao.getAllUserJobs(username);
        }

        return jobs;
    }

    /* (non-Javadoc)
     * @see org.ajug.service.JobServiceInterface#getUsersJobs(java.lang.String)
     */
    public List getUsersJobsForStatistics(final String username) {
        return jobDao.getAllUserJobsForStatistics(username);
    }

    /* (non-Javadoc)
     * @see org.jrecruiter.service.JobService#getUsersJobsForStatistics(java.lang.String, java.lang.Integer, org.jrecruiter.Constants.StatsMode)
     */
    public List<Job> getUsersJobsForStatistics(String username, Integer maxResult, StatsMode statsMode) {
        return jobDao.getUsersJobsForStatistics(username, maxResult, statsMode);
    }

    /* (non-Javadoc)
    * @see org.ajug.service.JobServiceInterface#addJob(org.jrecruiter.persistent.pojo.Job)
    */
    public void addJob(final Job jobs) {
        jobs.setId(null);
        updateJob(jobs);
    }

    /* (non-Javadoc)
    * @see org.ajug.service.JobServiceInterface#addJob(org.jrecruiter.persistent.pojo.Job)
    */
    public void updateJob(final Job jobs) {
        jobDao.update(jobs);
    }

    /* (non-Javadoc)
    * @see org.ajug.service.JobServiceInterface#getJobForId(java.lang.Long)
    */
    public Job getJobForId(final Long jobId) {

        return jobDao.get(jobId);
    }

    public List searchByKeyword(final String keyword) {
        return jobDao.searchByKeyword(keyword);
    }

    /* (non-Javadoc)
     * @see org.ajug.service.JobServiceInterface#deleteJobForId(java.lang.Long)
     */
    public void deleteJobForId(final Long jobId) {
        jobDao.remove(jobId);
    }


    /* (non-Javadoc)
     * @see org.ajug.service.JobServiceInterface#sendJobPostingToMailingList(java.lang.Long)
     */
    public void sendJobPostingToMailingList(final Job jobs) {

        final SimpleMailMessage msg = new SimpleMailMessage(this.message);
        msg.setTo(configurationDao.get("mail.jobposting.email").getMessageText());

        final Map < String,Object > model = new HashMap < String,Object > ();

        model.put("jobId", jobs.getId());
        model.put("jobTitle", jobs.getJobTitle());
        model.put("businessLocation", jobs.getBusinessLocation());
        model.put("businessName", jobs.getBusinessName());
        model.put("description", jobs.getDescription());
        model.put("jobRestrictions", jobs.getJobRestrictions());
        model.put("updateDate", jobs.getUpdateDate());
        model.put("businessEmail", jobs.getBusinessEmail());

        String result = null;
        String subject = null;
        try {

            result = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
                   "mail.jobposting.body", model);
            subject = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
                    "mail.jobposting.subject", model);
        } catch (VelocityException e) {
            e.printStackTrace();
        }

        msg.setText(result);

        msg.setSubject(subject);
        msg.setFrom(configurationDao.get("mail.from").getMessageText());

        try {
            mailSender.send(msg);
        } catch (MailException ex) {
            LOGGER.error(ex.getMessage());
            throw new RuntimeException(ex);
        }

    }

    public List getJRecruiterSettings() {
        return configurationDao.getAll();
    }

    public Configuration getJRecruiterSetting(final String key) {
        return configurationDao.get(key);
    }

    public void saveJRecruiterSetting(final Configuration configuration) {
        configurationDao.update(configuration);

    }

	/**
	 * @param userDao the userDao to set
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


}
