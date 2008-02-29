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
package org.jrecruiter.web.actions;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.easymock.EasyMock;
import org.jrecruiter.Constants.JobStatus;
import org.jrecruiter.dao.JobDao;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.User;
import org.jrecruiter.service.JobService;
import org.jrecruiter.service.UserService;
import org.jrecruiter.service.impl.JobServiceImpl;

/**
 * Test the Struts 2 Show Jobs Action
 *
 * @author Gunnar Hillert
 * @version $Id: EnumConverterTest.java 162 2008-02-26 04:55:29Z ghillert $
 */
public class ShowJobsActionTest extends TestCase {

    public void testExecute() throws Exception {

    	ShowJobsAction showJobsAction = new ShowJobsAction();

        JobService jobService = EasyMock.createMock(JobService.class);


        showJobsAction.setJobService(jobService);

        List<Job>jobs = new ArrayList<Job>();

        EasyMock.expect(jobService.getJobsCount()).andReturn(10);
        EasyMock.expect(jobService.getJobs(20, 1, "", null)).andReturn(jobs);

        EasyMock.replay(jobService);

    	String ret = showJobsAction.execute();

    	Assert.assertEquals("success", ret);
    }

    public void testExecuteAjax() throws Exception {
    	ShowJobsAction showJobsAction = new ShowJobsAction();
    	showJobsAction.setDisplayAjax("true");

        JobService jobService = EasyMock.createMock(JobService.class);

        showJobsAction.setJobService(jobService);

        List<Job>jobs = new ArrayList<Job>();

        EasyMock.expect(jobService.getJobsCount()).andReturn(10);
        EasyMock.expect(jobService.getJobs(20, 1, "", null)).andReturn(jobs);

        EasyMock.replay(jobService);

    	String ret = showJobsAction.execute();

    	Assert.assertEquals("ajax", ret);
    }
}

