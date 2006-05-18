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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.jrecruiter.model.User;
import org.jrecruiter.model.UserRole;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author Dorota Puchala
 * @version $Revision$, $Date$, $Author$
 */
public class UserDAOHibernate extends HibernateDaoSupport
            implements UserDAO, UserDetailsService {

    /**
     *   Initialize Logging.
     */
    public static final Logger LOGGER = Logger.getLogger(UserDAOHibernate.class);

    /**
     * Constructor.
     *
     */
    private UserDAOHibernate() {
    }

    /**
     * Add a user to persistence store.
     * @param role Role of the user
     * @param user User object
     */
    public void addUser(User user, String role) {

        UserRole userRole = new UserRole();
        userRole.setName(role);
        Set < UserRole > set = new HashSet < UserRole >();

        set.add(userRole);
        user.setRoles(set);

        getHibernateTemplate().save(user);

    }

    /**
     * Get a user from persistence store.
     * @param username
     * @return A single user
     */
    public User getUser(String username) {

        final Session session = getSession(false);
        final User user;

        try {

            final Query query = session.createQuery("from User user left join fetch user.roles "
                    + "where user.username= :username");
            query.setString("username", username);

            user = (User) query.uniqueResult();

        } catch (HibernateException ex) {
            throw convertHibernateAccessException(ex);
        }

        return user;
    }

    /**
     * Update a user in persistence store.
     * @param A user object
     *
     */
    public void updateUser(User user) {

        getHibernateTemplate().update(user);

    }

    /**
     * Return all users from persistence store.
     * @return List of users
     */
    @SuppressWarnings("unchecked")
    public List < User > getAllUsers() {

         List < User >  users = getHibernateTemplate().find(
                "from User as user order by user.username");

        return users;
    }

    /**
     * Delete an array of users from persistence store.
     *
     * @param usernameList list of user names.
     */
    public void deleteUser(final String[] usernameList) {

        List list = (List) getHibernateTemplate().execute(
                new HibernateCallback() {

                    public Object doInHibernate(final Session session)
                            throws HibernateException {
                        List list = session.createCriteria(User.class).add(
                                Expression.in("username", usernameList)).list();
                        return list;
                    }

                });

        for (int i = 0; i < list.size(); i++) {
            User user = (User) list.get(i);

            getHibernateTemplate().delete(user);
        }
    }


    /**
     * This method is used by ACEGI security to load user details for authentication.
     * @see org.acegisecurity.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     *
     * @param username Username
     * @return Details of the user
     * @throws DataAccessException
     * @throws UsernameNotFoundException Thrown if no user was found in persistence store.
     */
    @SuppressWarnings("unchecked")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

        UserDetails user;

        user = this.getUser(username);

        if (user==null){

            LOGGER.warn("loadUserByUsername() - No user with id " + username + " found.");
            throw new UsernameNotFoundException("loadUserByUsername() - No user with id " + username + " found.");
        }

        LOGGER.info(user.getAuthorities());

        return user;
    }

    /**
     * Retrieve all roles from persistence store. Currently only used for testing.
     * @return List of user roles.
     */
    @SuppressWarnings("unchecked")
    public List < UserRole > getAllRoles() {

        List < UserRole >  userRoles = getHibernateTemplate().find(
               "from UserRole as role order by role.name ASC");

       return userRoles;
   }

}
