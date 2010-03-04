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


/**
 * Special services allowing the administrator to populate the sytem with
 * demo data.
 *
 * @author Gunnar Hillert
 *
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
public interface DemoService {

    /**
     * Adds specifiable number of demo jobs to the system.
     *
     * @param user user for which the jobs are being generated.
     * @param numberOfJobsToCreate How many job shall be created.
     */
    void createDemoJobs(User user, Integer numberOfJobsToCreate);

    /**
     * Creates a single default user. If the user does not exist, yet, a new
     * user is created, otherwise the default user is returned.
     *
     */
    User createDemoUser();
}
