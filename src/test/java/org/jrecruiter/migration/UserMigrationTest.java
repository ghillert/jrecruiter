/**
 *
 */
package org.jrecruiter.migration;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
public class UserMigrationTest extends MigrationV1toV2Base {

	/**
     * Initialize Logging.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(UserMigrationTest.class);

    /**
     *
     */
    @Test
    public void migrateUsers() {
    	migrationService.migrateUserData(Boolean.FALSE);
    }

}
