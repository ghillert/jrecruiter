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
package org.jrecruiter.service.migration;


/**
 * This helper service contains methods that should be helpful for migrating jRecruiter
 * data from older Database schemas to newer version. Therefore, this is probably
 * not functionality used during production. If user have the need to migrate from
 * there special database environments, this should be the place to look for.
 *
 * @author Gunnar Hillert
 */
public interface MigrationService {

	/**
	 * Method for migrating user data.
	 *
	 * @param digestPasswords Shall passwords be 'digested'?
	 */
	void migrateUserData(Boolean digestPasswords);

	/**
	 * Migrate Job Data
	 */
	void migrateJobData();

	/**
	 * Method for migrating settings.
	 */
	void migrateSettings();

	/**
	 * Method for migrating regions.
	 */
	void migrateRegions();

	/**
	 * Method for migrating industries.
	 */
	void migrateIndustries();

}
