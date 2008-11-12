/**
 *
 */
package org.jrecruiter.dao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jrecruiter.common.Constants.JobStatus;
import org.jrecruiter.common.Constants.OfferedBy;
import org.jrecruiter.common.Constants.StatsMode;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.Statistic;
import org.jrecruiter.model.User;
import org.jrecruiter.model.statistics.JobCountPerDay;
import org.jrecruiter.test.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
public class JobDaoTest extends BaseTest {

    private @Autowired JobDao jobDao;
    private @Autowired UserDao userDao;

    /** Statis Dao */
    private @Autowired StatisticDao statisticDao;

    /**
     *   Initialize Logging.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(JobDaoTest.class);

    public void testGetJobsPaginated() {

        List<Job> jobs = jobDao.getJobs(20, 1, null, null);

        for (Job job : jobs) {

            LOGGER.info(job.getId().toString());

        }
    }

    //FIXME
//    public void testSearchByKeyword() {
//        jobDao.searchByKeyword("java");
//    }

    public void testSaveJobWithStatistic() {
        final Job job = this.getJob();

        Statistic statistic = new Statistic();
        statistic.setJob(job);
        statistic.setCounter(new Long(0));
        statistic.setUniqueVisits(10L);
        statistic.setLastAccess(new Date());

        User user = this.getUser();
        User savedUser = userDao.save(user);

        job.setStatistic(statistic);
        job.setUser(savedUser);

        Job savedJob = jobDao.save(job);
        assertNotNull(savedJob.getId());
    }

    public void testGetJob() {

        final Job job = this.getJob();

        User user = this.getUser();
        User savedUser = userDao.save(user);
        entityManager.flush();
        job.setUser(savedUser);
        Job savedJob = jobDao.save(job);

        Job job2 = jobDao.get(savedJob.getId());

        assertNotNull(job2);
    }

    public void testGetNonExistingJob() {

        assertNull(jobDao.get(9999999999999L));
    }

    public void testDoesJobExist() {

        final Job job = this.getJob();

        User user = this.getUser();
        User savedUser = userDao.save(user);

        job.setUser(savedUser);

        assertFalse(jobDao.exists(9999999L));
        Job savedJob = jobDao.save(job);
        assertNotNull(savedJob.getId());

        assertTrue(jobDao.exists(savedJob.getId()));
    }

    public void testGetAllUserJobs() {

        final Job job = this.getJob();
        final User user = this.getUser();

        User savedUser = userDao.save(user);
        entityManager.flush();

        job.setUser(savedUser);
        jobDao.save(job);

        List <Job> jobs = jobDao.getAllUserJobs("demo44");

        assertNotNull(jobs);
        assertTrue(jobs.size()>0);
    }

    private User getUser() {

        User user = new User();
        user.setUsername("demo44");
        user.setEmail("demo@demo.com");
        user.setFirstName("Demo First Name");
        user.setLastName("Demo Last Name");
        user.setPassword("demo");
        user.setPhone("123456");
        user.setRegistrationDate(new Date());

        return user;

    }

    private Job getJob() {

        Job job = new Job();
        job.setBusinessAddress1("businessAddress1");
        job.setBusinessAddress2("businessAddress2");
        job.setBusinessCity("businessCity");
        job.setBusinessEmail("businessEmail");
        job.setRegionOther("businessLocation");
        job.setBusinessName("businessName");
        job.setBusinessPhone("businessPhone");
        job.setBusinessState("businessState");
        job.setBusinessZip("businessZip");
        job.setDescription("description");

        job.setJobRestrictions("jobRestrictions");
        job.setJobTitle("jobTitle");
        job.setLatitude(BigDecimal.ONE);
        job.setLongitude(BigDecimal.ZERO);
        job.setOfferedBy(OfferedBy.RECRUITER);
        job.setRegistrationDate(new Date());
        job.setSalary(new BigDecimal(10000));
        job.setStatus(JobStatus.ACTIVE);
        job.setUpdateDate(new Date());
        job.setWebsite("www.google.com");

        return job;

    }

    public void testGetAllJobs() {

        final Job job = this.getJob();

        User user = this.getUser();
        User savedUser = userDao.save(user);

        job.setUser(savedUser);
        jobDao.save(job);

        List <Job> jobs = jobDao.getAllJobs();

        assertTrue(jobs.size() > 0);

    }

    public void testGetAllUserJobsForStatistics() {
        final Job job = this.getJob();
        final User user = this.getUser();

        User savedUser = userDao.save(user);

        job.setUser(savedUser);
        jobDao.save(job);

        List <Job> jobs = jobDao.getAllUserJobsForStatistics(savedUser.getId());

        assertNotNull(jobs);
        assertTrue(jobs.size()>0);
    }

    public void testGetUsersJobsForStatistics() {
        final Job job = this.getJob();
        final User user = this.getUser();

        User savedUser = userDao.save(user);
        job.setUser(savedUser);


        Statistic statistic = new Statistic();

        statistic.setJob(job);
        statistic.setCounter(new Long(0));
        statistic.setUniqueVisits(10L);
        statistic.setLastAccess(new Date());
        job.setStatistic(statistic);

        jobDao.save(job);
        entityManager.flush();


        List <Job> jobs = jobDao.getUsersJobsForStatistics(savedUser.getId(), 5, StatsMode.PAGE_HITS, false);

        assertNotNull(jobs);
        assertTrue(jobs.size()>0);

        List <Job> jobs2 = jobDao.getUsersJobsForStatistics(savedUser.getId(), 5, StatsMode.UNIQUE_HITS, false);

        assertNotNull(jobs2);
        assertTrue(jobs2.size()>0);
    }

    public void testGetUsersJobsForStatisticsAdmin() {
        final Job job = this.getJob();
        final User user = this.getUser();

        User savedUser = userDao.save(user);
        job.setUser(savedUser);
        Job savedJob = jobDao.save(job);

        Statistic statistic = new Statistic();

        statistic.setJob(savedJob);
        statistic.setCounter(new Long(0));
        statistic.setUniqueVisits(10L);
        statistic.setLastAccess(new Date());

        statisticDao.save(statistic);

        List <Job> jobs = jobDao.getUsersJobsForStatistics(savedUser.getId(), 5, StatsMode.PAGE_HITS, true);
        List <Job> jobs2 = jobDao.getUsersJobsForStatistics(savedUser.getId(), 5, StatsMode.UNIQUE_HITS, true);

        assertNotNull(jobs);
        assertTrue(jobs2.size()>0);

        assertNotNull(jobs2);
        assertTrue(jobs.size()>0);
    }

    public void testGetJobsCount() {
        final Job job = this.getJob();
        final User user = this.getUser();

        User savedUser = userDao.save(user);

        job.setUser(savedUser);
        jobDao.save(job);

        Long totalNumberOfJobs = jobDao.getJobsCount();

        assertNotNull(totalNumberOfJobs);
        assertTrue(totalNumberOfJobs.intValue() > 0);
    }

    public void testGetJobCountForDay() {
        Calendar cal1 = Calendar.getInstance();
        cal1.clear();
        cal1.set(1500, 05, 20);

        Calendar cal2 = Calendar.getInstance();
        cal2.clear();
        cal2.set(1500, 04, 20);

        Calendar cal3 = Calendar.getInstance();
        cal3.clear();
        cal3.set(1500, 03, 20);

        final User user = this.getUser();

        User savedUser = userDao.save(user);

        final Job job1 = this.getJob();
        job1.setUser(savedUser);
        job1.setRegistrationDate(cal1.getTime());
        jobDao.save(job1);

        final Job job2 = this.getJob();
        job2.setUser(savedUser);
        job2.setRegistrationDate(cal2.getTime());
        jobDao.save(job2);

        final Job job3 = this.getJob();
        job3.setUser(savedUser);
        job3.setRegistrationDate(cal3.getTime());
        jobDao.save(job3);

        entityManager.flush();

        Calendar queryDate = Calendar.getInstance();
        queryDate.clear();
        queryDate.set(1500, 4, 25);
        Long totalNumberOfJobs = jobDao.getJobCount(queryDate.getTime());

        assertNotNull(totalNumberOfJobs);
        assertTrue(totalNumberOfJobs.intValue() == 2);
    }

    public void testGetJobCountPerDayAndPeriod() {
        Calendar cal1 = Calendar.getInstance();
        cal1.clear();
        cal1.set(1500, 05, 20);

        Calendar cal2 = Calendar.getInstance();
        cal2.clear();
        cal2.set(1500, 04, 20);

        Calendar cal3 = Calendar.getInstance();
        cal3.set(1500, 04, 20);

        final User user = this.getUser();

        User savedUser = userDao.save(user);

        final Job job1 = this.getJob();
        job1.setUser(savedUser);
        job1.setRegistrationDate(cal1.getTime());
        jobDao.save(job1);

        final Job job2 = this.getJob();
        job2.setUser(savedUser);
        job2.setRegistrationDate(cal2.getTime());
        jobDao.save(job2);

        final Job job3 = this.getJob();
        job3.setUser(savedUser);
        job3.setRegistrationDate(cal3.getTime());
        jobDao.save(job3);

        entityManager.flush();

        List<JobCountPerDay> jobCountPerDay = jobDao.getJobCountPerDayAndPeriod(cal3.getTime(), cal1.getTime());

        assertNotNull(jobCountPerDay);
        assertTrue(jobCountPerDay.size() == 2);

    }

    public void setStatisticDao(StatisticDao statisticDao) {
        this.statisticDao = statisticDao;
    }

    public void testUpdateJob() {
        final Job job = this.getJob();
        final User user = this.getUser();

        User savedUser = userDao.save(user);
        job.setUser(savedUser);
        Job savedJob = jobDao.save(job);

        entityManager.flush();

        assertEquals("www.google.com", savedJob.getWebsite());

        savedJob.setWebsite("www.hillert.com");
        jobDao.save(savedJob);
        entityManager.flush();

        Job job2 = jobDao.get(savedJob.getId());
        assertEquals("www.hillert.com", job2.getWebsite());

    }


}
