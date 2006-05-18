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

import org.jrecruiter.Constants;
import org.jrecruiter.model.Job;

import java.util.List;

/**
 *
 * @author Jerzy Puchala
 * @version @version $Id$
 */
public interface JobsDAO {
    /**
     * Method for returning list of all jobs.
     *
     * @return
     *
     * @throws org.jrecruiter.persistent.dao.DAOException
     */
    List<Job> getAllJobs() throws DAOException;

    /**
     * Method for adding or updating a job posting.
     *
     * @param job job posting
     *
     * @throws org.jrecruiter.persistent.dao.DAOException
     */
    void update(Job job) throws DAOException;

    /**
     * Method for getting a job posting.
     *
     * @param jobId job posting id
     *
     * @throws org.jrecruiter.persistent.dao.DAOException
     */
    Job get(Long jobId) throws DAOException;

    /**
     * Method for getting a jobs postings
     * based on a provided keyword.
     *
     * @param keyword search keyword
     *
     * @throws org.jrecruiter.persistent.dao.DAOException
     */
    List searchByKeyword(String keyword) throws DAOException;

    /**
     * Method for getting a job posting.
     *
     * @param jobId job posting id
     *
     * @throws org.jrecruiter.persistent.dao.DAOException
     */
    void delete(Long jobId) throws DAOException;

    /**
     * Method for getting users jobs.
     *
     * @param username name of user owning the job.
     * @return List of Job objects for given User
     */
    List getAllUserJobs(String username);

    /**
     * Method for getting users jobs for statistical purposes.
     *
     * @param username name of user owning the job.
     * @return List of Job objects for given User
     */
    List getAllUserJobsForStatistics(String username);

    /**
     * Method for returning list of jobs owned by the user for statistical
     * purposes.
     *
     * @param username
     * @return List of jobs.
     */
    List < Job > getUsersJobsForStatistics(String username, Integer maxResult, Constants.StatsMode statsMode);
}
