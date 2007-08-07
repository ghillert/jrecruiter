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
package org.jrecruiter.dao;

import org.jrecruiter.model.Industry;
import org.jrecruiter.model.User;
import org.jrecruiter.model.Role;

import java.util.List;

/**
 * @author Dorota Puchala, Gunnar Hillert
 * @version @version $Id: UserDAO.java 24 2006-05-18 03:09:15Z ghillert $
 */
public interface UserDao extends GenericDao < User, Long >{

    /**
     * Get a user from persistence store.
     * @param username
     * @return A single user
     */
    User getUser(String username);

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

}
