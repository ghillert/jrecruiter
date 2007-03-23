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
package org.jrecruiter.web.controller.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jrecruiter.model.Job;
import org.jrecruiter.service.JobService;
import org.jrecruiter.web.WebUtil;
import org.jrecruiter.web.controller.BaseSimpleFormController;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * List all the jobs. 
 * 
 * @author Gunnar Hillert
 * @version $Id: JobListAction.java 58 2006-10-16 03:45:45Z ghillert $
 *
 */
public class EditJobFormController extends BaseSimpleFormController  {
	
	/**
	 * Logger Declaration.
	 */
    private final Log LOGGER = LogFactory.getLog(EditJobFormController.class);
    
    /**
     * The service layer reference.
     */
    private JobService service;
    
    /**
     * Inject the service layer reference.
     * @param service 
     */
    public void setService(JobService service) {
		this.service = service;
	}
    
    /**
     * 
     */
    public ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command,
            BindException errors)
	throws Exception {
		LOGGER.debug("entering 'onSubmit' method...");

		Job job = (Job) command;
		final Job jobFromDB = service.getJobForId(job.getId());
		
        if (request.getParameter("delete") != null) {

        	//TODO user objects
        	//FIXME Security improvements needed
        	 service.deleteJobForId(jobFromDB.getId());
//             List ids = (List) dynaForm.get("job");
//             if(ids != null){
//                 for (int i = 0; i < ids.size(); i++) {
//                     LazyDynaBean aLong = (LazyDynaBean) ids.get(i);
//                     String delete = (String) aLong.get("delete");
//                     if (StringUtils.isNumeric(delete)) {
//                         service.deleteJobForId(Long.valueOf(delete));
//                     }
//                 }
//             }
             
             request.getSession().setAttribute("message",
                     getText("job.delete.success", ""));

        } else {
            if (job != null) {

                String jobDescription = (String) job.getDescription();

                jobFromDB.setBusinessName(job.getBusinessName());
                jobFromDB.setBusinessLocation(job.getBusinessLocation());
                jobFromDB.setJobTitle(job.getJobTitle());
                jobFromDB.setSalary(job.getSalary());
                jobFromDB.setDescription(WebUtil.stripTags(jobDescription, "<b>"));
                jobFromDB.setWebsite(job.getWebsite());
                jobFromDB.setBusinessAddress1(job.getBusinessAddress1());
                jobFromDB.setBusinessAddress2(job.getBusinessAddress2());
                jobFromDB.setBusinessCity(job.getBusinessCity());
                jobFromDB.setBusinessState(job.getBusinessState());
                jobFromDB.setBusinessZip(job.getBusinessZip());
                jobFromDB.setBusinessPhone(job.getBusinessPhone());
                jobFromDB.setBusinessEmail(job.getBusinessEmail());
                jobFromDB.setIndustry(job.getIndustry());
                jobFromDB.setJobRestrictions(job.getJobRestrictions());
                jobFromDB.setUpdateDate(new Date());
                service.updateJob(jobFromDB);
            } else {
                throw new IllegalArgumentException("Job with id " + job.getId() + " does not exist.");
            }

            request.getSession().setAttribute("message",
                    getText("job.edit.success", ""));

        }

        return new ModelAndView(getSuccessView());

	}

    
    
    /* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
        
		//FIXME security
		final Long id = Long.valueOf(request.getParameter("id"));
        final Job job = service.getJobForId(id);

        request.setAttribute("statistics", job.getStatistics());

		return job;
	}


}
