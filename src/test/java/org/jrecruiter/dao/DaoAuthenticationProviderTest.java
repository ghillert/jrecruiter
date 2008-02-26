/**
 *
 */
package org.jrecruiter.dao;

import java.util.Date;

import org.acegisecurity.providers.dao.DaoAuthenticationProvider;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.log4j.Logger;
import org.jrecruiter.model.User;
import org.jrecruiter.test.BaseTest;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
public class DaoAuthenticationProviderTest extends BaseTest {

	private DaoAuthenticationProvider daoAuthenticationProvider;

	private UserDao userDao;

	public void setUserDao(UserDao userDAO) {
		this.userDao = userDAO;
	}

	public void setDaoAuthenticationProvider(
			DaoAuthenticationProvider daoAuthenticationProvider) {
		this.daoAuthenticationProvider = daoAuthenticationProvider;
	}

	/**
	 *   Initialize Logging.
	 */
	public static final Logger LOGGER = Logger
			.getLogger(DaoAuthenticationProviderTest.class);

	public void testGetUser() {

		User user = new User();
		user.setUsername("demo44");
		user.setEmail("demo@demo.com");
		user.setFirstName("Demo First Name");
		user.setLastName("Demo Last Name");
		user.setPassword("demo");
		user.setPhone("123456");
		user.setRegistrationDate(new Date());
		userDao.save(user);

		UserDetails user2 = daoAuthenticationProvider.getUserDetailsService()
				.loadUserByUsername("demo44");
		assertNotNull(user2);

	}

}
