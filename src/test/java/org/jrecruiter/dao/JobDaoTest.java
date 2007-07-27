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

	public void testGetAllJobs() {

		List<Job> jobs = jobDao.getAllJobs();

		for (Job job : jobs) {

			LOGGER.info(job.getId());

		}
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
		Job job = new Job();

        Statistic statistic = new Statistic();
        statistic.setJob(job);
        statistic.setCounter(new Long(0));
        statistic.setUniqueVisits(10L);
        statistic.setLastAccess(new Date());

		User user = new User();
		user.setUsername("demo44");
		user.setEmail("demo@demo.com");
		user.setFirstName("Demo First Name");
		user.setLastName("Demo Last Name");
		user.setPassword("demo");
		user.setPhone("123456");
		user.setRegistrationDate(new Date());
		userDao.save(user);

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
		//job.setIndustry("industry");
		job.setJobRestrictions("jobRestrictions");
		job.setJobTitle("jobTitle");
		job.setLatitude(BigDecimal.ONE);
		job.setLongitude(BigDecimal.ZERO);
		job.setOfferedBy(OfferedBy.RECRUITER);
		job.setRegistrationDate(new Date());
		job.setSalary(new BigDecimal(10000));
		job.setStatistic(statistic);
		job.setStatus(JobStatus.ACTIVE);
		job.setUpdateDate(new Date());
		job.setUser(user);
		job.setWebsite("www.google.com");

		jobDao.save(job);

		assertNotNull(job.getId());
	}
}
