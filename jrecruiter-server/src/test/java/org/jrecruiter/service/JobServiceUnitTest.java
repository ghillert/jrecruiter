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
package org.jrecruiter.service;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.jrecruiter.common.Constants;
import org.jrecruiter.common.Constants.JobStatus;
import org.jrecruiter.common.Constants.OfferedBy;
import org.jrecruiter.dao.ConfigurationDao;
import org.jrecruiter.dao.JobCountPerDayDao;
import org.jrecruiter.dao.JobDao;
import org.jrecruiter.dao.RegionDao;
import org.jrecruiter.dao.StatisticDao;
import org.jrecruiter.dao.UserDao;
import org.jrecruiter.model.Configuration;
import org.jrecruiter.model.Region;
import org.jrecruiter.model.Role;
import org.jrecruiter.model.ServerSettings;
import org.jrecruiter.model.Statistic;
import org.jrecruiter.model.User;
import org.jrecruiter.model.UserToRole;
import org.jrecruiter.model.statistics.JobCountPerDay;
import org.jrecruiter.model.Job;
import org.jrecruiter.service.impl.JobServiceImpl;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;


/**
 * Unit Test of the JobService class
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
public class JobServiceUnitTest extends TestCase {

    public void testDeleteJobForIdTest() throws Exception {

        JobServiceImpl jobService = new JobServiceImpl();

        final JobDao jobDao = Mockito.mock(JobDao.class);
        final JobCountPerDayDao jobCountPerDayDao = Mockito.mock(JobCountPerDayDao.class);

        ReflectionTestUtils.setField(jobService, "jobDao", jobDao, JobDao.class);
        ReflectionTestUtils.setField(jobService, "jobCountPerDayDao", jobCountPerDayDao, JobCountPerDayDao.class);

        jobDao.remove(1L);

        JobCountPerDay jobCountPerDay = new JobCountPerDay();

        Mockito.when(jobCountPerDayDao.getByDate((Date)Mockito.anyObject())).thenReturn(jobCountPerDay);

        Mockito.when(jobDao.getJobsCount()).thenReturn(10L);

        Mockito.when(jobCountPerDayDao.save(jobCountPerDay)).thenReturn(jobCountPerDay);

        final Job job = new Job();
        job.setId(1L);

        jobService.deleteJobForId(job.getId());

    }

    public void testGetJobs() throws Exception {

        List<Job> jobs = new ArrayList<Job>();

        for (long i=1; i<=10; i++) {
            jobs.add(this.getJob(i));
        }

        JobServiceImpl jobService = new JobServiceImpl();
        JobDao jobDao = Mockito.mock(JobDao.class);

        ReflectionTestUtils.setField(jobService, "jobDao", jobDao, JobDao.class);
        Mockito.when(jobDao.getAllJobs()).thenReturn(jobs);

        List<Job> jobsFromDb = jobService.getJobs();

        assertNotNull(jobsFromDb);
        assertTrue(jobsFromDb.size() == 10);

    }

    public void testGetIndustries() throws Exception {

        List<Job> jobs = new ArrayList<Job>();

        for (long i=1; i<=10; i++) {
            jobs.add(this.getJob(i));
        }

        JobServiceImpl jobService = new JobServiceImpl();
        JobDao jobDao = Mockito.mock(JobDao.class);

        ReflectionTestUtils.setField(jobService, "jobDao", jobDao, JobDao.class);
        Mockito.when(jobDao.getAllJobs()).thenReturn(jobs);

        List<Job> jobsFromDb = jobService.getJobs();

        assertNotNull(jobsFromDb);
        assertTrue(jobsFromDb.size() == 10);

    }

    public void testGetRegions() throws Exception {

        List<Region> regions = new ArrayList<Region>();

        for (long i=1; i<=10; i++) {
            regions.add(new Region(i, "Test_" + i));
        }

        JobServiceImpl jobService = new JobServiceImpl();
        RegionDao regionDao = Mockito.mock(RegionDao.class);

        ReflectionTestUtils.setField(jobService, "regionDao", regionDao, RegionDao.class);
        Mockito.when(regionDao.getAllRegionsOrdered()).thenReturn(regions);

        List<Region> regionsromDb = jobService.getRegions();

        assertNotNull(regionsromDb);
        assertTrue(regionsromDb.size() == 10);

    }

    public void testGetJobsForPagination() throws Exception {

        final List<Job> jobs = new ArrayList<Job>();

        for (long i=1; i<=10; i++) {
            jobs.add(this.getJob(i));
        }

        final JobServiceImpl jobService = new JobServiceImpl();
        final JobDao jobDao = Mockito.mock(JobDao.class);

        ReflectionTestUtils.setField(jobService, "jobDao", jobDao, JobDao.class);
        Mockito.when(jobDao.getJobs(5, 1, null, null)).thenReturn(jobs);

        final List<Job> jobsFromDb = jobService.getJobs(5, 1, null, null);

        assertNotNull(jobsFromDb);
        assertTrue(jobsFromDb.size() == 10);

    }

    public void testGetJobsCountTest() throws Exception {
        final JobServiceImpl jobService = new JobServiceImpl();

        final JobDao jobDao = Mockito.mock(JobDao.class);
        ReflectionTestUtils.setField(jobService, "jobDao", jobDao, JobDao.class);

        Mockito.when(jobDao.getJobsCount()).thenReturn(10L);

        final Long jobsCounted = jobService.getJobsCount();

        assertEquals(Long.valueOf(10), jobsCounted);
    }

    public void testGetJRecruiterSettings() throws Exception {

        final JobServiceImpl jobService = new JobServiceImpl();

        List<Configuration>settings = new ArrayList<Configuration>();

        for (long i=1; i<=10; i++) {
            Configuration configuration = new Configuration("configuration_" + i);
            settings.add(configuration);
        }

        final ConfigurationDao configurationDao = Mockito.mock(ConfigurationDao.class);
        Mockito.when(configurationDao.getAll()).thenReturn(settings);

        ReflectionTestUtils.setField(jobService, "configurationDao", configurationDao, ConfigurationDao.class);

        List<Configuration>settings2 = jobService.getJRecruiterSettings();

        assertNotNull(settings2);
        assertTrue(settings2.size() == 10);
    }

    public void testGetJRecruiterSetting() throws Exception {

        final JobServiceImpl jobService = new JobServiceImpl();

        Configuration configuration = new Configuration();

        final ConfigurationDao configurationDao = Mockito.mock(ConfigurationDao.class);
        Mockito.when(configurationDao.get("key")).thenReturn(configuration);

        ReflectionTestUtils.setField(jobService, "configurationDao", configurationDao, ConfigurationDao.class);

        Configuration configuration2 = jobService.getJRecruiterSetting("key");

        assertNotNull(configuration2);
    }

    public void testSaveJRecruiterSetting() throws Exception {
        final JobServiceImpl jobService = new JobServiceImpl();
        Configuration configuration = new Configuration();

        final ConfigurationDao configurationDao = Mockito.mock(ConfigurationDao.class);
        Mockito.when(configurationDao.save(configuration)).thenReturn(configuration);

        ReflectionTestUtils.setField(jobService, "configurationDao", configurationDao, ConfigurationDao.class);

        jobService.saveJRecruiterSetting(configuration);
    }

    public void testGetUsersJobsTestAsAdminUser() throws Exception {

        final User user = this.getUser();
        user.getUserToRoles().add(new UserToRole(1L, new Role(1L, Constants.Roles.ADMIN.name()), user));

        final JobServiceImpl jobService = new JobServiceImpl();

        final JobDao jobDao = Mockito.mock(JobDao.class);

        ReflectionTestUtils.setField(jobService, "jobDao", jobDao, JobDao.class);

        final UserDao userDao = Mockito.mock(UserDao.class);
        ReflectionTestUtils.setField(jobService, "userDao", userDao, UserDao.class);

        Mockito.when(userDao.getUser("demo44")).thenReturn(user);

        final List<Job>adminJobs = new ArrayList<Job>();

        for (long i=1; i<=10; i++) {
            Job job = new Job(i);
            adminJobs.add(job);
        }

        Mockito.when(jobDao.getAllJobs()).thenReturn(adminJobs);

        final List<Job>jobsFromService = jobService.getUsersJobs("demo44");

        assertTrue(jobsFromService.size() == 10);

    }

    public void testGetUsersJobsAsNormalUser() throws Exception {

        final User user = this.getUser();
        user.getUserToRoles().add(new UserToRole(1L, new Role(1L, Constants.Roles.MANAGER.name()), user));

        final JobServiceImpl jobService = new JobServiceImpl();

        final JobDao jobDao = Mockito.mock(JobDao.class);
        ReflectionTestUtils.setField(jobService, "jobDao", jobDao, JobDao.class);

        final UserDao userDao = Mockito.mock(UserDao.class);
        ReflectionTestUtils.setField(jobService, "userDao", userDao, UserDao.class);

        Mockito.when(userDao.getUser("demo44")).thenReturn(user);

        final List<Job>userJobs = new ArrayList<Job>();

        for (long i=1; i<=5; i++) {
            Job job = new Job(i);
            userJobs.add(job);
        }

        Mockito.when(jobDao.getAllUserJobs("demo44")).thenReturn(userJobs);

        final List<Job>jobsFromService = jobService.getUsersJobs("demo44");

        assertTrue(jobsFromService.size() == 5);

    }

    public void testGetUsersJobsForStatistics() throws Exception {

        final User user = this.getUser();
        user.getUserToRoles().add(new UserToRole(1L, new Role(1L, Constants.Roles.MANAGER.name()), user));

        final JobServiceImpl jobService = new JobServiceImpl();

        final JobDao jobDao = Mockito.mock(JobDao.class);
        ReflectionTestUtils.setField(jobService, "jobDao", jobDao, JobDao.class);

        final UserDao userDao = Mockito.mock(UserDao.class);
        ReflectionTestUtils.setField(jobService, "userDao", userDao, UserDao.class);

        Mockito.when(userDao.getUser("demo44")).thenReturn(user);

        final List<Job>jobs = new ArrayList<Job>();

        for (long i=1; i<=5; i++) {
            Job job = new Job(i);
            jobs.add(job);
        }

        Mockito.when(jobDao.getAllUserJobsForStatistics(user.getId())).thenReturn(jobs);

        final List<Job>jobs2 = jobService.getUsersJobsForStatistics("demo44");

        assertNotNull(jobs2);
        assertTrue(jobs2.size() == 5);

    }


    public void testAddJob() throws Exception {

        final JobServiceImpl jobService = new JobServiceImpl();

        Job job = this.getJob(1L);

        final JobDao jobDao = Mockito.mock(JobDao.class);
        final JobCountPerDayDao jobCountPerDayDao = Mockito.mock(JobCountPerDayDao.class);


        Mockito.when(jobDao.save(job)).thenReturn(job);

        JobCountPerDay jobCountPerDay = new JobCountPerDay();

        Mockito.when(jobCountPerDayDao.getByDate((Date)Mockito.anyObject())).thenReturn(jobCountPerDay);

        Mockito.when(jobDao.getJobsCount()).thenReturn(10L);

        Mockito.when(jobCountPerDayDao.save(jobCountPerDay)).thenReturn(jobCountPerDay);

        ReflectionTestUtils.setField(jobService, "jobDao", jobDao, JobDao.class);
        ReflectionTestUtils.setField(jobService, "jobCountPerDayDao", jobCountPerDayDao, JobCountPerDayDao.class);

        jobService.addJob(job);

    }

//    public void testGetJobForId() throws Exception {
//
//        final JobServiceImpl jobService = new JobServiceImpl();
//
//        Job job = this.getJob(1L);
//
//        final JobDao jobDao = Mockito.mock(JobDao.class);
//        Mockito.when(jobDao.save(job)).thenReturn(job);
//        ReflectionTestUtils.setField(jobService, "jobDao", jobDao, JobDao.class);
//
//
//
//        Mockito.replay(jobDao);
//
//        jobService.addJob(job);
//
//    }


    public void testUpdateJobTest() throws Exception {
        final JobServiceImpl jobService = new JobServiceImpl();

        final Job job = this.getJob(1L);

        final JobDao jobDao = Mockito.mock(JobDao.class);
        final JobCountPerDayDao jobCountPerDayDao = Mockito.mock(JobCountPerDayDao.class);
        final NotificationService notificationService = Mockito.mock(NotificationService.class);
        final ServerSettings serverSettings = Mockito.mock(ServerSettings.class);

        Mockito.when(jobDao.save(job)).thenReturn(job);

        JobCountPerDay jobCountPerDay = new JobCountPerDay();

        Mockito.when(jobCountPerDayDao.getByDate((Date)Mockito.anyObject())).thenReturn(jobCountPerDay);

        Mockito.when(jobDao.getJobsCount()).thenReturn(10L);

        Mockito.when(jobCountPerDayDao.save(jobCountPerDay)).thenReturn(jobCountPerDay);

        Mockito.when(serverSettings.getServerAddress()).thenReturn("test");

        Mockito.when(notificationService.shortenUrl((String)Mockito.anyObject())).thenReturn((new URL("http://www.google.com")));

        notificationService.sendTweetToTwitter((String)Mockito.anyObject());

        ReflectionTestUtils.setField(jobService, "jobDao", jobDao, JobDao.class);
        ReflectionTestUtils.setField(jobService, "notificationService", notificationService, NotificationService.class);
        ReflectionTestUtils.setField(jobService, "serverSettings", serverSettings, ServerSettings.class);


        jobService.updateJob(job);

    }

    public void testUpdateJobStatistic() throws Exception {
        final JobServiceImpl jobService = new JobServiceImpl();

        final Statistic statistic = new Statistic();
        statistic.setJob(this.getJob(1L));
        statistic.setId(1L);

        final StatisticDao statisticDao = Mockito.mock(StatisticDao.class);
        Mockito.when(statisticDao.save(statistic)).thenReturn(statistic);
        ReflectionTestUtils.setField(jobService, "statisticDao", statisticDao, StatisticDao.class);

        jobService.updateJobStatistic(statistic);
    }

    public void testUpdateJobStatistic2() throws Exception {
        final JobServiceImpl jobService = new JobServiceImpl();

        final Statistic statistic = new Statistic();
        statistic.setJob(this.getJob(1L));

        final StatisticDao statisticDao = Mockito.mock(StatisticDao.class);
        Mockito.when(statisticDao.save(statistic)).thenReturn(statistic);

        ReflectionTestUtils.setField(jobService, "statisticDao", statisticDao, StatisticDao.class);

        jobService.updateJobStatistic(statistic);
    }
//~~~~~Helper methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

private Job getJob(Long jobId) {

    Job job = new Job(jobId);
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
    job.setSalary("10000");
    job.setStatus(JobStatus.ACTIVE);
    job.setUpdateDate(new Date());
    job.setWebsite("www.google.com");

    return job;

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

}