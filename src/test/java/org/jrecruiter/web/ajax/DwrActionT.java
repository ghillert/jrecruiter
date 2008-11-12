/**
 *
 */
package org.jrecruiter.web.ajax;

import java.math.BigDecimal;
import java.util.Date;

import org.directwebremoting.Container;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.impl.DefaultWebContextBuilder;
import org.directwebremoting.spring.SpringContainer;
import org.jrecruiter.common.Constants.JobStatus;
import org.jrecruiter.common.Constants.OfferedBy;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.User;
import org.jrecruiter.service.JobService;
import org.jrecruiter.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;


/**
 * @author Gunnar Hillert
 * @version $Id$
 */
public class DwrActionT {
    private DwrAction ajaxService;
    private JobService jobService;
    private UserService userService;


    /**
     *   Initialize Logging.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(DwrActionT.class);


    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setAjaxService(DwrAction ajaxService) {
        this.ajaxService = ajaxService;
    }

    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }

    public void notestGetJob() throws Exception {

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        MockServletConfig servletConfig = new MockServletConfig();
        MockServletContext servletContext = new MockServletContext();
        Container container = new SpringContainer();

        DefaultWebContextBuilder builder = new DefaultWebContextBuilder();
        builder.set(request, response, servletConfig, servletContext, container);

        WebContextFactory.setWebContextBuilder(builder);

        final Job job = this.getJob();

        User user = this.getUser();
        userService.addUser(user);
        job.setUser(user);
        jobService.addJob(job);

        String ret = ajaxService.getJob(job.getId());

    //	assertNotNull(ret);
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
