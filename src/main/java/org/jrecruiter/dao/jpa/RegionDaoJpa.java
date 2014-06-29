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

import java.util.List;

import org.jrecruiter.dao.RegionDao;
import org.springframework.stereotype.Repository;
import org.jrecruiter.model.Region;
/**
 *
 * @author Gunnar Hillert
 */
@Repository("regionDao")
public final class RegionDaoJpa extends GenericDaoJpa< Region, Long>
								  implements RegionDao {

	/**
	 * Constructor.
	 *
	 */
	private RegionDaoJpa() {
		super(Region.class);
	}

	@SuppressWarnings("unchecked")
	public List<Region> getAllRegionsOrdered() {

		final List<Region> regions;

		final javax.persistence.Query query = entityManager.createQuery(
				"select reg from Region reg " +
				"order by name ASC");

		regions = query.getResultList();

		return regions;
	}
}
