/**
 *
 */
package org.jrecruiter.dao;

import java.math.BigDecimal;
import java.util.Date;

import org.jrecruiter.common.Constants.JobStatus;
import org.jrecruiter.common.Constants.OfferedBy;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.Statistic;
import org.jrecruiter.model.User;
import org.jrecruiter.test.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
public class StatisticDaoTest extends BaseTest {

    private @Autowired StatisticDao statisticDao;

    private @Autowired UserDao userDao;

    private @Autowired JobDao jobDao;

    /**
     *   Initialize Logging.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(StatisticDaoTest.class);

    public void testGetAllStatistics() {
        Statistic statistic = getStatistic();

        Statistic savedStatistic = statisticDao.save(statistic);

        assertNotNull(savedStatistic.getId());
    }

    private Statistic getStatistic() {

        Statistic statistic = new Statistic();
        Job job = getJob();

        final User user = this.getUser();

        User savedUser = userDao.save(user);

        job.setUser(savedUser);

        Job savedJob = jobDao.save(job);

        statistic.setJob(savedJob);
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
