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
package org.jrecruiter.dao;

import java.util.List;

import org.jrecruiter.model.Configuration;

/**
 * @author Gunnar Hillert
 * @version @version $Id: SettingsDAO.java 24 2006-05-18 03:09:15Z ghillert $
 */
public interface SettingsDAO {

    /**
     * Method for returning list of all jobs.
     *
     * @return
     *
     */
    List<Configuration> getAllConfigurations();

    /**
     * Method for adding or updating a job posting.
     *
     * @param job job posting
     *
     */
    void update(Configuration configuration);

    /**
     * Method for getting a job posting.
     *
     * @param jobId job posting id
     *
     */
    Configuration get(String key);

}
