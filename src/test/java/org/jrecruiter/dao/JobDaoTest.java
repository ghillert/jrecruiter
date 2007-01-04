/**
 *
 */
package org.jrecruiter.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.jrecruiter.model.Job;
import org.testng.annotations.Test;

/**
 * @author Gunnar Hillert
 *
 */
public class JobDaoTest extends BaseDaoTest {

	/**
	 * 
	 */
	private JobsDAO jobDao;
		
    /**
     *   Initialize Logging.
     */
    public static final Logger LOGGER = Logger.getLogger(JobDaoTestNG.class);

    public JobsDAO getJobDao() {
		return jobDao;
	}

	public void setJobDao(JobsDAO jobDao) {
		this.jobDao = jobDao;
	}

	@Test(groups = {"daoIntegrationTest"})
    public void getAllJobs() {
        
        List<Job> jobs = jobDao.getAllJobs();

        for (Job job : jobs) {

            LOGGER.info(job.getId());

        }
    }

    @Test(groups = {"dao-integration-test"})
    public void getJobsPaginated() {

        List<Job> jobs = jobDao.getJobs(20, 1, null, null);

        for (Job job : jobs) {

            LOGGER.info(job.getId());

        }
    }
    
    @Test(groups = {"dao-integration-test"})
    public void searchByKeyword() {

    jobDao.searchByKeyword("java");
    assert 1 == 1;
  }
}
