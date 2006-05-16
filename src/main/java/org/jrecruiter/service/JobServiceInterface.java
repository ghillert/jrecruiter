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

import java.util.List;

import org.jrecruiter.Constants;
import org.jrecruiter.model.Configuration;
import org.jrecruiter.model.Job;

/**
 *
 * @author Jerzy Puchala
 * @version $Revision: 1.5 $, $Date: 2006/03/19 21:58:05 $, $Author: ghillert $
 */
public interface JobServiceInterface {
    /**
     * Method for returning list of available job postings.
     *
     * @return List of jobs.
     */
    List < Job > getJobs();

    /**
     * Method for returning list of jobs owned by the user.
     *
     * @param username
     * @return List of jobs.
     */
    List < Job > getUsersJobs(String username);

    /**
     * Method for returning list of jobs owned by the user for statistical
     * purposes.
     *
     * @param username
     * @return List of jobs.
     */
    List < Job > getUsersJobsForStatistics(String username);

    /**
     * Method for returning list of jobs owned by the user for statistical
     * purposes.
     *
     * @param username
     * @return List of jobs.
     */
    List < Job > getUsersJobsForStatistics(String username, Integer maxResult, Constants.StatsMode statsMode);

    /**
     * Method for adding a job posting.
     *
     * @param jobs
     */
    void addJob(Job jobs);

    /**
     * Method for update a job posting.
     *
     * @param jobs
     */
    void updateJob(Job jobs);

    /**
     * Method for getting a job posting for a job id.
     *
     * @param jobId ID number of a job posting
     * @return JobReq
     */
    Job getJobForId(Long jobId);

    /**
     * Method for deleting a job posting for a job id.
     *
     * @param jobId ID number of a job posting
     */
    void deleteJobForId(Long jobId);

    /**
     * Method for sending a job posting to the mailing list.
     *
     * @param jobs
     */
    void sendJobPostingToMailingList(Job jobs);

    /**
     * Method for getting a jobs postings
     * based on a provided keyword.
     *
     * @param keyword search keyword
     */
    List < Job > searchByKeyword(String keyword);

    /**
     * Method for getting a List of the jRecruiter Settings.
     *
     * @param jobId ID number of a job posting
     * @return JobReq
     */
    List < Configuration > getJRecruiterSettings();

    /**
     * Method for getting a single jRecruiter Setting.
     *
     * @param jobId ID number of a job posting
     * @return JobReq
     */
    Configuration getJRecruiterSetting(String key);

    /**
     * Method for saving a jRecruiter Setting to the persistence store..
     *
     * @param configuration A Configuration Element
     */
    void saveJRecruiterSetting(Configuration configuration);
}
