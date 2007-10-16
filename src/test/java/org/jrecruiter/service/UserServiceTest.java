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

import org.acegisecurity.userdetails.UserDetails;
import org.jrecruiter.model.User;
import org.jrecruiter.service.exceptions.DuplicateUserException;
import org.jrecruiter.test.BaseTest;

/**
 *
 * @author Gunnar Hillert
 * @version $Id: JobService.java 93 2007-01-22 02:42:14Z ghillert $
 */
public class UserServiceTest extends BaseTest {

	UserService userService;

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void testAddUserTest(){

		final User user = getUser();
		final User user2 = getUser();

		try {
			userService.addUser(user);
		    flushSession();
		} catch (DuplicateUserException e) {
			fail();
		}
		try {
			userService.addUser(user2);
			flushSession();
		} catch (DuplicateUserException e) {
			assertNotNull(e.getMessage());
			return;
		}
		fail();
	};

	//TODO
	public void getUserTest(){}

	//TODO
	public void updateUserTest(){}

	//TODO
	public void getAllUsersTest(){}

	//TODO
	public void deleteUserTest(){}

	public void testSendPassword() throws Exception {
		final User user = getUser();
		userService.addUser(user);
		flushSession();

		userService.sendPassword(user);

	}

	public void testLoadUserByUsername() {

		final User user = getUser();

		try {
			userService.addUser(user);
		} catch (DuplicateUserException e) {
			fail();
		}

		final UserDetails user2 = userService.loadUserByUsername(user.getUsername());

		assertNotNull(user2);
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
