/**
 *
 */
package org.jrecruiter.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.IOUtils;
import org.jrecruiter.common.CalendarUtils;
import org.jrecruiter.common.CollectionUtils;
import org.jrecruiter.common.Constants.UserAuthenticationType;
import org.jrecruiter.dao.BackupDao;
import org.jrecruiter.dao.jaxb.BackupDaoJaxb;
import org.jrecruiter.model.Industry;
import org.jrecruiter.model.Role;
import org.jrecruiter.model.User;
import org.jrecruiter.model.UserToRole;
import org.jrecruiter.model.export.Backup;
import org.jrecruiter.service.system.impl.SystemSetupServiceImpl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.util.ReflectionTestUtils;
import org.jrecruiter.scala.Region;

/**
 * Contains test methods to verify the deserialization of backup data using Jaxb.
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
public class SystemSetupServiceTest {

	public static final Logger LOGGER = LoggerFactory.getLogger(SystemSetupServiceTest.class);

	private SystemSetupService createSystemSetupService() throws Exception {


		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();

		BackupDao backupDao = new BackupDaoJaxb();

		jaxb2Marshaller.setClassesToBeBound(new Class[]{Backup.class});
		jaxb2Marshaller.afterPropertiesSet();

    	final SystemSetupService systemSetupService = new SystemSetupServiceImpl();
    	ReflectionTestUtils.setField(backupDao, "marshaller", jaxb2Marshaller);
    	ReflectionTestUtils.setField(systemSetupService, "backupDao", backupDao);

    	return systemSetupService;

	}

	@Test
	public void testConvertToBackupData() throws Exception {

    	final java.io.InputStream inputStream =  DemoServiceTest.class.getResourceAsStream("/org/jrecruiter/server/seeddata/seeddata.xml");
    	final SystemSetupService systemSetupService = this.createSystemSetupService();

    	final Backup backup = systemSetupService.convertToBackupData(inputStream);

    	final List<Industry> industries = backup.getIndustries();
    	final List<Region>   regions    = backup.getRegions();
    	final List<Role>     roles      = backup.getRoles();

    	assertTrue("Expecting 16 industries but found: " + industries.size(), industries.size() == 16);
    	assertTrue("Expecting 10 regions but found: " + regions.size(), regions.size() == 10);
    	assertTrue("Expecting 2 roles but found: " + roles.size(), roles.size() == 2);

    	for (final Industry industry : industries) {
    		assertNotNull(industry.getId());
    		assertNotNull(industry.getName());
    	}

    	for (final Region region : regions) {
    		assertNotNull(region.getId());
    		assertNotNull(region.getName());
    	}

    	for (final Role role : roles) {
    		assertNotNull(role.getId());
    		assertNotNull(role.getName());
    	}

    	final List<User>     users       = backup.getUsers();

    	assertTrue("Expecting 1 user but found: " + users.size(), users.size() == 1);

    	for (final User user : users) {
    		assertNotNull(user.getId());
    		assertNotNull(user.getUsername());

    		assertEquals("aswCoBHvJHtCSyJWRHvch0e4sKflhzkRsP8bUsf1FPTjEkFi3nlQbgtbRx95GluwVr82Ol6WHaLiW/eeoJzXvwjZjEYPgrVYuZmm3Xn0Rek=", user.getPassword());
    	}
	}

	@Test
	public void testConvertTestToBackupData() throws Exception {

    	final java.io.InputStream inputStream =  DemoServiceTest.class.getResourceAsStream("/org/jrecruiter/server/seeddata/demodata.xml");

    	final SystemSetupService systemSetupService = this.createSystemSetupService();

    	final Backup backup = systemSetupService.convertToBackupData(inputStream);

    	final List<Industry> industries = backup.getIndustries();
    	final List<Region>   regions    = backup.getRegions();
    	final List<Role>     roles      = backup.getRoles();

    	assertTrue("Expecting 0 industries but found: " + industries.size(), industries.size() == 0);
    	assertTrue("Expecting 0 regions but found: " + regions.size(), regions.size() == 0);
    	assertTrue("Expecting 0 roles but found: " + roles.size(), roles.size() == 0);

    	final List<User>     users       = backup.getUsers();

    	assertTrue("Expecting 1 user but found: " + users.size(), users.size() == 1);

    	for (final User user : users) {
    		assertNotNull(user.getId());
    		assertNotNull(user.getUsername());

    		assertTrue("Expecting 1 role associated but found: " + user.getUserToRoles().size(), user.getUserToRoles().size() == 1);

    		for (UserToRole userToRole : user.getUserToRoles()) {
    		    assertNotNull(userToRole.getRoleName());
    		}

    	}

	}

	@Test
	public void testSimpleMarshelling() throws Exception {

		final String expectedString = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><backup><industries/><regions><region id=\"1\">MyRegion</region></regions><roles/><users/><jobs/><statistics/></backup>";

		final StringWriter stringWriter = new StringWriter();

		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();

		jaxb2Marshaller.setClassesToBeBound(new Class[]{Backup.class});
		jaxb2Marshaller.afterPropertiesSet();

    	Backup backup = new Backup();

    	Region region = new Region(1L, "MyRegion");

    	final List<Region> regions = CollectionUtils.getArrayList();

    	regions.add(region);

    	backup.setRegions(regions);

    	jaxb2Marshaller.marshal(backup, new StreamResult(stringWriter));

    	assertNotNull(stringWriter);

    	assertEquals(expectedString, stringWriter.toString());

	}

	@Test
	public void testUserMarshalling() throws Exception {

		final java.io.InputStream inputStream =  DemoServiceTest.class.getResourceAsStream("/org/jrecruiter/server/service/testUserMarshallingExpectation.txt");

		final String expectedString = IOUtils.toString(inputStream, "UTF-8");

		final StringWriter stringWriter = new StringWriter();

		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();

		jaxb2Marshaller.setClassesToBeBound(new Class[]{Backup.class});

		Map<String, Object> jaxbContextProperties = CollectionUtils.getHashMap();

		jaxbContextProperties.put(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		jaxb2Marshaller.setMarshallerProperties(jaxbContextProperties);
		jaxb2Marshaller.afterPropertiesSet();

    	final Backup backup = new Backup();

    	final User user = new User();
    	user.setCompany("company");
    	user.setEmail("email");
    	user.setEnabled(true);
    	user.setFax("123456789");
    	user.setFirstName("firstName");
    	user.setId(99L);

    	final Calendar lastLoginDate    = CalendarUtils.getCalendarInUTC(2010, 6, 25, 15, 50, 0);
    	final Calendar registrationDate = CalendarUtils.getCalendarInUTC(2010, 5, 25, 15, 50, 0);
    	final Calendar updateDate       = CalendarUtils.getCalendarInUTC(2010, 5, 24, 15, 50, 0);


    	user.setLastLoginDate(lastLoginDate.getTime());
    	user.setLastName("lastName");
    	user.setPassword("password");
    	user.setPhone("123456789");
    	user.setRegistrationDate(registrationDate.getTime());
    	user.setUpdateDate(updateDate.getTime());
    	user.setUserAuthenticationType(UserAuthenticationType.USERNAME_PASSWORD);
    	user.setUsername("username");

    	Role role = new Role(1L, "SPECIAL_ROLE");

    	user.getUserToRoles().add(new UserToRole(1L, role, user));
    	user.setVerificationKey("SOME_LONG_STRING");

    	final List<User> users = CollectionUtils.getArrayList();
    	final List<Role> roles = CollectionUtils.getArrayList();

    	users.add(user);

    	backup.setUsers(users);
    	backup.setRoles(roles);

    	jaxb2Marshaller.marshal(backup, new StreamResult(stringWriter));

    	LOGGER.info(stringWriter.toString());

    	assertNotNull(stringWriter);

    	assertEquals(expectedString, stringWriter.toString());

	}

}



