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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.jrecruiter.model.Job;
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
    public String getJob(Long jobId) {
    	
    	WebContext context = WebContextFactory.get();
    	HttpServletRequest request = context.getHttpServletRequest();
    	
    	Job job = service.getJobForId(jobId);
    	request.setAttribute("jobDetail", job);
    	String ret = "";
    	try {
    		ret = context.forwardToString(TEMPLATE_DIRECTORY + "/jobDetail.jsp");
    	} catch (ServletException e) {
    		LOGGER.error(e.getMessage(), e);
    	} catch (IOException e) {
    		LOGGER.error(e.getMessage(), e);
    	}
        return ret;
        
    }

}
