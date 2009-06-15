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

import java.util.List;
import java.util.Map;

import org.jrecruiter.model.User;
import org.jrecruiter.service.exceptions.DuplicateUserException;
import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UsernameNotFoundException;

/**
 * Provides user related methods.
 *
 * @author Dorota Puchala
 * @author Gunnar Hillert
 *
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
public interface UserService {

    /**
     * Adds a brand new user to the system. If a user
     * with the username already exists a duplicate user exception
     * is thrown.
     *
     * @param user The user to add
     * @throws DuplicateUserException
     */
    User addUser(User user) throws DuplicateUserException;

    /**
     * Adds a brand new user to the system. If a user
     * with the username already exists a duplicate user exception
     * is thrown.
     *
     * @param user The user to add
     * @param accountValidationUrl Url that handles account validation
     * @throws DuplicateUserException
     */
    User addUser(User user, String accountValidationUrl) throws DuplicateUserException;

    /**
     * Load a user by the provided username
     * @param username
     * @return a single user
     */
    User getUser(String username);

    /**
     * Load a user by the provided user id.
     * @param userId
     * @return a single user
     */
    User getUser(Long userId);

    /**
     * Load a user by the provided user id.
     * @param userId
     * @return a single user
     */
    void updateUser(User user);

    /**
     * Get a list of all users.
     * @return List of users
     */
    List<User> getAllUsers();

    /**
     * Method for returning a filtered list of available job postings.
     *
     * @return List of jobs.
     */
    List < User > getUsers(Integer pageSize, Integer pageNumber, Map<String, String> sortOrders, Map<String, String> userFilters);

    /**
     * Returns the number of totally available jobs in the system.
     *
     * @return Total number of jobs
     */
    Long getUsersCount();

    /**
     * Delete a user from the system.
     * @param user
     */
    void deleteUser(User user);

    /**
     * Reset the password for the provided user.
     * @param user
     */
    void resetPassword(User user);

    /**
     * This method is used by ACEGI security to load user details for authentication.
     * @see org.acegisecurity.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     *
     * @param username Username
     * @return Details of the user
     * @throws DataAccessException
     * @throws UsernameNotFoundException Thrown if no user was found in persistence store.
     */
    UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException;

    /**
     * Get a user by its verification key. This method is used to verify user
     * account creation.
     *
     * @param key Key for which the corresponding user supposedly exists
     * @return Return a user for the existing key
     */
    User getUserByVerificationKey(String key);
}
