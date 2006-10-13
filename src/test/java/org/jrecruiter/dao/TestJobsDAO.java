/**
 *
 */
package org.jrecruiter.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.User;
import org.jrecruiter.persistent.dao.JobsDAO;
import org.jrecruiter.persistent.dao.UserDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.testng.annotations.Configuration;
import org.testng.annotations.Test;

/**
 * @author Gunnar Hillert
 *
 */
public class TestJobsDAO {

    ApplicationContext ctx;
    SessionFactory sessionFactory;
    /**
     *   Initialize Logging.
     */
    public static final Logger LOGGER = Logger.getLogger(TestJobsDAO.class);

    @Configuration(beforeTestClass = true)
    public void configure() {
        System.out.println("Initialization");
        ctx = new ClassPathXmlApplicationContext("testApplicationContext.xml");

        sessionFactory = (SessionFactory) ctx
                .getBean("sessionFactory");
        Session s = sessionFactory.openSession();
        TransactionSynchronizationManager.bindResource(sessionFactory,
                new SessionHolder(s));
    }

    @Configuration(afterTestClass = true)
    public void shutdown() {
        SessionHolder holder = (SessionHolder) TransactionSynchronizationManager
                .getResource(sessionFactory);
        Session s = holder.getSession();
        s.flush();
        TransactionSynchronizationManager.unbindResource(sessionFactory);
        SessionFactoryUtils.closeSession(s);
    }

    @Test(groups = { "exec-group" })
    public void getAllJobs() {
        System.out.println("exec");
        JobsDAO jobsDAO = (JobsDAO) ctx.getBean("jobsDAO");

        List<Job> jobs = jobsDAO.getAllJobs();

        for (Job job : jobs) {

            LOGGER.info(job.getId());

        }
    }

    @Test(groups = { "exec-group" })
    public void getJobsPaginated() {
        System.out.println("exec");
        JobsDAO jobsDAO = (JobsDAO) ctx.getBean("jobsDAO");

        List<Job> jobs = jobsDAO.getJobs(20, 1, null, null);

        for (Job job : jobs) {

            LOGGER.info(job.getId());

        }
    }

    @Test(groups = { "exec-group" })
    public void getAllUsers() {
        System.out.println("exec");
        UserDAO jobsDAO = (UserDAO) ctx.getBean("userDAO");

        List<User> jobs = jobsDAO.getAllUsers();

        for (User job : jobs) {

            LOGGER.info(job.getUsername());

        }
    }
}
