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
package org.jrecruiter;

import org.jrecruiter.dao.ConfigurationDaoTest;
import org.jrecruiter.dao.DaoAuthenticationProviderTest;
import org.jrecruiter.dao.IndustryDaoTest;
import org.jrecruiter.dao.JobDaoTest;
import org.jrecruiter.dao.RegionDaoTest;
import org.jrecruiter.dao.RoleDaoTest;
import org.jrecruiter.dao.StatisticDaoTest;
import org.jrecruiter.dao.UserDaoTest;
import org.jrecruiter.dao.UserToRoleDaoTest;
import org.jrecruiter.service.GoogleMapsStaticTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ConfigurationDaoTest.class,
	DaoAuthenticationProviderTest.class,
	IndustryDaoTest.class,
	JobDaoTest.class,
	RegionDaoTest.class,
	RoleDaoTest.class,
	StatisticDaoTest.class,
	UserDaoTest.class,
	UserToRoleDaoTest.class,
	GoogleMapsStaticTest.class
})
public class GlobalIntegrationTestSuite {

}
