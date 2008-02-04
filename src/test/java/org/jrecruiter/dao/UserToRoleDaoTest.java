/**
 *
 */
package org.jrecruiter.dao;

import java.util.Date;

import org.jrecruiter.model.Role;
import org.jrecruiter.model.User;
import org.jrecruiter.model.UserToRole;
import org.jrecruiter.test.BaseTest;

/**
 * @author Gunnar Hillert
 *
 */
public class UserToRoleDaoTest extends BaseTest {

    private UserDao       userDao;
    private UserToRoleDao userToRoleDao;
    private RoleDao       roleDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * @param roleDao the roleDao to set
     */
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public void setUserToRoleDao(UserToRoleDao userToRoleDao) {
        this.userToRoleDao = userToRoleDao;
    }

    public void testSaveUserToRole() {

        final Role role = new Role(null, "TEST");
        roleDao.save(role);

        User user = userDao.get(160L);

        UserToRole userToRole = new UserToRole(null, role, user);

        userToRoleDao.save(userToRole);
        super.flushSession();

        assertNotNull(userToRole.getId());
    }

    public void testSaveUserToRole2() {

        final Role role = new Role(null, "TEST");
        roleDao.save(role);

        User user = getUser();

        UserToRole userToRole = new UserToRole(null, role, user);

        assertTrue(user.getUserToRoles().size() == 0);

        user.getUserToRoles().add(userToRole);

        assertTrue(user.getUserToRoles().size() == 1);

        userDao.save(user);
        super.flushSession();

        assertNotNull(userToRole.getId());
    }


    private User getUser() {

        final User user = new User();
        user.setUsername("demo44");
        user.setEmail("demo@demo.com");
        user.setFirstName("Demo First Name");
        user.setLastName("Demo Last Name");
        user.setPassword("demo");
        user.setPhone("123456");
        user.setRegistrationDate(new Date());

        return user;
    }
}
