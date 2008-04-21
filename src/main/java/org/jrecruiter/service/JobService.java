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
import org.jrecruiter.model.Industry;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.Region;
import org.jrecruiter.model.Statistic;

/**
 *
 * @author Jerzy Puchala
 * @author Gunnar Hillert
 * @version $Id:JobService.java 128 2007-07-27 03:55:54Z ghillert $
 */
public interface JobService {
    /**
     * Method for adding a job posting.
     *
     * @param jobs
     */
    Job addJob(Job job);

    /**
     * Method for deleting a job posting for a job id.
     *
     * @param jobId ID number of a job posting
     */
    void deleteJobForId(Long jobId);

    /**
     * Method for getting a job posting for a job id.
     *
     * @param jobId ID number of a job posting
     * @return JobReq
     */
    Job getJobForId(Long jobId);

    /**
     * Method for returning list of available job postings.
     *
     * @return List of jobs.
     */
    List < Job > getJobs();

    /**
     * Method for returning list of available job postings.
     *
     * @return List of jobs.
     */
    List < Job > getJobs(Integer pageSize, Integer pageNumber, String fieldSorted, String sortOrder);

    /**
     * Returns the number of totally available jobs in the system.
     *
     * @return Total number of jobs
     */
    Integer getJobsCount();

    /**
     * Method for getting a single jRecruiter Setting.
     *
     * @param jobId ID number of a job posting
     * @return JobReq
     */
    Configuration getJRecruiterSetting(String key);

    /**
     * Method for getting a List of the jRecruiter Settings.
     *
     * @param jobId ID number of a job posting
     * @return JobReq
     */
    List < Configuration > getJRecruiterSettings();

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
     * Method for saving a jRecruiter Setting to the persistence store..
     *
     * @param configuration A Configuration Element
     */
    void saveJRecruiterSetting(Configuration configuration);

    /**
     * Method for getting a jobs postings
     * based on a provided keyword.
     *
     * @param keyword search keyword
     */
    List < Job > searchByKeyword(String keyword);

    /**
     * Method for sending a job posting to the mailing list.
     *
     * @param jobs
     */
    void sendJobPostingToMailingList(Job jobs);

    /**
     * Method for update a job posting.
     *
     * @param jobs
     */
    void updateJob(Job jobs);

    /**
     *
     * @param statistics
     */
    void updateJobStatistic(Statistic statistics);

    /**
     * Method for getting a List of the regions.
     *
     * @return List of Regions, ordered by region name.
     */
    List < Region > getRegions();

    /**
     * Method for getting a List of the industries.
     *
     * @return List of Industries, ordered by industry name.
     */
    List < Industry > getIndustries();

    /** Re-index the Hibernate Search */
    void reindexSearch();
}
