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

import org.apache.struts2.convention.annotation.Result;
import org.jrecruiter.model.User;
import org.jrecruiter.service.DemoService;
import org.jrecruiter.web.actions.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Gunnar Hillert
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */

@Result(name="success", location="index", type="redirectAction")
public class SetupDemoAction extends BaseAction {

    /** serialVersionUID. */
    private static final long serialVersionUID = -3422780336408883930L;

    private transient @Autowired DemoService demoService;

    public String execute() {

        User user = demoService.createDemoUser();
        demoService.createDemoJobs(user, 300);

        addActionMessage("300 demo jobs have been created.");
        return SUCCESS;
    }

}
