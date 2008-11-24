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

import org.jrecruiter.web.actions.BaseAction;
import org.jrecruiter.web.interceptor.RetrieveMessages;

/**
 * Show the main index page of the admin screens.
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
public class IndexAction extends BaseAction {

    /** serialVersionUID. */
    private static final long serialVersionUID = 4614516114027504626L;

    /**
     *
     */
    @Override
    @RetrieveMessages
    public String execute() {
            super.getLoggedInUser();
            return SUCCESS;
    }
}
