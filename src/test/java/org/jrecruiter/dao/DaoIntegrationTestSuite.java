package org.jrecruiter.dao;

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
	UserToRoleDaoTest.class
})
public class DaoIntegrationTestSuite {

}
