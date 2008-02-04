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

import org.hibernate.Query;
import org.jrecruiter.dao.RegionDao;
import org.jrecruiter.model.Region;


/**
 *
 * @author Gunnar Hillert
 * @version $Id: UserRoleDAOHibernate.java 61 2006-11-17 04:40:39Z ghillert $
 */
public class RegionDaoHibernate extends GenericDaoHibernate< Region, Long>
                                  implements RegionDao {

    /**
     * Constructor.
     *
     */
    private RegionDaoHibernate() {
        super(Region.class);
    }

    @SuppressWarnings("unchecked")
    public List<Region> getAllRegionsOrdered() {

        final List<Region> regions;

        final Query query = sf.getCurrentSession().createQuery(
                "select reg from Region reg " +
                "order by name ASC");

        regions = query.list();

        return regions;
    }
}
