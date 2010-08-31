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

import java.util.Set;

import org.jrecruiter.model.User;
import org.jrecruiter.model.UserToRole;
import org.jrecruiter.model.export.Backup;
import org.jrecruiter.test.BaseServiceIntegrationTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

/**
 *
 * @author Gunnar Hillert
 * @version $Id: UserServiceTest.java 561 2010-03-04 15:12:02Z ghillert $
 */
public class DemoServiceTest extends BaseServiceIntegrationTest {

    @Autowired SystemSetupService demoService;

    @Test
    public void testCreateDatabase(){
        demoService.createDatabase();
    };

    @Test
    public void testUpdateDatabase(){

     //   demoService.updateDatabase();
    };

    @Test
    @Rollback(false)
    public void testLoadAndRestoreSeedData(){
        demoService.loadAndRestoreSeedData();
    };

    @Test
    @Rollback(false)
    public void testRestore(){

    	final java.io.InputStream inputStream =  DemoServiceTest.class.getResourceAsStream("/org/jrecruiter/server/seeddata/demodata.xml");

        final Backup backup = demoService.convertToBackupData(inputStream);

        for (User user : backup.getUsers()) {

        	final Set<UserToRole> userToRoles = user.getUserToRoles();
        	Assert.assertTrue("Every user should have at least one role.", !userToRoles.isEmpty());

        	for (final UserToRole userToRole : userToRoles) {
        		Assert.assertNotNull(userToRole.getRoleName());
        	}
        }

    };

}
