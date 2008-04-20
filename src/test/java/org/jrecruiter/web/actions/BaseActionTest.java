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

import java.util.HashMap;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.apache.struts2.ServletActionContext;
import org.easymock.EasyMock;
import org.jrecruiter.service.JobService;
import org.springframework.mock.web.MockHttpServletRequest;

import com.opensymphony.xwork2.ActionContext;

/**
 * Test the Struts 2 Logout Action
 *
 * @author Gunnar Hillert
 * @version $Id: ShowJobsActionTest.java 173 2008-02-29 06:17:07Z ghillert $
 */
public class BaseActionTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        ActionContext.getContext().setSession(new HashMap());

        // populate the request so getRequest().getSession() doesn't fail in BaseAction.java
        ServletActionContext.setRequest(new MockHttpServletRequest());

        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        ActionContext.getContext().setSession(null);
        super.tearDown();
    }

    public void testExecute() throws Exception {

        LogoutAction logoutAction = new LogoutAction();

        JobService jobService = EasyMock.createMock(JobService.class);

        logoutAction.setSession(new HashMap<String, Object>());

        EasyMock.replay(jobService);

        String ret = logoutAction.execute();

        Assert.assertEquals("success", ret);
    }
}

