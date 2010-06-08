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
package org.jrecruiter.web.ajax;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.Statistic;
import org.jrecruiter.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author Gunnar Hillert
 * @version $Id$
 */
public class DwrAction extends BaseDwrAction {

    /**
     *   Initialize Logging.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(DwrAction.class);

    private @Autowired JobService service;

    /* (non-Javadoc)
     * @see org.ajug.service.JobServiceInterface#getJobs()
     */
    @SuppressWarnings("unchecked")
    public String getJob(Long jobId) throws Exception {

        WebContext context = WebContextFactory.get();
        HttpServletRequest request = context.getHttpServletRequest();

        String ret = "";

        final Job job = service.getJobForId(jobId);

        if (job==null){

            LOGGER.warn("Requested jobposting with id " + jobId + " was not found.");

            //TODO FIX forward
            ret = context.forwardToString(TEMPLATE_DIRECTORY + "/jobDetail.jsp");

        } else {

             Statistic statistics = job.getStatistic();

             if (statistics == null) {

                 statistics = new Statistic();
                 statistics.setJob(job);
                 statistics.setCounter(Long.valueOf(0));
                 job.setStatistic(statistics);
             }

             Set<Long> viewedPostings = new HashSet<Long>();

             if (request.getSession().getAttribute("visited") != null){

                 viewedPostings = (Set<Long>)request.getSession().getAttribute("visited");

                 if (viewedPostings.contains(jobId)) {
                     long counter = statistics.getCounter().longValue() + 1 ;
                     statistics.setCounter(Long.valueOf(counter));
                     viewedPostings.add(jobId);
                 }

             } else {

                 long counter;

                 if (statistics.getCounter() != null) {
                     counter = statistics.getCounter().longValue() + 1 ;
                 } else {
                     counter = 1;
                 }


                 statistics.setCounter(Long.valueOf(counter));

                 viewedPostings.add(jobId);
                 request.getSession().setAttribute("visited", viewedPostings);

             }

             statistics.setLastAccess(new Date());
             //service.updateJobStatistic(statistics);

             request.setAttribute("jobDetail", job);
             ret = context.forwardToString(TEMPLATE_DIRECTORY + "/jobDetail.jsp");

            }
        return ret;


    }

}
