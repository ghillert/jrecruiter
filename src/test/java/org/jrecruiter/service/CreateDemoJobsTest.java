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

import org.jrecruiter.test.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
public class CreateDemoJobsTest extends BaseTest {

    private @Autowired DemoService  demoService;

    public void testCreate30DemoJobsTest() throws Exception {

        final int numberOfJobs = 30;

        demoService.createDemoJobs(numberOfJobs);

    }

}