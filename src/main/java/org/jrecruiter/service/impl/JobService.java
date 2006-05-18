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


import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.jrecruiter.Constants.StatsMode;
import org.jrecruiter.model.Configuration;
import org.jrecruiter.model.Job;
import org.jrecruiter.persistent.dao.JobsDAO;
import org.jrecruiter.persistent.dao.SettingsDAO;
import org.jrecruiter.service.JobServiceInterface;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * @author Jerzy Puchala
 * @version $Id$
 */
public class JobService implements JobServiceInterface {

    /**
     *   Initialize Logging.
     */
    private static final Logger LOGGER = Logger.getLogger(JobService.class);

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
    private JobsDAO jobsDao;

    /**
     * Settings Dao.
     */
    private SettingsDAO settingsDao;

    /**
     * @param jobsDao The jobsDao to set.
     */
    public void setJobsDao(final JobsDAO jobsDao) {
        this.jobsDao = jobsDao;
    }

    /**
     * @param settingsDao The settingsDao to set.
     */
    public final void setSettingsDao(final SettingsDAO settingsDao) {
        this.settingsDao = settingsDao;
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
        return jobsDao.getAllJobs();
    }

    /* (non-Javadoc)
     * @see org.ajug.service.JobServiceInterface#getUsersJobs(java.lang.String)
     */
    public List getUsersJobs(final String username) {
        return jobsDao.getAllUserJobs(username);
    }

    /* (non-Javadoc)
     * @see org.ajug.service.JobServiceInterface#getUsersJobs(java.lang.String)
     */
    public List getUsersJobsForStatistics(final String username) {
        return jobsDao.getAllUserJobsForStatistics(username);
    }

    /* (non-Javadoc)
     * @see org.jrecruiter.service.JobServiceInterface#getUsersJobsForStatistics(java.lang.String, java.lang.Integer, org.jrecruiter.Constants.StatsMode)
     */
    public List<Job> getUsersJobsForStatistics(String username, Integer maxResult, StatsMode statsMode) {
        return jobsDao.getUsersJobsForStatistics(username, maxResult, statsMode);
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
        jobsDao.update(jobs);
    }

    /* (non-Javadoc)
    * @see org.ajug.service.JobServiceInterface#getJobForId(java.lang.Long)
    */
    public Job getJobForId(final Long jobId) {

        return jobsDao.get(jobId);
    }

    public List searchByKeyword(final String keyword) {
        return jobsDao.searchByKeyword(keyword);
    }

    /* (non-Javadoc)
     * @see org.ajug.service.JobServiceInterface#deleteJobForId(java.lang.Long)
     */
    public void deleteJobForId(final Long jobId) {
        jobsDao.delete(jobId);
    }


    /* (non-Javadoc)
     * @see org.ajug.service.JobServiceInterface#sendJobPostingToMailingList(java.lang.Long)
     */
    public void sendJobPostingToMailingList(final Job jobs) {

        final SimpleMailMessage msg = new SimpleMailMessage(this.message);
        msg.setTo(settingsDao.get("mail.jobposting.email").getText());

        final Map < String,Object > model = new HashMap < String,Object > ();

        model.put("jobId", jobs.getId());
        model.put("jobTitle", jobs.getJobTitle());
        model.put("businessLocation", jobs.getBusinessLocation());
        model.put("businessName", jobs.getBusinessName());
        model.put("description", jobs.getDescription());
        model.put("jobRestrictions", jobs.getJobRestrictions());
        model.put("updateDate", jobs.getUpdateDate());

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
        msg.setFrom(settingsDao.get("mail.from").getText());

        try {
            mailSender.send(msg);
        } catch (MailException ex) {
            LOGGER.error(ex.getMessage());
            throw new RuntimeException(ex);
        }

    }

    public List getJRecruiterSettings() {
        return settingsDao.getAllConfigurations();
    }

    public Configuration getJRecruiterSetting(final String key) {
        return settingsDao.get(key);
    }

    public void saveJRecruiterSetting(final Configuration configuration) {
        settingsDao.update(configuration);

    }


}
