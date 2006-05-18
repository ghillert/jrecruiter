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


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.Statistics;
import org.jrecruiter.service.JobServiceInterface;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Jerzy Puchala
 * @version $Id$
 */
public class JobDetailAction extends Action {

    public static final Logger LOGGER = Logger.getLogger(JobPostingAction.class);

    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        ApplicationContext context = WebApplicationContextUtils.
        getRequiredWebApplicationContext(servlet.getServletContext());

        JobServiceInterface service = (JobServiceInterface) context.
        getBean("jobService");

        Long id = Long.valueOf(request.getParameter("id"));
        Job jobDetail = service.getJobForId(id);

        if (jobDetail==null){

            ActionMessages errors = new ActionMessages();
            errors.add("notfound", new ActionMessage("error.jobposting.not.found", id.toString()));
            saveErrors(request.getSession(), errors);

            LOGGER.warn("Requested jobposting with id " + id + " was not found.");

            return mapping.findForward("jobList");

        }

        Statistics statistics = jobDetail.getStatistics();

        if (statistics == null) {

            statistics = new Statistics();
            statistics.setJob(jobDetail);
            statistics.setCounter(new Long(0));
            statistics.setUniqueVisits(new Long(0));
            jobDetail.setStatistics(statistics);
        }

        Set viewedPostings = new HashSet<Long>();

        if (request.getSession().getAttribute("visited") != null){

            viewedPostings = (Set)request.getSession().getAttribute("visited");

            if (viewedPostings.contains(id)){


            } else {
                long counter = statistics.getUniqueVisits().longValue() + 1 ;
                statistics.setUniqueVisits(new Long (counter));
                viewedPostings.add(id);
            }

        } else {

            long counter;

            if (statistics.getUniqueVisits() != null)
            {
                counter = statistics.getUniqueVisits().longValue() + 1 ;
            } else {
                counter = 1;
            }


            statistics.setUniqueVisits(new Long (counter));

            viewedPostings.add(id);
            request.getSession().setAttribute("visited", viewedPostings);

        }

        Long counter = statistics.getCounter().longValue();
        counter ++;

        statistics.setCounter(new Long(counter));
        statistics.setLastAccess(new Date());
        service.updateJob(jobDetail);

        request.setAttribute("jobDetail", jobDetail);

        return mapping.findForward("success");
    }

}
