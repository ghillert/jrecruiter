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

import java.util.List;

import org.jrecruiter.dao.RegionDao;
import org.jrecruiter.model.Region;


/**
 *
 * @author Gunnar Hillert
 * @version $Id: RegionDaoHibernate.java 156 2008-02-21 03:26:28Z ghillert $
 */
public class RegionDaoJpa extends GenericDaoJpa< Region, Long>
                                  implements RegionDao {

    /**
     * Constructor.
     *
     */
    private RegionDaoJpa() {
        super(Region.class);
    }

    @SuppressWarnings("unchecked")
    public List<Region> getAllRegionsOrdered() {

        final List<Region> regions;

        final javax.persistence.Query query = entityManager.createQuery(
                "select reg from Region reg " +
                "order by name ASC");

        regions = query.getResultList();

        return regions;
    }
}
