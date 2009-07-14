/**
 *
 */
package org.jrecruiter.dao;

import java.util.List;

import junit.framework.Assert;

import org.jrecruiter.model.Role;
import org.jrecruiter.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
public class RoleDaoTest extends BaseTest {

	private @Autowired RoleDao roleDao;

    @Test
	public void testSave() {

		final Role role = new Role(null, "TEST");
		Role savedRole = roleDao.save(role);

		Assert.assertNotNull(savedRole.getId());

		List<Role> roles = roleDao.getAll();

		Assert.assertTrue(roles.size() >= 1);
	}

    @Test
	public void testGetRole() {

		final Role role = new Role(null, "TEST");
		Role savedRole = roleDao.save(role);

		Assert.assertNotNull(savedRole.getId());

		Role role2 = roleDao.getRole("TEST");

		Assert.assertNotNull(role2);
	}
}
