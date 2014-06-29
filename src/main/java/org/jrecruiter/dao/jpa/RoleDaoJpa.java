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

import javax.persistence.NoResultException;

import org.jrecruiter.dao.RoleDao;
import org.jrecruiter.model.Role;
import org.springframework.stereotype.Repository;


/**
 *
 * @author Gunnar Hillert
 */
@Repository("roleDao")
public final class RoleDaoJpa extends GenericDaoJpa< Role, Long>
implements RoleDao {

	/**
	 * Constructor.
	 *
	 */
	private RoleDaoJpa() {
		super(Role.class);
	}

	/* (non-Javadoc)
	 * @see org.jrecruiter.dao.RoleDao#getRole(java.lang.String)
	 */
	public Role getRole(final String roleName) {

		final Role role;

		javax.persistence.Query query = entityManager
		.createQuery("select r from Role r "
				+ "where r.name = :role");
		query.setParameter("role", roleName);

		try {
			role = (Role) query.getSingleResult();
		} catch(NoResultException e) {
			throw new IllegalStateException(
					String.format("Role '%s' does not exist. Roles should always " +
							"be there. Is the seed data populated?", roleName));
		}

		return role;
	}

}
