package org.jrecruiter.web.actions;

import java.util.ArrayList;
import java.util.List;

import org.jrecruiter.model.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

/**
 * List all the jobs.
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
public class SearchAction extends BaseAction  {

    /** serialVersionUID. */
    private static final long serialVersionUID = -6920008555687729003L;

    /**
     * Logger Declaration.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(SearchAction.class);

    private String keyword;
    private List<Job>jobs = new ArrayList<Job>();

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * @return the keyword
     */
    public String getKeyword() {
        return keyword;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }


    /**
     *
     */
    @Validations(
            requiredStrings = {
                        @RequiredStringValidator(type = ValidatorType.SIMPLE, fieldName = "keyword", trim=true, message = "Please enter a search term.")
                     },
            stringLengthFields = {
                        @StringLengthFieldValidator(type = ValidatorType.SIMPLE, trim = true, maxLength = "250", fieldName = "keyword",  message = "The search term must be shorter than ${maxLength} characters.")
                    }
            )
    public String search() {

        LOGGER.debug("entering 'onSubmit' method...");

        this.jobs = jobService.searchByKeyword(this.keyword.toLowerCase());

        return SUCCESS;

    }

}
