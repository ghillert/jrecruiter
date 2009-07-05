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

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jrecruiter.dao.GenericDao;

import org.jrecruiter.common.Constants;
import org.jrecruiter.model.Job;

/**
 * Interface for any job-related persistence calls.
 *
 * @author Jerzy Puchala, Gunnar Hillert
 * @version @version $Id$
 */
public interface JobDao extends GenericDao < Job, Long >{
    /**
     * Method for returning list of all jobs.
     *
     * @return List of jobs
     */
    List < Job > getAllJobs();

    /**
     * Method for getting a job posting.
     *
     * @param jobId job posting id
     *
     * @return Job
     */
    Job get(Long jobId);

    /**
     * Method for getting a jobs postings
     * based on a provided keyword.
     *
     * @param keyword search keyword
     *
     * @return List of jobs
     */
    List < Job > searchByKeyword(String keyword);

    /**
     * Method for getting users jobs.
     *
     * @param username name of user owning the job.
     * @return List of Job objects for given User
     */
    List <Job> getAllUserJobs(String username);

    /**
     * Method for returning list of available job postings.
     * @param pageSize Max number of results returned
     * @param pageNumber Which page are you one?
     * @param fieldSorted Which field shall be sorted
     * @param sortOrder What is the sort order?
     * @return List of jobs.
     */
    List < Job > getJobs(Integer pageSize, Integer pageNumber, Map<String, String> sortOrders, Map<String, String> jobFilters);

    /**
     * Returns the number of totally available jobs in the system.
     *
     * @return Total number of jobs
     */
    Long getJobsCount();

    /**
     *
     * @param day
     * @return
     */
    Long getJobCount(Date day);

    /**
     * Method for getting users jobs for statistical purposes.
     *
     * @param userId user id of user owning the job.
     * @return List of Job objects for given User
     */
    List<Job> getAllUserJobsForStatistics(Long userId);

    /**
     * Method for returning list of jobs owned by the user for statistical
     * purposes.
     *
     * @param userId user id of user owning the job.
     * @param maxResult maximum number of statistics objects returned
     * @param statsMode  what type of statistical information to be generated
     * @param administrator Is the user admin?
     * @return List of jobs.
     */
    List < Job > getUsersJobsForStatistics(Long userId,
                                           Integer maxResult,
                                           Constants.StatsMode statsMode,
                                           Boolean administrator);
    /**
     * Re-index the Hibernate Search index
     */
    void reindexSearch();

    /** Get a summary list of jobs */
	List<Job> getJobSummaries();
}
