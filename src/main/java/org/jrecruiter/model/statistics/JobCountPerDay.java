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
package org.jrecruiter.model.statistics;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Used for portraying statistical information regarding how many jobs have been
 * posted for that day.
 *
 * @author Gunnar Hillert
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames="jobDate"))
public class JobCountPerDay {

	//~~~~~Fields~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@XmlAttribute
	private Long id;

	@Column(unique=false, nullable=true, insertable=true, updatable=true, length=8)
	@Temporal(TemporalType.DATE)
	@XmlAttribute
	private java.util.Date jobDate;

	@Column(unique=false, nullable=false, insertable=true, updatable=true, precision=0, scale=10)
	@XmlAttribute
	private Long numberOfJobsPosted  = Long.valueOf(0);

	@Column(unique=false, nullable=false, insertable=true, updatable=true, precision=0, scale=10)
	@XmlAttribute
	private Long numberOfJobsDeleted = Long.valueOf(0);

	@Column(unique=false, nullable=false, insertable=true, updatable=true, precision=0, scale=10)
	@XmlAttribute
	private Long totalNumberOfJobs   = Long.valueOf(0);

	@XmlAttribute
	private Boolean automaticallyCleaned   = Boolean.FALSE;

	//~~~~~Constructor~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 * @param jobDate
	 * @param numberOfJobsPosted
	 * @param numberOfJobsDeleted
	 * @param totalNumberOfJobs
	 */
	public JobCountPerDay(Date jobDate, Long numberOfJobsPosted,
			Long numberOfJobsDeleted, Long totalNumberOfJobs) {
		super();
		this.jobDate = jobDate;
		this.numberOfJobsPosted = numberOfJobsPosted;
		this.numberOfJobsDeleted = numberOfJobsDeleted;
		this.totalNumberOfJobs = totalNumberOfJobs;
	}

	/**
	 */
	public JobCountPerDay() {
		super();
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return String.format("%s[jobDate=%s, numberOfJobsPosted=%s]",
				super.toString(),
				this.jobDate,
				this.numberOfJobsPosted);
	}

	//~~~~~Getters and Setters~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public java.util.Date getJobDate() {
		return this.jobDate;
	}

	public long getJobDateInMillis() {
		return this.jobDate.getTime();
	}

	public Long getNumberOfJobsPosted() {
		return numberOfJobsPosted;
	}

	public Long getNumberOfJobsDeleted() {
		return numberOfJobsDeleted;
	}

	public void setNumberOfJobsDeleted(Long numberOfJobsDeleted) {
		this.numberOfJobsDeleted = numberOfJobsDeleted;
	}

	public Long getTotalNumberOfJobs() {
		return totalNumberOfJobs;
	}

	public void setTotalNumberOfJobs(Long totalNumberOfJobs) {
		this.totalNumberOfJobs = totalNumberOfJobs;
	}

	public void setJobDate(java.util.Date jobDate) {
		this.jobDate = jobDate;
	}

	public void setNumberOfJobsPosted(Long numberOfJobsPosted) {
		this.numberOfJobsPosted = numberOfJobsPosted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAutomaticallyCleaned() {
		return automaticallyCleaned;
	}

	public void setAutomaticallyCleaned(Boolean automaticallyCleaned) {
		this.automaticallyCleaned = automaticallyCleaned;
	}

}
