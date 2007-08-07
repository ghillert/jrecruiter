/**
 *
 */
package org.jrecruiter.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jrecruiter.model.User;
import org.jrecruiter.test.BaseTest;
import org.springframework.dao.DataAccessException;

/**
 * @author Gunnar Hillert
 *
 */
public class UserDaoTest extends BaseTest {

	private UserDao userDao;

	public void setUserDao(UserDao userDAO) {
		this.userDao = userDAO;
	}

	/**
	 * Initialize Logging.
	 */
	public static final Logger LOGGER = Logger.getLogger(UserDaoTest.class);

	public void testGetAllUsers() {

		List<User> users = userDao.getAllUsers();
		LOGGER.info("Number of users found: " + users.size());
		LOGGER.info("Details of first user: " + users.get(0));
	}

	public void testGetSingleUser() {
		System.out.println("exec");

		User user = userDao.getUser("admin");

		LOGGER.info(user);

	}

	/**
	 *
	 *
	 */
	public void testAddAndRemoveUserTest() {

		User user = new User();
		user.setUsername("demo44");
		user.setEmail("demo@demo.com");
		user.setFirstName("Demo First Name");
		user.setLastName("Demo Last Name");
		user.setPassword("demo");
		user.setPhone("123456");
		user.setRegistrationDate(new Date());
		userDao.save(user);

		assertNotNull(user.getUsername());
		assertTrue(user.getFirstName().equals("Demo First Name"));
		assertNotNull(user.getId());

		userDao.deleteUser(new String[] { user.getUsername() });

		try {

			LOGGER.info("Retrieving User: " + user.getUsername());
			user = userDao.getUser(user.getUsername());
			LOGGER.info(user);
			if (user != null) {
				fail("User found in database");
			}
		} catch (DataAccessException dae) {
			LOGGER.debug("Expected exception: " + dae.getMessage());
			assertNotNull(dae);
		}

	}

}
