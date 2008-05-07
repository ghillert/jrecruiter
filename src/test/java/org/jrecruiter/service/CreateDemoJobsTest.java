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
import org.jrecruiter.model.Industry;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.Region;
import org.jrecruiter.model.User;
import org.jrecruiter.test.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;

import de.svenjacobs.loremipsum.LoremIpsum;

/**
 *
 * @author Gunnar Hillert
 * @version $Id: JobServiceTest.java 189 2008-04-21 02:50:07Z ghillert $
 */
public class CreateDemoJobsTest extends BaseTest {

    private @Autowired JobService  jobService;
    private @Autowired UserService userService;

    public void testCreate30DemoJobsTest() throws Exception {

        final int numberOfJobs = 30;
        final LoremIpsum loremIpsum = new LoremIpsum();

        final User user = userService.addUser(this.getUser());

        for (int i = 0; i <= numberOfJobs; i++) {

            final Job job = new Job();
            job.setBusinessAddress1(loremIpsum.getWords(2));
            job.setBusinessAddress2(loremIpsum.getWords(3));
            job.setBusinessCity(loremIpsum.getWords(2));
            job.setBusinessEmail(loremIpsum.getWords(1) + "@" + loremIpsum.getWords(1) + ".com");
            job.setBusinessName(loremIpsum.getWords(2));
            job.setBusinessPhone("111-111-1111");
            job.setBusinessState(loremIpsum.getWords(1));
            job.setBusinessZip(loremIpsum.getWords(1));
            job.setDescription(loremIpsum.getParagraphs(2));
            job.setIndustry(new Industry(-1L, null));
            job.setJobRestrictions(loremIpsum.getWords(30));
            job.setJobTitle(loremIpsum.getWords(2));
            job.setLatitude(BigDecimal.TEN);
            job.setLongitude(BigDecimal.TEN);
            job.setOfferedBy(OfferedBy.RECRUITER);
            job.setRegion(new Region(-1L, null));
            job.setRegistrationDate(new Date());
            job.setSalary(BigDecimal.valueOf(100000.50));
            job.setStatus(JobStatus.ACTIVE);

            job.setUser(user);
            job.setUsesMap(Boolean.TRUE);
            job.setWebsite("http://www.google.com/");

            this.jobService.addJob(job);
        }

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