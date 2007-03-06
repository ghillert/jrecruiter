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


import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.Statistics;
import org.jrecruiter.service.JobService;


/**
 * @author Gunnar Hillert
 * @version $Id: JobServiceImpl.java 78 2007-01-04 03:07:40Z ghillert $
 */
public class DwrAction extends BaseDwrAction {

    /**
     *   Initialize Logging.
     */
    private static final Logger LOGGER = Logger.getLogger(DwrAction.class);

    private JobService service;

    public void setService(JobService service) {
		this.service = service;
	}

	/* (non-Javadoc)
     * @see org.ajug.service.JobServiceInterface#getJobs()
     */
    public String getJob(Long jobId) throws Exception {
    	
    	WebContext context = WebContextFactory.get();
    	HttpServletRequest request = context.getHttpServletRequest();
    	
    	String ret = "";
    	
    	final Job job = service.getJobForId(jobId);
    	
        if (job==null){

            ActionMessages errors = new ActionMessages();
            errors.add("notfound", new ActionMessage("error.jobposting.not.found", jobId.toString()));
            
            request.setAttribute("errors", errors);

            LOGGER.warn("Requested jobposting with id " + jobId + " was not found.");

            //TODO FIX forward
            ret = context.forwardToString(TEMPLATE_DIRECTORY + "/jobDetail.jsp");

        } else {
        	
        	 Statistics statistics = job.getStatistics();

             if (statistics == null) {

                 statistics = new Statistics();
                 statistics.setJob(job);
                 statistics.setCounter(new Long(0));
                 statistics.setUniqueVisits(new Long(0));
                 job.setStatistics(statistics);
             }

             Set viewedPostings = new HashSet<Long>();

             if (request.getSession().getAttribute("visited") != null){

                 viewedPostings = (Set)request.getSession().getAttribute("visited");

                 if (viewedPostings.contains(jobId)){


                 } else {
                     long counter = statistics.getUniqueVisits().longValue() + 1 ;
                     statistics.setUniqueVisits(new Long (counter));
                     viewedPostings.add(jobId);
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

                 viewedPostings.add(jobId);
                 request.getSession().setAttribute("visited", viewedPostings);

             }

             Long counter = statistics.getCounter().longValue();
             counter ++;

             statistics.setCounter(new Long(counter));
             statistics.setLastAccess(new Date());
             service.updateJob(job);
         
             
             request.setAttribute("jobDetail", job);
         	
         		ret = context.forwardToString(TEMPLATE_DIRECTORY + "/jobDetail.jsp");

            }
        return ret;
        
        
    }

}
