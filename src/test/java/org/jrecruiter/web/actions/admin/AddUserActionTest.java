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
package org.jrecruiter.web.actions.admin;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.easymock.EasyMock;
import org.jrecruiter.Constants.JobStatus;
import org.jrecruiter.dao.JobDao;
import org.jrecruiter.model.User;
import org.jrecruiter.service.UserService;
import org.jrecruiter.web.actions.BaseActionTest;

/**
 * Test the Struts 2 Add User Action
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
public class AddUserActionTest extends BaseActionTest {

    public void testExecute() throws Exception {
        AddUserAction addUserAction = new AddUserAction();

        String ret = addUserAction.execute();

        Assert.assertEquals("input", ret);
    }

    public void testSave() throws Exception {
        AddUserAction addUserAction = new AddUserAction();

        User user = new User();
        user.setUsername("abc");
        addUserAction.setUser(user);

        UserService userService = EasyMock.createMock(UserService.class);

        userService.addUser(user);
        EasyMock.replay(userService);

        addUserAction.setUserService(userService);

        String ret = addUserAction.save();

        Assert.assertEquals("success", ret);
    }
}

