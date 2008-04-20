/**
 *
 */
package org.jrecruiter.dao;

import java.util.List;

import org.jrecruiter.model.Role;
import org.jrecruiter.test.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
public class RoleDaoTest extends BaseTest {

	private @Autowired RoleDao roleDao;

	public void testSave() {

		final Role role = new Role(null, "TEST");
		Role savedRole = roleDao.save(role);

		assertNotNull(savedRole.getId());

		List<Role> roles = roleDao.getAll();

		assertTrue(roles.size() >= 1);
	}

	public void testGetRole() {

		final Role role = new Role(null, "TEST");
		Role savedRole = roleDao.save(role);

		assertNotNull(savedRole.getId());

		Role role2 = roleDao.getRole("TEST");

		assertNotNull(role2);
	}
}
