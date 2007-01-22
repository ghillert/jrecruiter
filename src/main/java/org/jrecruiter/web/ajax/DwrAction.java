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


import org.apache.log4j.Logger;
import org.jrecruiter.model.Job;
import org.jrecruiter.service.JobService;

/**
 * @author Gunnar Hillert
 * @version $Id: JobServiceImpl.java 78 2007-01-04 03:07:40Z ghillert $
 */
public class DwrAction {

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
    public Job getJob(Long jobId) {
        return service.getJobForId(jobId);
    }

}
