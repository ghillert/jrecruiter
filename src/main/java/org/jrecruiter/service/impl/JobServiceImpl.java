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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.jrecruiter.common.AcegiUtil;
import org.jrecruiter.common.Constants.Roles;
import org.jrecruiter.common.Constants.StatsMode;
import org.jrecruiter.dao.ConfigurationDao;
import org.jrecruiter.dao.IndustryDao;
import org.jrecruiter.dao.JobCountPerDayDao;
import org.jrecruiter.dao.JobDao;
import org.jrecruiter.dao.RegionDao;
import org.jrecruiter.dao.StatisticDao;
import org.jrecruiter.dao.UserDao;
import org.jrecruiter.model.Configuration;
import org.jrecruiter.model.Industry;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.Region;
import org.jrecruiter.model.Statistic;
import org.jrecruiter.model.User;
import org.jrecruiter.model.statistics.JobCountPerDay;
import org.jrecruiter.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
@Transactional
@Service("jobService")
public class JobServiceImpl implements JobService {

    /** Initialize Logging. */
    private final static Logger LOGGER = LoggerFactory.getLogger(JobServiceImpl.class);

    /** Used for creating the Apache-Velocity-based Email template. */
    private @Autowired VelocityEngine velocityEngine;

    /** Mailsender. */
    private @Autowired MailSender mailSender;

    /** Email message. */
    private @Autowired SimpleMailMessage message;

    /** Job Dao. */
    private @Autowired JobDao jobDao;

    /** Job Count Per Day Dao. */
    private @Autowired JobCountPerDayDao jobCountPerDayDao;
    
    /** User Dao. */
    private @Autowired UserDao userDao;

    /** Industry Dao. */
    private @Autowired IndustryDao industryDao;

    /** User Region Dao. */
    private @Autowired RegionDao regionDao;

    /** Statistic Dao. */
    private @Autowired StatisticDao statisticDao;

    /** Settings Dao. */
    private @Autowired ConfigurationDao configurationDao;


    //~~~~~Business Methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /* (non-Javadoc)
     * @see org.ajug.service.JobServiceInterface#getJobs()
     */
    public List<Job> getJobs() {
        return jobDao.getAllJobs();
    }

    /* (non-Javadoc)
     * @see org.jrecruiter.service.JobService#getJobs(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public List<Job> getJobs(Integer pageSize, Integer pageNumber, Map<String, String> sortOrders, Map<String, String> jobFilters) {
        return jobDao.getJobs(pageSize, pageNumber, sortOrders, jobFilters);
    }

    /* (non-Javadoc)
     * @see org.jrecruiter.service.JobService#getJobsCount()
     */
    public Long getJobsCount() {
        return jobDao.getJobsCount();
    }

    /* (non-Javadoc)
     * @see org.ajug.service.JobServiceInterface#getUsersJobs(java.lang.String)
     */
    public List<Job> getUsersJobs(final String username) {

        List <Job> jobs = null;
        User user = userDao.getUser(username);

        boolean administrator = false;

        if (AcegiUtil.containsRole(user.getAuthorities(), Roles.ADMIN.name())) {
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
    public List<Job> getUsersJobsForStatistics(final String username) {

        final User user = userDao.getUser(username);

        if (user == null) {
            throw new IllegalArgumentException("No user found for username " + username);
        }

        if (AcegiUtil.hasRole(Roles.ADMIN.name())) {
            return jobDao.getAll();
        }

        return jobDao.getAllUserJobsForStatistics(user.getId());
    }

    /* (non-Javadoc)
     * @see org.jrecruiter.service.JobService#getUsersJobsForStatistics(java.lang.String, java.lang.Integer, org.jrecruiter.Constants.StatsMode)
     */
    public List<Job> getUsersJobsForStatistics(String username, Integer maxResult, StatsMode statsMode) {

        final User user = userDao.getUser(username);

        if (user == null) {
            throw new IllegalArgumentException("No user found for username " + username);
        }

        boolean administrator = false;

        if (AcegiUtil.hasRole(Roles.ADMIN.name())) {
            administrator = true;
        }

        return jobDao.getUsersJobsForStatistics(user.getId(), maxResult, statsMode, administrator);
    }

    /* (non-Javadoc)
    * @see org.ajug.service.JobServiceInterface#addJob(org.jrecruiter.persistent.pojo.Job)
    */
    public Job addJob(final Job job) {
    	
    	if (job.getStatistic() == null) {
    		Statistic statistic = new Statistic(job.getId(), Long.valueOf(0), null, Long.valueOf(0));
    		statistic.setJob(job);
    		job.setStatistic(statistic);
    	}
    	
    	final Job savedJob = jobDao.save(job);
    		
    	saveJobStatistics(savedJob);
    	
        return savedJob;
    }
    
    private void saveJobStatistics(Job job) {
    	
    	Date currentDate = new Date();
    	
    	JobCountPerDay jobCountPerDay = jobCountPerDayDao.getByDate(currentDate);
    	
    	if (jobCountPerDay == null) {
    		jobCountPerDay = new JobCountPerDay();
    		jobCountPerDay.setJobDate(new Date());
    		jobCountPerDay.setNumberOfJobsDeleted(Long.valueOf(0));
    		jobCountPerDay.setNumberOfJobsPosted(Long.valueOf(1));
    		jobCountPerDay.setTotalNumberOfJobs(jobDao.getJobsCount());
    		jobCountPerDayDao.save(jobCountPerDay);
    	} else {
    		jobCountPerDay.setNumberOfJobsPosted(jobCountPerDay.getNumberOfJobsPosted() + 1);
    		jobCountPerDay.setTotalNumberOfJobs(jobDao.getJobsCount() + 1);
    		jobCountPerDayDao.save(jobCountPerDay);
    	}
    }
    
    /* (non-Javadoc)
    * @see org.ajug.service.JobServiceInterface#addJob(org.jrecruiter.persistent.pojo.Job)
    */
    public void updateJob(final Job job) {
    	
    	if (job.getStatistic() == null) {
    		Statistic statistic = new Statistic(job.getId(), Long.valueOf(0), null, Long.valueOf(0));
    		statistic.setJob(job);
    		job.setStatistic(statistic);
    	}
    	
        final Job savedJob = jobDao.save(job);
        
        saveJobStatistics(savedJob);
    }

    /* (non-Javadoc)
    * @see org.ajug.service.JobServiceInterface#getJobForId(java.lang.Long)
    */
    public Job getJobForId(final Long jobId) {

        return jobDao.get(jobId);
    }

    public List<Job> searchByKeyword(final String keyword) {
        return jobDao.searchByKeyword(keyword);
    }

    /* (non-Javadoc)
     * @see org.ajug.service.JobServiceInterface#deleteJobForId(java.lang.Long)
     */
    public void deleteJobForId(final Long jobId) {
        jobDao.remove(jobId);
        
	  	JobCountPerDay jobCountPerDay = jobCountPerDayDao.getByDate(new Date());
      
		if (jobCountPerDay == null) {
			jobCountPerDay = new JobCountPerDay();
			jobCountPerDay.setJobDate(new Date());
			jobCountPerDay.setNumberOfJobsDeleted(Long.valueOf(1));
			jobCountPerDay.setNumberOfJobsPosted(Long.valueOf(0));
			jobCountPerDay.setTotalNumberOfJobs(jobDao.getJobsCount() - 1 );
			jobCountPerDayDao.save(jobCountPerDay);
		} else {
			jobCountPerDay.setNumberOfJobsPosted(jobCountPerDay.getNumberOfJobsPosted() -1);
			jobCountPerDay.setTotalNumberOfJobs(jobDao.getJobsCount() -1 );
			jobCountPerDayDao.save(jobCountPerDay);
		}
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
        model.put("businessLocation", jobs.getRegionOther());
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
            throw new IllegalStateException(ex);
        }

    }

    public List<Configuration> getJRecruiterSettings() {
        return configurationDao.getAll();
    }

    public Configuration getJRecruiterSetting(final String key) {
        return configurationDao.get(key);
    }

    public void saveJRecruiterSetting(final Configuration configuration) {
        configurationDao.save(configuration);

    }

    public List<Industry> getIndustries() {
        return this.industryDao.getAllIndustriesOrdered();
    }

    public List<Region> getRegions() {
        return this.regionDao.getAllRegionsOrdered();
    }

    public void updateJobStatistic(Statistic statistics) {
        if (statistics.getId() != null) {
            this.statisticDao.save(statistics);
        } else {
            this.statisticDao.save(statistics);
        }

    }

    public void reindexSearch() {
        jobDao.reindexSearch();
    }

    public Industry getIndustry(Long industryId) {
        return industryDao.get(industryId);
    }

    public Region getRegion(Long regionId) {
        return regionDao.get(regionId);
    }

    public List<JobCountPerDay> getJobCountPerDayAndPeriod(Date fromDate, Date toDate) {
        return jobCountPerDayDao.getJobCountPerDayAndPeriod(fromDate, toDate);
    }

    public Long jobCount(Date day) {
        return jobDao.getJobCount(day);
    }



}
