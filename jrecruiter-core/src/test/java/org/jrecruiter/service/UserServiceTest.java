/*
*	http://www.jrecruiter.org
*
*	Disclaimer of Warranty.
*
*	Unless required by applicable law or agreed to in writing, Licensor provides
*	the Work (and each Contributor provides its Contributions) on an "AS IS" BASIS,
*	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied,
*	including, without limitation, any warranties or conditions of TITLE,
*	NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE. You are
*	solely responsible for determining the appropriateness of using or
*	redistributing the Work and assume any risks associated with Your exercise of
*	permissions under this License.
*
*/
package org.jrecruiter.service;

import java.util.Date;

import junit.framework.Assert;

import org.jasypt.digest.StringDigester;
import org.jrecruiter.dao.RoleDao;
import org.jrecruiter.model.User;
import org.jrecruiter.service.exceptions.DuplicateUserException;
import org.jrecruiter.test.BaseServiceIntegrationTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
public class UserServiceTest extends BaseServiceIntegrationTest {

    @Autowired UserService userService;
    @Autowired RoleDao roleDao;
    @Autowired StringDigester stringDigester;
    @Autowired org.jasypt.spring.security3.PasswordEncoder passwordEncoder;

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);

    @Test
    public void testAddUserTest(){

        final User user = getUser();
        final User user2 = getUser();

        try {
            userService.addUser(user);
            entityManager.flush();
        } catch (DuplicateUserException e) {
        	Assert.fail();
        }
        try {
            userService.addUser(user2);
            entityManager.flush();
        } catch (DuplicateUserException e) {
        	Assert.assertNotNull(e.getMessage());
            return;
        }
        Assert.fail();
    };

    @Test
    public void testAddUserTest2(){

        final User user = getUser();

        user.setPassword("jrecruiter");

        try {
            userService.addUser(user);
            entityManager.flush();
        } catch (DuplicateUserException e) {
        	Assert.fail();
        }

        final User userFromDb = userService.getUser(user.getUsername());

        //FIXME password digestion should probbly be a part of saving a user through the service
        Assert.assertEquals("jrecruiter", userFromDb.getPassword());

    };

    //TODO
    public void getUserTest(){}

    //TODO
    public void updateUserTest(){}

    //TODO
    public void getAllUsersTest(){}

    //TODO
    public void deleteUserTest(){}

    @Test
    public void testSendPassword() throws Exception {
        final User user = getUser();
        User savedUser = userService.addUser(user);
        entityManager.flush();

        userService.resetPassword(savedUser);

    }

    @Test
    public void testLoadUserByUsername() {

        final User user = getUser();

        try {
            userService.addUser(user);
            entityManager.flush();
        } catch (DuplicateUserException e) {
        	Assert.fail();
        }

        final UserDetails user2 = userService.loadUserByUsername(user.getUsername());

        Assert.assertNotNull(user2);
    }

    @Test
    public void testPasswordEncoder() {
        boolean expectation = passwordEncoder.isPasswordValid("ccWyzbWK1zdTgCy3pz28mN/VE3SSLvblaolK2znAoTGZ00zLHs61ZxemLc1YrlLIBvgbMWi7x3fxg7AdtwCxr8HxiBunE5hX/o9JuGbcm/w=", "jrecruiter", null);
        Assert.assertTrue(expectation);
    }

    //~~~~~Helper Methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

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
