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

import junit.framework.Assert;

import org.jrecruiter.common.Constants.JobStatus;
import org.jrecruiter.common.Constants.OfferedBy;
import org.jrecruiter.model.User;
import org.jrecruiter.model.Job;
import org.jrecruiter.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
public class JobServiceTest extends BaseTest {

    @Autowired JobService jobService;
    @Autowired UserService userService;

    public void addJobTest(){}

    @Test
    public void testDeleteJobForIdTest() throws Exception {
        final Job job = this.getJob();

        User user = this.getUser();
        User savedUser = userService.addUser(user);
        job.setUser(savedUser);
        Job savedJob = jobService.addJob(job);

        Job job2 = jobService.getJobForId(savedJob.getId());
        Assert.assertNotNull(job2);
        jobService.deleteJobForId(job2.getId());

        Assert.assertNull(jobService.getJobForId(savedJob.getId()));
    }

    @Test
    public void testRemoveOldJobs() {
        jobService.removeOldJobs(90);
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

    //FIXME Test
//    @Test
//    public void testSendJobPostingToMailingList() throws Exception {
//
//        final Job job = this.getJob();
//
//        User user = this.getUser();
//        User savedUser = userService.addUser(user);
//
//        job.setUser(savedUser);
//
//        jobService.addJobAndSendToMailingList(job, "test-url");
//
//    }



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