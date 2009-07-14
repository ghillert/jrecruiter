/**
 *
 */
package org.jrecruiter.migration;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
public class JobMigrationTest extends MigrationV1toV2Base {

    /**
     *
     *
     */
    @Test
    @Rollback(false)
    public void migrateJobs() {

        migrationService.migrateUserData(Boolean.FALSE);
        super.entityManager.flush();
        migrationService.migrateJobData();

    }

}
