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
package org.jrecruiter.webtier.actions;


import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jrecruiter.service.JobServiceInterface;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 
 * @author Jerzy Puchala
 * @version $Revision: 1.5 $, $Date: 2006/03/24 02:27:59 $, $Author: ghillert $
 */
public class JobListAction extends Action {
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        ApplicationContext context = WebApplicationContextUtils.
        getRequiredWebApplicationContext(servlet.getServletContext());

        JobServiceInterface service = (JobServiceInterface) context.
        getBean("jobService");

        List jobs = service.getJobs();

        request.setAttribute("JobList", jobs);
        
        String ajaxCall = request.getParameter("displayAjax");
        
        if (ajaxCall != null && ajaxCall.equalsIgnoreCase("true")) {
        	return mapping.findForward("ajax");
        }
        
        return mapping.findForward("success");
    }

}
