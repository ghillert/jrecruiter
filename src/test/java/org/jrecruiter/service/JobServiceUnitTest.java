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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.easymock.EasyMock;
import org.jrecruiter.Constants;
import org.jrecruiter.Constants.JobStatus;
import org.jrecruiter.Constants.OfferedBy;
import org.jrecruiter.dao.ConfigurationDao;
import org.jrecruiter.dao.JobDao;
import org.jrecruiter.dao.RegionDao;
import org.jrecruiter.dao.StatisticDao;
import org.jrecruiter.dao.UserDao;
import org.jrecruiter.model.Configuration;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.Region;
import org.jrecruiter.model.Role;
import org.jrecruiter.model.Statistic;
import org.jrecruiter.model.User;
import org.jrecruiter.model.UserToRole;
import org.jrecruiter.service.impl.JobServiceImpl;


/**
 * Unit Test of the JobService class
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
public class JobServiceUnitTest extends TestCase {

    public void testDeleteJobForIdTest() throws Exception {

        JobServiceImpl jobService = new JobServiceImpl();

        JobDao jobDao = EasyMock.createMock(JobDao.class);

        jobService.setJobDao(jobDao);
        jobDao.remove(1L);
        EasyMock.replay(jobDao);

        final Job job = new Job();
        job.setId(1L);

        jobService.deleteJobForId(job.getId());

    }

    public void testGetJobs(){

        List<Job> jobs = new ArrayList<Job>();

        for (long i=1; i<=10; i++) {
            jobs.add(this.getJob(i));
        }

        JobServiceImpl jobService = new JobServiceImpl();
        JobDao jobDao = EasyMock.createMock(JobDao.class);

        jobService.setJobDao(jobDao);
        EasyMock.expect(jobDao.getAllJobs()).andReturn(jobs);
        EasyMock.replay(jobDao);

        List<Job> jobsFromDb = jobService.getJobs();

        assertNotNull(jobsFromDb);
        assertTrue(jobsFromDb.size() == 10);

    }

    public void testGetIndustries(){

        List<Job> jobs = new ArrayList<Job>();

        for (long i=1; i<=10; i++) {
            jobs.add(this.getJob(i));
        }

        JobServiceImpl jobService = new JobServiceImpl();
        JobDao jobDao = EasyMock.createMock(JobDao.class);

        jobService.setJobDao(jobDao);
        EasyMock.expect(jobDao.getAllJobs()).andReturn(jobs);
        EasyMock.replay(jobDao);

        List<Job> jobsFromDb = jobService.getJobs();

        assertNotNull(jobsFromDb);
        assertTrue(jobsFromDb.size() == 10);

    }

    public void testGetRegions(){

        List<Region> regions = new ArrayList<Region>();

        for (long i=1; i<=10; i++) {
            regions.add(new Region(i, "Test_" + i));
        }

        JobServiceImpl jobService = new JobServiceImpl();
        RegionDao regionDao = EasyMock.createMock(RegionDao.class);
        jobService.setRegionDao(regionDao);

        EasyMock.expect(regionDao.getAllRegionsOrdered()).andReturn(regions);
        EasyMock.replay(regionDao);

        List<Region> regionsromDb = jobService.getRegions();

        assertNotNull(regionsromDb);
        assertTrue(regionsromDb.size() == 10);

    }

    public void testGetJobsForPagination(){

        final List<Job> jobs = new ArrayList<Job>();

        for (long i=1; i<=10; i++) {
            jobs.add(this.getJob(i));
        }

        final JobServiceImpl jobService = new JobServiceImpl();
        final JobDao jobDao = EasyMock.createMock(JobDao.class);

        jobService.setJobDao(jobDao);
        EasyMock.expect(jobDao.getJobs(5, 1, "test", "ascending")).andReturn(jobs);
        EasyMock.replay(jobDao);

        final List<Job> jobsFromDb = jobService.getJobs(5, 1, "test", "ascending");

        assertNotNull(jobsFromDb);
        assertTrue(jobsFromDb.size() == 10);

    }

    public void testGetJobsCountTest(){
        final JobServiceImpl jobService = new JobServiceImpl();

        final JobDao jobDao = EasyMock.createMock(JobDao.class);
        jobService.setJobDao(jobDao);

        EasyMock.expect(jobDao.getJobsCount()).andReturn(10);
        EasyMock.replay(jobDao);

        final Integer jobsCounted = jobService.getJobsCount();

        assertEquals(Integer.valueOf(10), jobsCounted);
    }

    public void testGetJRecruiterSettings() {

        final JobServiceImpl jobService = new JobServiceImpl();

        List<Configuration>settings = new ArrayList<Configuration>();

        for (long i=1; i<=10; i++) {
            Configuration configuration = new Configuration("configuration_" + i);
            settings.add(configuration);
        }

        final ConfigurationDao configurationDao = EasyMock.createStrictMock(ConfigurationDao.class);
        EasyMock.expect(configurationDao.getAll()).andReturn(settings);
        jobService.setConfigurationDao(configurationDao);

        EasyMock.replay(configurationDao);

        List<Configuration>settings2 = jobService.getJRecruiterSettings();

        assertNotNull(settings2);
        assertTrue(settings2.size() == 10);
    }

    public void testGetJRecruiterSetting() {

        final JobServiceImpl jobService = new JobServiceImpl();

        Configuration configuration = new Configuration();

        final ConfigurationDao configurationDao = EasyMock.createStrictMock(ConfigurationDao.class);
        EasyMock.expect(configurationDao.get("key")).andReturn(configuration);
        jobService.setConfigurationDao(configurationDao);

        EasyMock.replay(configurationDao);

        Configuration configuration2 = jobService.getJRecruiterSetting("key");

        assertNotNull(configuration2);
    }

    public void testSaveJRecruiterSetting() {
        final JobServiceImpl jobService = new JobServiceImpl();

        Configuration configuration = new Configuration();

        final ConfigurationDao configurationDao = EasyMock.createStrictMock(ConfigurationDao.class);
        configurationDao.update(configuration);
        jobService.setConfigurationDao(configurationDao);

        EasyMock.replay(configurationDao);
        jobService.saveJRecruiterSetting(configuration);
    }

    public void testGetUsersJobsTestAsAdminUser(){

        final User user = this.getUser();
        user.getUserToRoles().add(new UserToRole(1L, new Role(1L, Constants.Roles.ADMIN.name()), user));

        final JobServiceImpl jobService = new JobServiceImpl();

        final JobDao jobDao = EasyMock.createMock(JobDao.class);
        jobService.setJobDao(jobDao);

        final UserDao userDao = EasyMock.createMock(UserDao.class);
        jobService.setUserDao(userDao);

        EasyMock.expect(userDao.getUser("demo44")).andReturn(user);
        EasyMock.replay(userDao);

        final List<Job>adminJobs = new ArrayList<Job>();

        for (long i=1; i<=10; i++) {
            Job job = new Job(i);
            adminJobs.add(job);
        }

        EasyMock.expect(jobDao.getAllJobs()).andReturn(adminJobs);
        EasyMock.replay(jobDao);

        final List<Job>jobsFromService = jobService.getUsersJobs("demo44");

        assertTrue(jobsFromService.size() == 10);

    }

    public void testGetUsersJobsAsNormalUser(){

        final User user = this.getUser();
        user.getUserToRoles().add(new UserToRole(1L, new Role(1L, Constants.Roles.MANAGER.name()), user));

        final JobServiceImpl jobService = new JobServiceImpl();

        final JobDao jobDao = EasyMock.createMock(JobDao.class);
        jobService.setJobDao(jobDao);

        final UserDao userDao = EasyMock.createMock(UserDao.class);
        jobService.setUserDao(userDao);

        EasyMock.expect(userDao.getUser("demo44")).andReturn(user);
        EasyMock.replay(userDao);

        final List<Job>userJobs = new ArrayList<Job>();

        for (long i=1; i<=5; i++) {
            Job job = new Job(i);
            userJobs.add(job);
        }

        EasyMock.expect(jobDao.getAllUserJobs("demo44")).andReturn(userJobs);
        EasyMock.replay(jobDao);

        final List<Job>jobsFromService = jobService.getUsersJobs("demo44");

        assertTrue(jobsFromService.size() == 5);

    }

    public void testGetUsersJobsForStatistics() {

        final User user = this.getUser();
        user.getUserToRoles().add(new UserToRole(1L, new Role(1L, Constants.Roles.MANAGER.name()), user));

        final JobServiceImpl jobService = new JobServiceImpl();

        final JobDao jobDao = EasyMock.createMock(JobDao.class);
        jobService.setJobDao(jobDao);

        final UserDao userDao = EasyMock.createMock(UserDao.class);
        jobService.setUserDao(userDao);

        EasyMock.expect(userDao.getUser("demo44")).andReturn(user);
        EasyMock.replay(userDao);

        final List<Job>jobs = new ArrayList<Job>();

        for (long i=1; i<=5; i++) {
            Job job = new Job(i);
            jobs.add(job);
        }

        EasyMock.expect(jobDao.getAllUserJobsForStatistics(user.getId())).andReturn(jobs);
        EasyMock.replay(jobDao);

        final List<Job>jobs2 = jobService.getUsersJobsForStatistics("demo44");

        assertNotNull(jobs2);
        assertTrue(jobs2.size() == 5);

    }


    public void testAddJob() {

        final JobServiceImpl jobService = new JobServiceImpl();

        Job job = this.getJob(1L);

        final JobDao jobDao = EasyMock.createStrictMock(JobDao.class);
        jobDao.save(job);
        jobService.setJobDao(jobDao);

        EasyMock.replay(jobDao);

        jobService.addJob(job);

    }

    public void testGetJobForId() {

        final JobServiceImpl jobService = new JobServiceImpl();

        Job job = this.getJob(1L);

        final JobDao jobDao = EasyMock.createStrictMock(JobDao.class);
        jobDao.save(job);
        jobService.setJobDao(jobDao);



        EasyMock.replay(jobDao);

        jobService.addJob(job);

    }


    public void testUpdateJobTest(){
        final JobServiceImpl jobService = new JobServiceImpl();

        final Job job = this.getJob(1L);

        final JobDao jobDao = EasyMock.createStrictMock(JobDao.class);
        jobDao.update(job);
        jobService.setJobDao(jobDao);

        EasyMock.replay(jobDao);

        jobService.updateJob(job);

    }

    public void testUpdateJobStatistic() {
        final JobServiceImpl jobService = new JobServiceImpl();

        final Statistic statistic = new Statistic();
        statistic.setJob(this.getJob(1L));
        statistic.setId(1L);

        final StatisticDao statisticDao = EasyMock.createStrictMock(StatisticDao.class);
        statisticDao.update(statistic);
        jobService.setStatisticDao(statisticDao);

        EasyMock.replay(statisticDao);

        jobService.updateJobStatistic(statistic);
    }

    public void testUpdateJobStatistic2() {
        final JobServiceImpl jobService = new JobServiceImpl();

        final Statistic statistic = new Statistic();
        statistic.setJob(this.getJob(1L));

        final StatisticDao statisticDao = EasyMock.createStrictMock(StatisticDao.class);
        statisticDao.save(statistic);
        jobService.setStatisticDao(statisticDao);

        EasyMock.replay(statisticDao);

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
    job.setSalary(new BigDecimal(10000));
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