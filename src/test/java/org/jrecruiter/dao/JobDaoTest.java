/**
 *
 */
package org.jrecruiter.dao;
 

import java.util.List;

import org.apache.log4j.Logger;
import org.jrecruiter.model.Job;
import org.jrecruiter.test.BaseTest;

/**
 * @author Gunnar Hillert
 *
 */
public class JobDaoTest extends BaseTest {

	/**
	 * 
	 */
	private JobsDAO jobDao;
		
    /**
     *   Initialize Logging.
     */
    public static final Logger LOGGER = Logger.getLogger(JobDaoTest.class);

	public void setJobDao(JobsDAO jobDao) {
		this.jobDao = jobDao;
	}

    public void getAllJobs() {
        
        List<Job> jobs = jobDao.getAllJobs();

        for (Job job : jobs) {

            LOGGER.info(job.getId());

        }
    }

    public void getJobsPaginated() {

        List<Job> jobs = jobDao.getJobs(20, 1, null, null);

        for (Job job : jobs) {

            LOGGER.info(job.getId());

        }
    }
    
    public void searchByKeyword() {

    jobDao.searchByKeyword("java");
  }
}
