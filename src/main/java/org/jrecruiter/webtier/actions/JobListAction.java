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


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.displaytag.properties.SortOrderEnum;
import org.jrecruiter.service.JobService;
import org.jrecruiter.webtier.JobsForDisplaytagSorting;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Jerzy Puchala
 * @version $Id$
 */
public class JobListAction extends Action {

    /**
     *   Initialize Logging.
     */
    public static final Logger LOGGER = Logger.getLogger(JobListAction.class);

    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        ApplicationContext context = WebApplicationContextUtils.
        getRequiredWebApplicationContext(servlet.getServletContext());

        JobService service = (JobService) context.
        getBean("jobService");

        JobsForDisplaytagSorting jobsDisplaytag = new JobsForDisplaytagSorting();

        String sortDirection = null;
        String sortField = null;
        Integer page = 1;

        if (request.getParameter("sort") != null) {
            sortField = request.getParameter("sort");
            jobsDisplaytag.setSortCriterion(sortField);
        }
        if (request.getParameter("dir") != null) {
            sortDirection = request.getParameter("dir");

            if (sortDirection.equalsIgnoreCase("ASC")) {
                jobsDisplaytag.setSortOrder(SortOrderEnum.ASCENDING);
            } else if (sortDirection.equalsIgnoreCase("DESC")) {
                jobsDisplaytag.setSortOrder(SortOrderEnum.DESCENDING);
            }

        }
        if (request.getParameter("page") != null) {
            page = new Integer(request.getParameter("page"));
            jobsDisplaytag.setPageNumber(page);
        }

        request.getParameterNames();
        jobsDisplaytag.setFullListSize(service.getJobsCount());

        LOGGER.info("Retrieving all jobs - "
                        + ";Total Size: " + jobsDisplaytag.getFullListSize()
                        + ";Results per Page: " + jobsDisplaytag.getObjectsPerPage()
                        + ";Page: " + jobsDisplaytag.getPageNumber()
                        + ";Sort Field: " + jobsDisplaytag.getSortCriterion()
                        + ";Direction: " + sortDirection);

        List jobs = service.getJobs(jobsDisplaytag.getObjectsPerPage(),
                                    jobsDisplaytag.getPageNumber(),
                                    jobsDisplaytag.getSortCriterion(),
                                    sortDirection);
        jobsDisplaytag.setJobs(jobs);

        request.setAttribute("JobList", jobsDisplaytag);

        String ajaxCall = request.getParameter("displayAjax");
        request.getParameterNames();
        if (ajaxCall != null && ajaxCall.equalsIgnoreCase("true")) {
            return mapping.findForward("ajax");
        }

        return mapping.findForward("success");
    }

}
