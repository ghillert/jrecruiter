/**
 *
 */
package org.jrecruiter.dao;

import java.util.List;

import junit.framework.Assert;

import org.jrecruiter.common.Constants.Roles;
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

    /**
     * Test to verify that the seed data is correctly populated. Typically there
     * should be 2 roles in the system:
     *
     * <ul>
     *   <li>Admin Role</li>
     *   <li>Manager Role</li>
     * </ul>
     *
     */
    @Test
    public void testVerifyExistenceOfRoleSeedData() {

        final List<Role> roles = roleDao.getAll();

        boolean managerRoleFound = false;
        boolean adminRoleFound = false;

        for (final Role role : roles) {

            if (Roles.ADMIN.name().equals(role.getName())) {
                adminRoleFound = true;
            } else if (Roles.MANAGER.name().equals(role.getName())) {
                managerRoleFound = true;
            }

        }

        Assert.assertTrue("Expected the manager role. Is seed data populated?", managerRoleFound);
        Assert.assertTrue("Expected the admin role. Is seed data populated?",   adminRoleFound);

    }
}
