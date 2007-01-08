/**
 *
 */
package org.jrecruiter.dao;
import java.util.List;

import org.jrecruiter.model.UserRole;
import org.testng.annotations.Test;

/**
 * @author Gunnar Hillert 
 *
 */
public class RoleDaoTNG extends BaseDaoTNG {

	private UserDAO userDao;
	
	private UserRoleDAO userRoleDao;
	
	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	/**
	 * 
	 *
	 */
      @Test(groups = {"daoIntegrationTest"})
        public void getAllRolesTest() {

        List < UserRole > roles = userDao.getAllRoles();
        LOGGER.info("Number of found roles: " + roles.size());
      }
}
