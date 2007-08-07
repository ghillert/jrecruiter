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
public class StatisticDaoTest extends BaseTest {

	/**
	 *
	 */
	private StatisticDao statisticDao;

	private UserDao userDao;

	private JobDao jobDao;

	/**
	 * @param jobDao the jobDao to set
	 */
	public void setJobDao(JobDao jobDao) {
		this.jobDao = jobDao;
	}

	public void setUserDao(UserDao userDAO) {
		this.userDao = userDAO;
	}

	/**
	 *   Initialize Logging.
	 */
	public static final Logger LOGGER = Logger.getLogger(StatisticDaoTest.class);

	/**
	 * @param statisticDao the statisticDao to set
	 */
	public void setStatisticDao(StatisticDao statisticDao) {
		this.statisticDao = statisticDao;
	}

	public void testGetAllStatistics() {
		final Job job = this.getJob();

		Statistic statistic = getStatistic();

		statisticDao.save(statistic);

		assertNotNull(statistic.getId());
	}

	private Statistic getStatistic() {

		Statistic statistic = new Statistic();
		Job job = getJob();

		final User user = this.getUser();

		userDao.save(user);

		job.setUser(user);

		jobDao.save(job);

        statistic.setJob(job);
        statistic.setCounter(new Long(0));
        statistic.setUniqueVisits(10L);
        statistic.setLastAccess(new Date());

        return statistic;

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
