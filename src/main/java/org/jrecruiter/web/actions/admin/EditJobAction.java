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

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.Statistic;
import org.jrecruiter.service.JobService;
import org.jrecruiter.web.WebUtil;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * List all the jobs.
 *
 * @author Gunnar Hillert
 * @version $Id: EditJobFormController.java 128 2007-07-27 03:55:54Z ghillert $
 *
 */
public class EditJobAction extends ActionSupport implements Preparable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 2621352739536377825L;

	private Job job;
	private Statistic statistic;

	public Long id;

	/**
	 * Logger Declaration.
	 */
    private final Log LOGGER = LogFactory.getLog(EditJobAction.class);

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

    public String delete() {

    	//TODO user objects
    	//FIXME Security improvements needed
    	 service.deleteJobForId(this.job.getId());
//         List ids = (List) dynaForm.get("job");
//         if(ids != null){
//             for (int i = 0; i < ids.size(); i++) {
//                 LazyDynaBean aLong = (LazyDynaBean) ids.get(i);
//                 String delete = (String) aLong.get("delete");
//                 if (StringUtils.isNumeric(delete)) {
//                     service.deleteJobForId(Long.valueOf(delete));
//                 }
//             }
//         }

         super.addActionMessage(getText("job.delete.success"));
         return SUCCESS;
    }

    /**
     *
     */
    public String save() {

		final Job jobFromDB = service.getJobForId(job.getId());

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

        super.addActionMessage(getText("job.edit.success", ""));

        return SUCCESS;

	}

    public void prepare() throws Exception {
		if (id != null) {
			 this.job = service.getJobForId(id);
			 this.statistic = job.getStatistic();
		}

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Statistic getStatistic() {
		return statistic;
	}

	public void setStatistic(Statistic statistic) {
		this.statistic = statistic;
	}


}
