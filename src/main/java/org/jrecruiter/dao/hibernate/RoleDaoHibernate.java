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

import org.hibernate.Query;
import org.jrecruiter.dao.RoleDao;
import org.jrecruiter.model.Role;


/**
 *
 * @author Gunnar Hillert
 * @version @version $Id$
 */
public class RoleDaoHibernate extends GenericDaoHibernate< Role, Long>
implements RoleDao {

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

		Query query = sf.getCurrentSession()
		.createQuery("select r from Role r "
				+ "where r.name = :role");
		query.setString("role", roleName);

		role = (Role) query.uniqueResult();

		return role;
	}

}
