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

import org.jrecruiter.dao.StatisticDao;
import org.jrecruiter.model.Statistic;


/**
 *
 * @author Gunnar Hillert
 * @version @version $Id: StatisticDaoHibernate.java 136 2008-01-13 15:39:09Z ghillert $
 */
public class StatisticDaoJpa extends GenericDaoJpa< Statistic, Long>
								   implements StatisticDao {

    /**
     * Constructor.
     *
     */
    private StatisticDaoJpa() {
    	super(Statistic.class);
    }

}
