/**
 *
 */
package org.jrecruiter.dao;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.jrecruiter.model.User;
import org.jrecruiter.test.BaseTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
public class UserDaoTest extends BaseTest {

    private @Autowired UserDao userDao;

    /**
     * Initialize Logging.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(UserDaoTest.class);

    @Test
    public void testGetAllUsers() {

        List<User> users = userDao.getAllUsers();
        LOGGER.info("Number of users found: " + users.size());
        LOGGER.info("Details of first user: " + users.get(0));
    }

    @Test
    public void testGetSingleUser() {
        User user = userDao.getUser("admin");
        LOGGER.info(user.toString());
    }

    /**
     *
     *
     */
    @Test
    public void testAddAndRemoveUser() {

        final User user = getUser();
        User savedUser = userDao.save(user);

        Assert.assertNotNull(savedUser.getUsername());
        Assert.assertTrue(savedUser.getFirstName().equals("Demo First Name"));
        entityManager.flush();
        Assert.assertNotNull(savedUser.getId());

        userDao.deleteUser(new String[] { savedUser.getUsername() });

        try {

            LOGGER.info("Retrieving User: " + savedUser.getUsername());
            User userFromDb = userDao.getUser(savedUser.getUsername());

            if (userFromDb != null) {
            	Assert.fail("User found in database");
            }
        } catch (DataAccessException dae) {
            LOGGER.debug("Expected exception: " + dae.getMessage());
            Assert.assertNotNull(dae);
        }

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
