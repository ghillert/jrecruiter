/**
 *
 */
package org.jrecruiter.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jrecruiter.Constants.JobStatus;
import org.jrecruiter.Constants.OfferedBy;
import org.jrecruiter.Constants.StatsMode;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.Statistic;
import org.jrecruiter.model.User;
import org.jrecruiter.test.BaseTest;

/**
 * @author Gunnar Hillert
 *
 */
public class JobDaoTest extends BaseTest {

	/**
	 *
	 */
	private JobDao jobDao;

	private UserDao userDao;

    /** Statis Dao */
	private StatisticDao statisticDao;

	public void setUserDao(UserDao userDAO) {
		this.userDao = userDAO;
	}


	/**
	 *   Initialize Logging.
	 */
	public static final Logger LOGGER = Logger.getLogger(JobDaoTest.class);

	public void setJobDao(JobDao jobDao) {
		this.jobDao = jobDao;
	}

	public void testGetJobsPaginated() {

		List<Job> jobs = jobDao.getJobs(20, 1, null, null);

		for (Job job : jobs) {

			LOGGER.info(job.getId());

		}
	}

	public void testSearchByKeyword() {
		jobDao.searchByKeyword("java");
	}

	public void testSaveJobWithStatistic() {
		final Job job = this.getJob();

        Statistic statistic = new Statistic();
        statistic.setJob(job);
        statistic.setCounter(new Long(0));
        statistic.setUniqueVisits(10L);
        statistic.setLastAccess(new Date());

		User user = this.getUser();
		userDao.save(user);

		job.setStatistic(statistic);
		job.setUser(user);

		jobDao.save(job);
		assertNotNull(job.getId());
	}

	public void testGetJob() {

		final Job job = this.getJob();

		User user = this.getUser();
		userDao.save(user);

		job.setUser(user);
		jobDao.save(job);

		Job job2 = jobDao.get(job.getId());

		assertNotNull(job2);
	}

	public void testDoesJobExist() {

		final Job job = this.getJob();

		User user = this.getUser();
		userDao.save(user);

		job.setUser(user);

		assertFalse(jobDao.exists(9999999L));
		jobDao.save(job);
		assertNotNull(job.getId());

		assertTrue(jobDao.exists(job.getId()));
	}

	public void testGetAllUserJobs() {

		final Job job = this.getJob();
		final User user = this.getUser();

		userDao.save(user);

		job.setUser(user);
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
		job.setBusinessLocation("businessLocation");
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
		userDao.save(user);

		job.setUser(user);
		jobDao.save(job);

		List <Job> jobs = jobDao.getAllJobs();

		assertTrue(jobs.size() > 0);

	}

	public void testGetAllUserJobsForStatistics() {
		final Job job = this.getJob();
		final User user = this.getUser();

		userDao.save(user);

		job.setUser(user);
		jobDao.save(job);

		List <Job> jobs = jobDao.getAllUserJobsForStatistics(user.getId());

		assertNotNull(jobs);
		assertTrue(jobs.size()>0);
	}

	public void getUsersJobsForStatistics() {
		final Job job = this.getJob();
		final User user = this.getUser();

		userDao.save(user);
		job.setUser(user);
		jobDao.save(job);

		Statistic statistic = new Statistic();

		statistic.setJob(job);
		statistic.setCounter(new Long(0));
		statistic.setUniqueVisits(10L);
		statistic.setLastAccess(new Date());

		statisticDao.save(statistic);

		List <Job> jobs = jobDao.getUsersJobsForStatistics(user.getId(), 5, StatsMode.PAGE_HITS, false);

		assertNotNull(jobs);
		assertTrue(jobs.size()>0);
	}


	public void testGetJobsCount() {
		final Job job = this.getJob();
		final User user = this.getUser();

		userDao.save(user);

		job.setUser(user);
		jobDao.save(job);

		Integer totalNumberOfJobs = jobDao.getJobsCount();

		assertNotNull(totalNumberOfJobs);
		assertTrue(totalNumberOfJobs.intValue() > 0);
	}

	public void setStatisticDao(StatisticDao statisticDao) {
		this.statisticDao = statisticDao;
	}
}
