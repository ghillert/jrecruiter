package org.jrecruiter.web.actions.admin;

import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.jrecruiter.Constants.JobStatus;
import org.jrecruiter.Constants.OfferedBy;
import org.jrecruiter.model.Industry;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.Region;
import org.jrecruiter.web.actions.BaseAction;
import org.texturemedia.smarturls.Result;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.UrlValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

/**
 * List all the jobs.
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
@Validation
@Result(name="success", location="index", type="redirectAction")
public class AddJobAction extends BaseAction implements Preparable, ModelDriven<Job> {

    /** serialVersionUID. */
    private static final long serialVersionUID = 4614516114027504626L;

    /**
     * Logger Declaration.
     */
    private final Log LOGGER = LogFactory.getLog(AddJobAction.class);

    private Job job = new Job();

    private Set<OfferedBy>offeredBySet;
    private List<Region> regions;
    private List<Industry>industries;
    private Map<Boolean, String>yesNoList;

    /** Prepare the select boxes of the form. */
    public void prepare() throws Exception {
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
    /**
     *
     */
    @Override
    @SkipValidation
    public String execute() {
        return INPUT;
    }

    @Validations(
            requiredFields = {
                    @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "businessEmail", message = "You must enter an Emal Address."),
                    @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "industry", message = "You must select an industry."),
                    @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "offeredBy", message = "Please select whether you are the hiring company or a recruiter."),
                    @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "region", message = "Please select a region."),
                    @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "usesMap", message = "Do you want to use Google Maps.")
                    },
            requiredStrings = {
                        @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "jobTitle", message = "You must enter a job title."),
                        @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "businessName", message = "You must provide a company name."),
                        @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "description", message = "You must enter a job description.")
                    },
            emails =
                    { @EmailValidator(type = ValidatorType.SIMPLE, fieldName = "businessEmail", message = "You must enter a valid email.")},
            urls =
                    { @UrlValidator(type = ValidatorType.SIMPLE, fieldName = "website", message = "You must enter a valid url.")},
            stringLengthFields =
                    {@StringLengthFieldValidator(type = ValidatorType.SIMPLE, trim = true, maxLength = "50", fieldName = "jobTitle", message = "The job title must be shorter than ${maxLength} characters.")}
            )
    public String save() {

        LOGGER.debug("Adding Job...");

        final Region region = jobService.getRegion(job.getRegion().getId());
        final Industry industry = jobService.getIndustry(job.getIndustry().getId());

        job.setRegion(region);
        job.setIndustry(industry);

        job.setStatus(JobStatus.ACTIVE);
        job.setUser(super.getLoggedInUser());
        job.setRegistrationDate(new Date());
        job.setUpdateDate(new Date());

        jobService.addJob(job);
       // jobService.sendJobPostingToMailingList(job);

        super.addActionMessage(getText("job.add.success"));

        return SUCCESS;
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
