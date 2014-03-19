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
