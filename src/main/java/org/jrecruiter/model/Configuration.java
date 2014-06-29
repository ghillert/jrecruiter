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
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.hibernate.annotations.Type;

/**
 * This class represents configuration data.
 *
 * @author Gunnar Hillert
 */
@Entity
@Table(uniqueConstraints = {  }
)
@XmlAccessorType(XmlAccessType.FIELD)
public class Configuration implements Serializable {

	public enum ConfigurationKey {

		SEED_DATA("seed.data.is.setup");

		private String key;

		private ConfigurationKey(String key) {
			this.key = key;
		}

		public String getKey() {
			return key;
		}

	}

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 7279232706235477101L;

	// Fields

	@Id
	@Column(length=200)
	private String messageKey;

	@Column(unique=false, nullable=true, insertable=true, updatable=true)
	@Type(type="text")
	private String messageText;

	@Column(unique=false, nullable=true, insertable=true, updatable=true, length=8)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModified;

	// Constructors

	/** default constructor */
	public Configuration() {
	}

	/** minimal constructor */
	public Configuration(String messageKey) {
		this.messageKey = messageKey;
	}
	/** full constructor */
	public Configuration(String messageKey, String messageText, Date lastModified) {
		this.messageKey = messageKey;
		this.messageText = messageText;
		this.lastModified = lastModified;
	}

	// Property accessors

	public String getMessageKey() {
		return this.messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public String getMessageText() {
		return this.messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public Date getLastModified() {
		return this.lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
}

