/**
 *
 */
package org.jrecruiter.migration;

import org.junit.Test;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
public class UserMigrationTest extends MigrationV1toV2Base {

    /**
     *
     */
    @Test
    public void migrateUsers() {
        migrationService.migrateUserData(Boolean.FALSE);
    }

}
