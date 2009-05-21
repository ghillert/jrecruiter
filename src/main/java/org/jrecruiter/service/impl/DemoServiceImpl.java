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
package org.jrecruiter.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.jrecruiter.common.Constants.JobStatus;
import org.jrecruiter.common.Constants.OfferedBy;
import org.jrecruiter.model.Industry;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.Region;
import org.jrecruiter.model.User;
import org.jrecruiter.service.DemoService;
import org.jrecruiter.service.JobService;
import org.jrecruiter.service.UserService;
import org.jrecruiter.service.exceptions.DuplicateUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.svenjacobs.loremipsum.LoremIpsum;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
@Service("demoService")
@Transactional
public class DemoServiceImpl implements DemoService {

    private @Autowired JobService  jobService;
    private @Autowired UserService userService;

    /** {@inheritDoc} */
    public void createDemoJobs(final User user, final Integer numberOfJobsToCreate) {
        final LoremIpsum loremIpsum = new LoremIpsum();

        Random random = new Random();

        for (int i = 0; i <= numberOfJobsToCreate; i++) {

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
            job.setIndustry(new Industry(1L, null));
            job.setJobRestrictions(loremIpsum.getWords(30));
            job.setJobTitle(loremIpsum.getWords(2));
            job.setLatitude(BigDecimal.TEN);
            job.setLongitude(BigDecimal.TEN);
            job.setOfferedBy(OfferedBy.RECRUITER);

            if (i % 2 == 0) {
                job.setRegion(new Region(2L, null));
            } else {
                job.setRegionOther("Some Other Region");
            }

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_YEAR, random.nextInt(200));

            job.setRegistrationDate(cal.getTime());
            job.setUpdateDate(cal.getTime());
            job.setSalary(BigDecimal.valueOf(100000.50));
            job.setStatus(JobStatus.ACTIVE);

            job.setUser(user);
            job.setUsesMap(Boolean.TRUE);
            job.setWebsite("http://www.google.com/");

            this.jobService.addJob(job);
        }
    }


    /** {@inheritDoc} */
    public User createDemoUser() {
        User demoUser = getUser();

        User userFromDb = userService.getUser(demoUser.getEmail());

        if (userFromDb != null) {
            return userFromDb;
        } else {

            try {
                demoUser = userService.addUser(demoUser);
            } catch (DuplicateUserException e) {
                throw new IllegalStateException(e);
            }

        }

        return demoUser;
    }

    //~~~~~Helper Method~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

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
