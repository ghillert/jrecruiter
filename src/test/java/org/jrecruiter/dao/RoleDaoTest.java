/**
 *
 */
package org.jrecruiter.dao;

import java.util.List;

import org.jrecruiter.model.Role;
import org.jrecruiter.test.BaseTest;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
public class RoleDaoTest extends BaseTest {

	private RoleDao roleDao;

	/**
	 * @param roleDao the roleDao to set
	 */
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public void testSave() {

		final Role role = new Role(null, "TEST");
		roleDao.save(role);

		assertNotNull(role.getId());

		List<Role> roles = roleDao.getAll();

		assertTrue(roles.size() >= 1);
	}

	public void testGetRole() {

		final Role role = new Role(null, "TEST");
		roleDao.save(role);

		assertNotNull(role.getId());

		Role role2 = roleDao.getRole("TEST");

		assertNotNull(role2);
	}
}
