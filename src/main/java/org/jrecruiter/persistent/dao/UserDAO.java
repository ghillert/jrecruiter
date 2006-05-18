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
package org.jrecruiter.persistent.dao;

import org.jrecruiter.model.User;
import org.jrecruiter.model.UserRole;

import java.util.List;

/**
 * @author Dorota Puchala, Gunnar Hillert
 * @version @version $Id$
 */
public interface UserDAO {

    /**
     * Add a user to persistence store.
     * @param role Role of the user
     * @param user User object
     */
    void addUser(User user, String role);

    /**
     * Get a user from persistence store.
     * @param username
     * @return A single user
     */
    User getUser(String username);

    /**
     * Update a user in persistence store.
     * @param A user object
     */
    void updateUser(User user);

    /**
     * Return all users from persistence store.
     * @return List of users
     */
    List < User > getAllUsers();

    /**
     * Delete an array of users from persistence store.
     *
     * @param usernameList list of user names.
     */
    void deleteUser(String[] usernameList);

    /**
     * Retrieve all roles from persistence store. Currently only used for testing.
     * @return List of user roles.
     */
    List < UserRole > getAllRoles();
}
