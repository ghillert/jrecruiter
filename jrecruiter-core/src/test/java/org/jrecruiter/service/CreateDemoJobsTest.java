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

import org.jrecruiter.model.User;
import org.jrecruiter.test.BaseServiceIntegrationTest;
import org.jrecruiter.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
public class CreateDemoJobsTest extends BaseServiceIntegrationTest {

    private @Autowired SystemSetupService  systemSetupService;

    @Test
    public void testCreate30DemoJobsTest() throws Exception {

        final int numberOfJobs = 30;

        //FIXME
    //    User user = demoService.createDemoUser();
    //    demoService.createDemoJobs(user, numberOfJobs);

    }

}