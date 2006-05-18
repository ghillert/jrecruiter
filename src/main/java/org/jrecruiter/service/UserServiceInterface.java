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

import org.jrecruiter.model.User;
import org.jrecruiter.service.exceptions.DuplicateUserException;

import java.util.List;

/**
 * @author Dorota Puchala
 * @version $Id$
 */
public interface UserServiceInterface {

    void addUser(User user) throws DuplicateUserException;

    User getUser(String username);

    void updateUser(User user);

    List<User> getAllUsers();

    void deleteUser(String[] usernameList);

    void sendPassword(User user);
}
