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

import org.jrecruiter.dao.SchemaMigrationDao;
import org.jrecruiter.model.SchemaMigration;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gunnar Hillert
 * @version $Id: IndustryDaoJpa.java 291 2008-11-13 02:20:08Z ghillert $
 */
@Repository("schemaMigrationDao")
public final class SchemaMigrationDaoJpa extends GenericDaoJpa< SchemaMigration, Long>
                                  implements SchemaMigrationDao {

    /**
     * Constructor.
     */
    private SchemaMigrationDaoJpa() {
        super(SchemaMigration.class);
    }

}
