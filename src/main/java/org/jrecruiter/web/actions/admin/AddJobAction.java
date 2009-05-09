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

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.jrecruiter.common.Constants.JobStatus;
import org.jrecruiter.model.Industry;
import org.jrecruiter.model.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.UrlValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

/**
 * Add a job posting.
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
@Result(name="success", location="index", type="redirectAction")
public class AddJobAction extends JobBaseAction {

    /** serialVersionUID. */
    private static final long serialVersionUID = 4614516114027504626L;

    /**
     * Logger Declaration.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(AddJobAction.class);

    /**
     * Initialize the form.
     */
    @Override
    @SkipValidation
    public String execute() {
        return INPUT;
    }


    /**
     * Save the job.
     */
    @Validations(
            requiredFields = {
                    @RequiredFieldValidator(type = ValidatorType.SIMPLE, fieldName = "businessEmail", message = "You must enter an Email Address."),
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

        final Region region = jobService.getRegion(this.model.getJob().getRegion().getId());
        final Industry industry = jobService.getIndustry(this.model.getJob().getIndustry().getId());

        this.model.getJob().setRegion(region);
        this.model.getJob().setIndustry(industry);

        this.model.getJob().setStatus(JobStatus.ACTIVE);
        this.model.getJob().setUser(super.getLoggedInUser());
        this.model.getJob().setRegistrationDate(new Date());
        this.model.getJob().setUpdateDate(new Date());

        HttpServletRequest request = ServletActionContext.getRequest();
        jobService.addJobAndSendToMailingList(this.model.getJob(),
                request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/registration/account-validation.html");

        super.addActionMessage(getText("job.add.success"));

        return SUCCESS;
    }
}
