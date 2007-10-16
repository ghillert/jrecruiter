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
import java.util.Date;

import org.jrecruiter.Constants.JobStatus;
import org.jrecruiter.Constants.OfferedBy;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.User;
import org.jrecruiter.test.BaseTest;
import org.springframework.orm.ObjectRetrievalFailureException;

/**
 *
 * @author Gunnar Hillert
 * @version $Id: JobService.java 93 2007-01-22 02:42:14Z ghillert $
 */
public class JobServiceTest extends BaseTest {

	JobService jobService;
	UserService userService;


    public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * @param jobService the jobService to set
	 */
	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}

	public void addJobTest(){}

	public void testDeleteJobForIdTest() throws Exception {
		final Job job = this.getJob();

		User user = this.getUser();
		userService.addUser(user);
		job.setUser(user);
		jobService.addJob(job);

		Job job2 = jobService.getJobForId(job.getId());
		assertNotNull(job2);
		jobService.deleteJobForId(job2.getId());

		try {
			jobService.getJobForId(job.getId());
		} catch (ObjectRetrievalFailureException e) {
			return;
		}

		fail();

	}

    public void getJobForIdTest(){}

    public void getJobsTest(){}

    public void getJobsTest2(){}

    public void getJobsCountTest(){}

    public void getJRecruiterSettingTest(){}

    public void getJRecruiterSettingsTest(){}

    public void getUsersJobsTest(){}

    public void getUsersJobsForStatisticsTest(){}

    public void getUsersJobsForStatistics2Test(){}

    public void saveJRecruiterSettingTest(){}

    public void searchByKeywordTest(){}

    public void testSendJobPostingToMailingList() throws Exception {

		final Job job = this.getJob();

		User user = this.getUser();
		userService.addUser(user);

		job.setUser(user);

		jobService.addJob(job);

    	jobService.sendJobPostingToMailingList(job);

    }

    public void updateJobTest(){}



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