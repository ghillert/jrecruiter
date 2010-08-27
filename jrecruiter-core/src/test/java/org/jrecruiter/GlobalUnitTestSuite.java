package org.jrecruiter;

import org.jrecruiter.common.GoogleMapsUtilsUnitTest;
import org.jrecruiter.common.SystemInformationUtilsUnitTest;
import org.jrecruiter.service.JobServiceUnitTest;
import org.jrecruiter.service.SystemSetupServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	UtilsUnitTest.class,
	GoogleMapsUtilsUnitTest.class,
	SystemInformationUtilsUnitTest.class,
	JobServiceUnitTest.class,
	SystemSetupServiceTest.class
})
public class GlobalUnitTestSuite {

}
