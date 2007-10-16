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

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.criterion.Expression;
import org.jrecruiter.dao.UserDao;
import org.jrecruiter.model.User;

/**
 * @author Dorota Puchala
 * @version $Id: UserDAOHibernate.java 24 2006-05-18 03:09:15Z ghillert $
 */
public class UserDaoHibernate extends GenericDaoHibernate< User, Long>
implements UserDao {

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

		final User user;

		final Query query = sf.getCurrentSession().createQuery(
				"select user from User user " +
				"left join fetch user.userToRoles utr " +
				"left join fetch utr.role "
				+ "where user.username= :username ");
		query.setString("username", username);

		user = (User) query.uniqueResult();

		return user;
	}

	/**
	 * Return all users from persistence store.
	 * @return List of users
	 */
	@SuppressWarnings("unchecked")
	public List < User > getAllUsers() {

		List < User >  users = sf.getCurrentSession().createQuery(
		"from User user order by user.username ASC").list();

		return users;
	}

	/**
	 * Delete an array of users from persistence store.
	 *
	 * @param usernameList list of user names.
	 */
	@SuppressWarnings("unchecked")
	public void deleteUser(final String[] usernameList) {

		List<User> list = (List<User>) sf.getCurrentSession().createCriteria(User.class).add(
				Expression.in("username", usernameList)).list();

		for (int i = 0; i < list.size(); i++) {
			User user = (User) list.get(i);

			this.remove(user.getId());
		}
	}
}
