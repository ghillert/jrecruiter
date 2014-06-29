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

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;

import org.springframework.security.core.GrantedAuthority;

/**
 * Represents a User Role.
 *
 * @author Gunnar Hillert
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(uniqueConstraints = { @UniqueConstraint( columnNames = { "name" } ) }
)
public class Role extends BaseModelObject implements GrantedAuthority {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 7447568690062928081L;

	/** Name of the role. */
	@XmlAttribute @XmlID
	@Column(unique=true, nullable=false, insertable=true, updatable=true, length=50)
	private String name;

	/** Description. */
	@XmlValue
	@Column(unique=false, nullable=true, insertable=true, updatable=true)
	private String description;

	@XmlTransient
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="role")
	private Set<UserToRole> userToRoles = new HashSet<UserToRole>(0);

	// Constructors

	/** default constructor */
	public Role() {
	}

	public Role(final String name) {
		this.name = name;
	}

	/** minimal constructor */
	public Role(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	/** full constructor */
	public Role(Long id, String name, String description, Set<UserToRole> userToRoles) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.userToRoles = userToRoles;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<UserToRole> getUserToRoles() {
		return this.userToRoles;
	}

	public void setUserToRoles(Set<UserToRole> userToRoles) {
		this.userToRoles = userToRoles;
	}

	// For ACEGI

	/**
	 * @see org.acegisecurity.GrantedAuthority#getAuthority()
	 * @return name of the role
	 */
	@Transient
	public String getAuthority() {
		return name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getAuthority();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Role other = (Role) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	public int compareTo(Object o) {

		if (o == null) {
			return -1;
		} else {
			final Role role = (Role) o;
			return this.name.compareTo(role.getName());
		}

	}

}

