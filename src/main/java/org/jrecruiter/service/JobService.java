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
package org.jrecruiter.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jrecruiter.model.Configuration;
import org.jrecruiter.model.Industry;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.Statistic;
import org.jrecruiter.model.statistics.JobCountPerDay;

import org.jrecruiter.model.Region;

/**
 * Provides job related methods.
 *
 * @author Jerzy Puchala
 * @author Gunnar Hillert
 */
public interface JobService {

	/**
	 * Method for adding a job posting. Returns the added job with the new primary id.
	 *
	 * @param jobs
	 */
	Job addJob(Job job);

	/**
	 *
	 * @param job
	 */
	void addJobAndSendToMailingList(Job job);

	/**
	 * Method for deleting a job posting for a job id.
	 *
	 * @param jobId ID number of a job posting
	 */
	void deleteJobForId(Long jobId);

	/**
	 * Method for getting a List of the industries.
	 *
	 * @return List of Industries, ordered by industry name.
	 */
	List < Industry > getIndustries();

	/**
	 * Return a single industry.
	 *
	 * @param industryId
	 * @return
	 */
	Industry getIndustry(Long industryId);

	/**
	 *
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	List<JobCountPerDay> getJobCountPerDayAndPeriod(Date fromDate, Date toDate);

	/**
	 * Method for getting a job posting for a job id.
	 *
	 * @param jobId ID number of a job posting
	 * @return JobReq
	 */
	Job getJobForId(Long jobId);

	/**
	 * Method for returning all available job postings.
	 *
	 * @return List of jobs.
	 */
	List < Job > getJobs();

	/**
	 * Method for returning a filtered list of available job postings.
	 *
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
	 * Return a single region.
	 *
	 * @param regionId
	 * @return
	 */
	Region getRegion(Long regionId);

	/**
	 * Method for getting a List of the regions.
	 *
	 * @return List of Regions, ordered by region name.
	 */
	List < Region > getRegions();

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
	 * @param maxResult
	 * @return List of jobs.
	 */
	List < Job > getUsersJobsForStatistics(String username, Integer maxResult);

	/** Re-index the Hibernate Search */
	void reindexSearch();

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
	 * Method for update a job posting.
	 *
	 * @param jobs
	 */
	void updateJob(Job jobs);

	/**
	 * Update the statistics table with the latest information. Makes sure that a job statistics data
	 * object exists at least for today and yesterday.
	 */
	void updateJobCountPerDays();

	/**
	 *
	 * @param asOfDay
	 */
	void updateJobCountPerDays(Calendar asOfDay);

	/**
	 *
	 * @param statistics
	 */
	void updateJobStatistic(Statistic statistics);

	/**
	 *
	 * @param id
	 * @return
	 */
	Job getJobForUniversalId(String id);

	/**
	 * Removed all job postings that have an
	 *
	 * @param days
	 */
	void removeOldJobs(Integer days);
}
