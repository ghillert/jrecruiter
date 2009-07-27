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

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jrecruiter.common.AcegiUtil;
import org.jrecruiter.common.CalendarUtils;
import org.jrecruiter.common.CollectionUtils;
import org.jrecruiter.common.Constants.Roles;
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
import org.jrecruiter.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
@Transactional
@Service("jobService")
public class JobServiceImpl implements JobService {

    /** Initialize Logging. */
    private final static Logger LOGGER = LoggerFactory.getLogger(JobServiceImpl.class);

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

    private @Autowired NotificationService  notificationService;

    //~~~~~Business Methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
    public List<Job> getJobs() {
        return jobDao.getAllJobs();
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
    public List<Job> getJobs(Integer pageSize, Integer pageNumber, Map<String, String> sortOrders, Map<String, String> jobFilters) {
        return jobDao.getJobs(pageSize, pageNumber, sortOrders, jobFilters);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
    public Long getJobsCount() {
        return jobDao.getJobsCount();
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
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

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
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

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
    public List<Job> getUsersJobsForStatistics(String username, Integer maxResult) {

        final User user = userDao.getUser(username);

        if (user == null) {
            throw new IllegalArgumentException("No user found for username " + username);
        }

        boolean administrator = false;

        if (AcegiUtil.hasRole(Roles.ADMIN.name())) {
            administrator = true;
        }

        return jobDao.getUsersJobsForStatistics(user.getId(), maxResult, administrator);
    }

    /** {@inheritDoc} */
    public Job addJob(final Job job) {

        if (job.getStatistic() == null) {
            Statistic statistic = new Statistic(job.getId(), Long.valueOf(0), null, Long.valueOf(0));
            statistic.setJob(job);
            job.setStatistic(statistic);
        }

        final Job savedJob = jobDao.save(job);

        saveJobStatistics(job);

        return savedJob;
    }

    /** {@inheritDoc} */
    public void addJobAndSendToMailingList(final Job job) {
        this.addJob(job);

        final Map<String, Object> context = CollectionUtils.getHashMap();
        context.put("jobId", job.getId());
        context.put("jobTitle", job.getJobTitle());
        context.put("businessLocation", job.getRegionOther());
        context.put("businessName", job.getBusinessName());
        context.put("description", job.getDescription());
        context.put("jobRestrictions", job.getJobRestrictions());
        context.put("updateDate", job.getUpdateDate());
        context.put("businessEmail", job.getBusinessEmail());

        notificationService.sendEmail("gunnar@hillert.com", "[ajug-jobs] " + job.getJobTitle(), context, "add-job");
        final String tweetMessage = "New Job: " + job.getJobTitle() + " @ " + job.getBusinessName();

        URL url = notificationService.shortenUrl("http://www.google.com/");
        notificationService.sendTweetToTwitter(tweetMessage + "; " + url.toString());

    }

    /** {@inheritDoc} */
    public void updateJob(final Job job) {

        if (job.getStatistic() == null) {
            Statistic statistic = new Statistic(job.getId(), Long.valueOf(0), null, Long.valueOf(0));
            statistic.setJob(job);
            job.setStatistic(statistic);
        }

        final Job savedJob = jobDao.save(job);

        saveJobStatistics(savedJob);

        final Map<String, Object> context = CollectionUtils.getHashMap();
        context.put("jobId", job.getId());
        context.put("jobTitle", job.getJobTitle());
        context.put("businessLocation", job.getRegionOther());
        context.put("businessName", job.getBusinessName());
        context.put("description", job.getDescription());
        context.put("jobRestrictions", job.getJobRestrictions());
        context.put("updateDate", job.getUpdateDate());
        context.put("businessEmail", job.getBusinessEmail());

        notificationService.sendEmail("gunnar@hillert.com", "[ajug-jobs] " + job.getJobTitle(), context, "add-job");
        String tweetMessage = "Job Update: " + job.getJobTitle() + " @ " + job.getBusinessName();

        URL url = notificationService.shortenUrl("http://www.google.com/");

        tweetMessage = tweetMessage + ": " + url.toString();
        notificationService.sendTweetToTwitter(tweetMessage);

    }

    /** {@inheritDoc} */
    public void saveJobStatistics(Job job) {

        if (job.getId() == null) {

            JobCountPerDay jobCountPerDay = jobCountPerDayDao.getByDate(job.getRegistrationDate());

            if (jobCountPerDay == null) {
                jobCountPerDay = new JobCountPerDay();
                jobCountPerDay.setJobDate(job.getRegistrationDate());
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
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
    public Job getJobForId(final Long jobId) {
        return jobDao.get(jobId);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
    public List<Job> searchByKeyword(final String keyword) {
        return jobDao.searchByKeyword(keyword);
    }

    /** {@inheritDoc} */
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

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
    public List<Configuration> getJRecruiterSettings() {
        return configurationDao.getAll();
    }

    /** {@inheritDoc} */
    public Configuration getJRecruiterSetting(final String key) {
        return configurationDao.get(key);
    }

    /** {@inheritDoc} */
    public void saveJRecruiterSetting(final Configuration configuration) {
        configurationDao.save(configuration);

    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
    public List<Industry> getIndustries() {
        return this.industryDao.getAllIndustriesOrdered();
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
    public List<Region> getRegions() {
        return this.regionDao.getAllRegionsOrdered();
    }

    /** {@inheritDoc} */
    public void updateJobStatistic(Statistic statistics) {
            this.statisticDao.save(statistics);
    }

    /** {@inheritDoc} */
    public void reindexSearch() {
        jobDao.reindexSearch();
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
    public Industry getIndustry(Long industryId) {
        return industryDao.get(industryId);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
    public Region getRegion(Long regionId) {
        return regionDao.get(regionId);
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
    public List<JobCountPerDay> getJobCountPerDayAndPeriod(final Date fromDate, final Date toDate) {

        //Make sure we have values at least for today and the previous day.
        this.updateJobCountPerDays();

        final List<JobCountPerDay> jobCountPerDays = jobCountPerDayDao.getJobCountPerDayAndPeriod(fromDate, toDate);
        return jobCountPerDays;
    }

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
    public Long jobCount(final Date day) {
        return jobDao.getJobCount(day);
    }

    /** {@inheritDoc} */
    public void updateJobCountPerDays() {
        this.updateJobCountPerDays(CalendarUtils.getCalendarWithoutTime());
    }

    /** {@inheritDoc} */
    public void updateJobCountPerDays(final Calendar asOfDay) {

        final Calendar today = CalendarUtils.getCalendarWithoutTime();
        today.setTime(asOfDay.getTime());
        final Calendar yesterday = CalendarUtils.getCalendarWithoutTime();
        yesterday.add(Calendar.DAY_OF_YEAR, -1);

        final List<JobCountPerDay> latestTwoJobCountPerDays = jobCountPerDayDao.getLatestTwoJobCounts();

        //If nothing exists yet, create an entry with zero jobs.
        if (latestTwoJobCountPerDays.isEmpty()) {
            jobCountPerDayDao.save(new JobCountPerDay(yesterday.getTime(), 0L, 0L, 0L));
        }

        boolean containsToday = false;

        //Let's make sure we have a value for today
        for (JobCountPerDay jobCountPerDay : latestTwoJobCountPerDays) {
            if (today.getTime().equals(jobCountPerDay.getJobDate())) {
                containsToday = true;
                break;
            }
        }

        if (!containsToday) {
            //We need to create a value for today
            final Long totalNumberOfJobs = this.getJobsCount();
            jobCountPerDayDao.save(new JobCountPerDay(today.getTime(), 0L, 0L, totalNumberOfJobs));
        }


    }
}
