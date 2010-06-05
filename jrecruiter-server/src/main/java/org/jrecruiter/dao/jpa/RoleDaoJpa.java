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
package org.jrecruiter.dao.jpa;

import javax.persistence.NoResultException;

import org.jrecruiter.dao.RoleDao;
import org.jrecruiter.model.Role;
import org.springframework.stereotype.Repository;


/**
 *
 * @author Gunnar Hillert
 * @version @version $Id$
 */
@Repository("roleDao")
public final class RoleDaoJpa extends GenericDaoJpa< Role, Long>
implements RoleDao {

    /**
     * Constructor.
     *
     */
    private RoleDaoJpa() {
        super(Role.class);
    }

    /* (non-Javadoc)
     * @see org.jrecruiter.dao.RoleDao#getRole(java.lang.String)
     */
    public Role getRole(final String roleName) {

        final Role role;

        javax.persistence.Query query = entityManager
        .createQuery("select r from Role r "
                + "where r.name = :role");
        query.setParameter("role", roleName);

        try {
            role = (Role) query.getSingleResult();
        } catch(NoResultException e) {
            throw new IllegalStateException(
                    String.format("Role '%s' does not exist. Roles should always " +
                            "be there. Is the seed data populated?", roleName));
        }

        return role;
    }

}
