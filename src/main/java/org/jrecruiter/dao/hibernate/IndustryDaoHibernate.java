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
import org.jrecruiter.dao.IndustryDao;
import org.jrecruiter.model.Industry;

/**
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
public class IndustryDaoHibernate extends GenericDaoHibernate< Industry, Long>
                                  implements IndustryDao {

    /**
     * Constructor.
     *
     */
    private IndustryDaoHibernate() {
        super(Industry.class);
    }

    @SuppressWarnings("unchecked")
    public List<Industry> getAllIndustriesOrdered() {

        final List<Industry> industries;

        final Query query = sf.getCurrentSession().createQuery(
                "select ind from Industry ind " +
                "order by name ASC");

        industries = query.list();

        return industries;
    }


}
