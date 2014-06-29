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
package org.jrecruiter.dao.jpa;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.jrecruiter.dao.JobCountPerDayDao;
import org.jrecruiter.model.statistics.JobCountPerDay;
import org.springframework.stereotype.Repository;

/**
 * This DAO provides job-related database methods.
 *
 * @author Puchala, Gunnar Hillert
 */
@Repository("jobCountPerDayDao")
public final class JobCountPerDayDaoJpa extends GenericDaoJpa< JobCountPerDay, Long>
implements JobCountPerDayDao {

	/**
	 * Constructor.
	 *
	 */
	private JobCountPerDayDaoJpa() {
		super(JobCountPerDay.class);
	}

	/** {@inheritDoc} */
	public JobCountPerDay getByDate(Date day) {
		Session session = (Session)entityManager.getDelegate();
		Query query = session.createQuery("select jcpd from JobCountPerDay jcpd "
				+ " where jcpd.jobDate = :jobDate ");
		query.setDate("jobDate", day);

		return (JobCountPerDay)query.uniqueResult();
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	public List<JobCountPerDay> getJobCountPerDayAndPeriod(Date fromDate, Date toDate) {

		Session session = (Session)entityManager.getDelegate();
		Query query = session.createQuery("select jcpd from JobCountPerDay jcpd "
				+ " where jcpd.jobDate >= :fromDate and jcpd.jobDate <= :toDate "
				+ " order by jobDate asc");
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);

		List<JobCountPerDay> jobCountPerDayList = query.list();

		return jobCountPerDayList;
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	public List<JobCountPerDay> getLatestTwoJobCounts() {
		Session session = (Session)entityManager.getDelegate();
		Query query = session.createQuery("select jcpd from JobCountPerDay jcpd "
				+ " order by jobDate desc");

		query.setMaxResults(2);
		final List<JobCountPerDay> jobCountPerDayList = query.list();

		return jobCountPerDayList;
	}

}


