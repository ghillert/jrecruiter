/**
 *
 */
package org.jrecruiter.dao;

import java.util.Date;

import junit.framework.Assert;

import org.jrecruiter.model.User;
import org.jrecruiter.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
public class DaoAuthenticationProviderTest extends BaseTest {

    private @Autowired UserDao userDao;

    @Test
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

        UserDetails user2 = userDao.getUser("demo44");
        Assert.assertNotNull(user2);

    }

}
