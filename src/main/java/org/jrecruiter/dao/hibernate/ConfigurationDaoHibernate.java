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

import org.jrecruiter.dao.ConfigurationDao;
import org.jrecruiter.model.Configuration;


/**
 *
 * @author Gunnar Hillert
 * @version @version $Id$
 */
public class ConfigurationDaoHibernate
						extends GenericDaoHibernate< Configuration, String>
						implements ConfigurationDao {

    /**
     * Constructor.
     *
     */
    private ConfigurationDaoHibernate() {
        super(Configuration.class);
    }

}
