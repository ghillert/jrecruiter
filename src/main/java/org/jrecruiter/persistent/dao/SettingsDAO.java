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
package org.jrecruiter.persistent.dao;

import java.util.List;

import org.jrecruiter.model.Configuration;
import org.jrecruiter.model.Job;

/**
 * @author Gunnar Hillert
 * @version $Revision: 1.2 $, $Date: 2006/02/06 04:08:35 $, $Author: ghillert $
 */
public interface SettingsDAO {
    
	/**
     * Method for returning list of all jobs.
     *
     * @return
     *
     * @throws org.jrecruiter.persistent.dao.DAOException
     */
    List<Configuration> getAllConfigurations() throws DAOException;

    /**
     * Method for adding or updating a job posting.
     *
     * @param job job posting
     *
     * @throws org.jrecruiter.persistent.dao.DAOException
     */
    void update(Configuration configuration) throws DAOException;

    /**
     * Method for getting a job posting.
     *
     * @param jobId job posting id
     *
     * @throws org.jrecruiter.persistent.dao.DAOException
     */
    Configuration get(String key) throws DAOException;
    
}
