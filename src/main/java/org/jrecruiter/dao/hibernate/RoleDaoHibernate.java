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

import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jrecruiter.Constants.Roles;
import org.jrecruiter.Constants.StatsMode;
import org.jrecruiter.dao.RoleDao;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.Role;
import org.jrecruiter.model.User;


/**
 *
 * @author Gunnar Hillert
 * @version @version $Id: SettingsDAOHibernate.java 24 2006-05-18 03:09:15Z ghillert $
 */
public class RoleDaoHibernate extends GenericDaoHibernate< Role, Long>
							  implements RoleDao {

    /**
     * Role Dao.
     */
    private RoleDao roleDao;

    /**
	 * @param roleDao the roleDao to set
	 */
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	/**
     * Constructor.
     *
     */
    private RoleDaoHibernate() {
    	super(Role.class);
    }

	/* (non-Javadoc)
	 * @see org.jrecruiter.dao.RoleDao#getRole(java.lang.String)
	 */
	public Role getRole(final String roleName) {

		final Role role;

		final Session session = getSession(false);

		try {
			Query query = session
					.createQuery("select r from Role r "
							+ "where r.name = :role");
			query.setString("role", roleName);

			role = (Role) query.uniqueResult();

		} catch (HibernateException ex) {
			throw convertHibernateAccessException(ex);
		}

		return role;
	}

}
