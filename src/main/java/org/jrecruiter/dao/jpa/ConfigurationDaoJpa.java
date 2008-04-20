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

import org.jrecruiter.dao.ConfigurationDao;
import org.jrecruiter.model.Configuration;


/**
 *
 * @author Gunnar Hillert
 * @version @version $Id: ConfigurationDaoHibernate.java 131 2007-08-07 03:37:02Z ghillert $
 */
public class ConfigurationDaoJpa
						extends GenericDaoJpa< Configuration, String>
						implements ConfigurationDao {

    /**
     * Constructor.
     *
     */
    private ConfigurationDaoJpa() {
        super(Configuration.class);
    }

}
