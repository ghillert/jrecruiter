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

import junit.framework.Assert;
import junit.framework.TestCase;

import org.easymock.EasyMock;
import org.jrecruiter.service.JobService;

/**
 * Test the Struts 2 Logout Action
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
public class LogoutActionTest extends TestCase {

    public void testExecute() throws Exception {

        LogoutAction logoutAction = new LogoutAction();

        JobService jobService = EasyMock.createMock(JobService.class);

        EasyMock.replay(jobService);

        String ret = logoutAction.execute();

        Assert.assertEquals("success", ret);
    }
}

