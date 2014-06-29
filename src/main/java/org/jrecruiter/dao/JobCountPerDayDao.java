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

import java.util.Date;
import java.util.List;

import org.jrecruiter.model.statistics.JobCountPerDay;

/**
 * Interface for any jobCountPerDay-related persistence calls.
 *
 * @author Gunnar Hillert
 */
public interface JobCountPerDayDao extends GenericDao < JobCountPerDay, Long >{

	/**
	 * Method for getting a single job count per day object.
	 *
	 * @param day The day for which the object shall be retrieved
	 *
	 * @return JobCountPerDay object
	 */
	JobCountPerDay getByDate(Date day);

	/**
	 *
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	List<JobCountPerDay> getJobCountPerDayAndPeriod(Date fromDate, Date toDate);

	/**
	 *
	 * @return
	 */
	List<JobCountPerDay> getLatestTwoJobCounts();

}
