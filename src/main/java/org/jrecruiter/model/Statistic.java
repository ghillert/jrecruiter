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
package org.jrecruiter.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Parameter;

/**
 * Holds statistical information about a job posting.
 *
 * @author Gunnar Hillert
 */
@Entity
@Table(uniqueConstraints = {  })
@XmlAccessorType(XmlAccessType.FIELD)
public class Statistic implements Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -6450245841259870972L;

	// Fields
	@XmlAttribute
	private Long  id;

	@XmlTransient
	private Job job;

	@XmlAttribute
	private Long counter;

	@XmlAttribute
	private Date lastAccess;

	// Constructors

	/** default constructor */
	public Statistic() {
	}

	/** full constructor */
	public Statistic(Long id, Long counter, Date lastAccess, Long uniqueVisits) {
		this.id = id;
		this.counter = counter;
		this.lastAccess = lastAccess;
	}

	@Id
	@GeneratedValue(generator="myForeignGenerator")
	@org.hibernate.annotations.GenericGenerator(
		name="myForeignGenerator",
		strategy="foreign",
		parameters=@Parameter(name="property", value="job")
	)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne(fetch=FetchType.LAZY, optional=false)
	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	@Column(name="counter", unique=false, nullable=false, insertable=true, updatable=true)
	public Long getCounter() {
		return this.counter;
	}

	public void setCounter(Long counter) {
		this.counter = counter;
	}

	@Column(name="last_access", unique=false, nullable=true, insertable=true, updatable=true, length=8)
	public Date getLastAccess() {
		return this.lastAccess;
	}

	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}

	@Override
	public String toString()
	{
		final String TAB = " | ";

		final StringBuilder retValue = new StringBuilder();

		retValue.append("Statistic ( ")
			.append(super.toString()).append(TAB)
			.append("id = ").append(this.getId()).append(TAB)
			.append("counter = ").append(this.getCounter()).append(TAB)
			.append("lastAccess = ").append(this.getLastAccess()).append(TAB)
			.append(" )");

		return retValue.toString();
	}

}

