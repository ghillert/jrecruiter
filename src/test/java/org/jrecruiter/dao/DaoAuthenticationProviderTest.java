/**
 *
 */
package org.jrecruiter.dao;

import java.util.Date;

import org.apache.log4j.Logger;
import org.jrecruiter.model.User;
import org.jrecruiter.service.UserService;
import org.jrecruiter.test.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.userdetails.UserDetails;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
public class DaoAuthenticationProviderTest extends BaseTest {

    private @Autowired UserService userService;
    private @Autowired UserDao userDao;

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

        UserDetails user2 = userService.loadUserByUsername("demo44");
        assertNotNull(user2);

    }

}
