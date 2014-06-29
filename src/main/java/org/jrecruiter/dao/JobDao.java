/*
 * Copyright 2006-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrecruiter.dao;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.jrecruiter.model.Job;

/**
 * Interface for any job-related persistence calls.
 *
 * @author Jerzy Puchala, Gunnar Hillert
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
	 * @param administrator Is the user admin?
	 * @return List of jobs.
	 */
	List < Job > getUsersJobsForStatistics(Long userId,
										   Integer maxResult,
										   Boolean administrator);
	/**
	 * Re-index the Hibernate Search index
	 */
	void reindexSearch();

	/** Get a summary list of jobs */
	List<Job> getJobSummaries();

	/**
	 *
	 * @param universalId
	 * @return
	 */
	Job getForUniversalId(String universalId);

	/**
	 *
	 * @param cal
	 * @return
	 */
	List<Job> getJobsByUpdateDate(Calendar cal);
}
