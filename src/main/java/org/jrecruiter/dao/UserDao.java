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

import java.util.List;
import java.util.Map;

import org.jrecruiter.model.User;

/**
 * @author Dorota Puchala, Gunnar Hillert
 * @version @version $Id$
 */
public interface UserDao extends GenericDao < User, Long >{

    /**
     * Get a user from persistence store.
     * @param username
     * @return A single user
     */
    User getUser(String username);

    /**
     * Get a user from persistence store either by its username or the provided
     * email address. 
     * 
     * @param username
     * @return A single user
     */
    User getUserByUsernameOrEmail(String usernameOrEmail);
    
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
     * Method for returning list of available users.
     * @param pageSize Max number of results returned
     * @param pageNumber Which page are you one?
     * @param fieldSorted Which field shall be sorted
     * @param sortOrder What is the sort order?
     * @return List of users.
     */
    List < User > getUsers(Integer pageSize, Integer pageNumber, Map<String, String> sortOrders, Map<String, String> userFilters);

    /**
     * Returns the number of totally available users in the system.
     *
     * @return Total number of jobs
     */
    Long getUsersCount();
}
