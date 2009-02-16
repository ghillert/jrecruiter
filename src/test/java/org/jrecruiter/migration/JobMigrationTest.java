/**
 *
 */
package org.jrecruiter.migration;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;

/**
 * @author Gunnar Hillert
 * @version $Id: UserDaoTest.java 293 2008-11-13 02:26:55Z ghillert $
 */
public class JobMigrationTest extends MigrationV1toV2Base {
    
	/**
     * Initialize Logging.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(JobMigrationTest.class);

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
