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
package org.jrecruiter.dao.hibernate;

import java.util.List;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.jrecruiter.dao.UserDao;
import org.jrecruiter.model.Role;
import org.jrecruiter.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * @author Dorota Puchala
 * @version $Id: UserDAOHibernate.java 24 2006-05-18 03:09:15Z ghillert $
 */
public class UserDaoHibernate extends GenericDaoHibernate< User, Long>
            implements UserDao, UserDetailsService {

    /**
     *   Initialize Logging.
     */
    public static final Logger LOGGER = Logger.getLogger(UserDaoHibernate.class);

    /**
     * Constructor.
     *
     */
    private UserDaoHibernate() {
    	super(User.class);
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

            final Query query = session.createQuery(
            			"select user from User user " +
            			"left join fetch user.userToRoles utr " +
            			"left join fetch utr.role "
                    + "where user.username= :username ");
            query.setString("username", username);

            user = (User) query.uniqueResult();

        } catch (HibernateException ex) {
            throw convertHibernateAccessException(ex);
        }

        return user;
    }

    /**
     * Return all users from persistence store.
     * @return List of users
     */
    @SuppressWarnings("unchecked")
    public List < User > getAllUsers() {

         List < User >  users = getHibernateTemplate().find(
                "from User user order by user.username ASC");

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
}
