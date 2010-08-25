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
package org.jrecruiter.service;

import java.io.InputStream;

import org.jrecruiter.model.User;
import org.jrecruiter.model.export.Backup;


/**
 * Special services allowing the administrator to populate the sytem with
 * demo data.
 *
 * @author Gunnar Hillert
 *
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
public interface SystemSetupService {

    /**
     * Adds specifiable number of demo jobs to the system.
     *
     * @param user user for which the jobs are being generated.
     * @param numberOfJobsToCreate How many job shall be created.
     */
    void createDemoJobs(User user, Integer numberOfJobsToCreate);

    /**
     * Restore a set of backed-up master data.
     */
    void restore(Backup backup);

    /**
     * Restore a set of backed-up master data.
     */
    void restore(InputStream inputStream);

    /** Create the database using Hibernate's SchemaExport functionality */
    void createDatabase();

    /** Update the database using Hibernate's SchemaUpdate functionality */
    void updateDatabase();

    /** */
    void loadAndRestoreSeedData();

    /** */
	boolean isDatabaseSetup();

	Backup convertToBackupData(InputStream inputStream);
}
