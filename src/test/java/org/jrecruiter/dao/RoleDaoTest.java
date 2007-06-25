/**
 *
 */
package org.jrecruiter.dao;

import java.util.List;

import org.jrecruiter.model.UserRole;
import org.jrecruiter.test.BaseTest;

/**
 * @author Gunnar Hillert
 * 
 */
public class RoleDaoTest extends BaseTest {

	private UserDAO userDao;

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	/**
	 * 
	 * 
	 */
	public void getAllRolesTest() {

		List<UserRole> roles = userDao.getAllRoles();
		LOGGER.info("Number of found roles: " + roles.size());
	}
}
