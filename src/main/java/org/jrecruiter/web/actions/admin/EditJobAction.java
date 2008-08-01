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
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jrecruiter.common.Constants.OfferedBy;
import org.jrecruiter.model.Industry;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.Region;
import org.jrecruiter.model.Statistic;
import org.jrecruiter.web.WebUtil;
import org.jrecruiter.web.actions.BaseAction;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * Edit a job. Save the changes or delete the job posting altogether.
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
public class EditJobAction extends BaseAction implements Preparable, ModelDriven<Job> {

    /** serialVersionUID. */
    private static final long serialVersionUID = 2621352739536377825L;

    private Job job = new Job();

    private Set<OfferedBy>offeredBySet;
    private List<Region> regions;
    private List<Industry>industries;
    private Map<Boolean, String>yesNoList;

    /** Prepare the select boxes of the form. */
    public void prepare() throws Exception {

        if (this.id != null) {

            Job jobFromDb = jobService.getJobForId(this.id);

            if (jobFromDb != null) {
                this.job = jobFromDb;
            } else {
                throw new IllegalStateException("No job found for id " + this.id);
            }

             this.statistic = job.getStatistic();
        }

        this.offeredBySet = EnumSet.allOf(OfferedBy.class);
        this.regions = jobService.getRegions();
        this.industries = jobService.getIndustries();
        this.yesNoList = new HashMap<Boolean, String>();
        this.yesNoList.put(Boolean.FALSE, "False");
        this.yesNoList.put(Boolean.TRUE, "True");

    }

    public Job getModel() {
        return this.job;
    }
    public void setModel(Job job) {
        this.job = job;
    }

    private Statistic statistic;

    public Long id;

    /**
     * Logger Declaration.
     */
    private final Log LOGGER = LogFactory.getLog(EditJobAction.class);

    public String delete() {
         jobService.deleteJobForId(this.job.getId()); //FIXME SECURITY
         super.addActionMessage(getText("job.delete.success"));
         return SUCCESS;
    }

    public String execute() {
        return INPUT;
    }

    /**
     *
     */
    public String save() {

        final Job jobFromDB = jobService.getJobForId(job.getId());

        if (job != null) {

            String jobDescription = (String) job.getDescription();

            jobFromDB.setBusinessName(job.getBusinessName());
            jobFromDB.setRegionOther(job.getRegionOther());
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
            jobService.updateJob(jobFromDB);
        } else {
            throw new IllegalArgumentException("Job with id " + job.getId() + " does not exist.");
        }

        super.addActionMessage(getText("job.edit.success", ""));

        return SUCCESS;

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

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Set<OfferedBy> getOfferedBySet() {
        return offeredBySet;
    }

    public void setOfferedBySet(Set<OfferedBy> offeredBySet) {
        this.offeredBySet = offeredBySet;
    }

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }

    public List<Industry> getIndustries() {
        return industries;
    }

    public void setIndustries(List<Industry> industries) {
        this.industries = industries;
    }

    public Map<Boolean, String> getYesNoList() {
        return yesNoList;
    }

    public void setYesNoList(Map<Boolean, String> yesNoList) {
        this.yesNoList = yesNoList;
    }


}
