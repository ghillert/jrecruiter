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
package org.jrecruiter.model;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
* This class represents a job posting.
*
* @author Jerzy Pucala, Gunnar Hillert
* @version $Revision: 1.3 $, $Date: 2006/03/01 05:19:15 $, $Author: ghillert $
*/
public class Job implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 6520479176825887240L;

    /**
     * Job posting id.
     */
    private Long id;

    /**
     * Business name.
     */
    private String businessName;

    /**
     * Location of the business.
     */
    private String businessLocation;

    /**
     * Job title.
     */
    private String jobTitle;

    /**
     * Salary.
     */
    private Double salary;

    /**
     * Description of the job posting.
     */
    private String description;

    /**
     * Website of the company.
     */
    private String website;

    /**
     * Address field 1.
     */
    private String businessAddress1;

    /**
     * Address field 2.
     */
    private String businessAddress2;

    /**
     * City.
     */
    private String businessCity;

    /**
     * State.
     */
    private String businessState;

    /**
     * Postal Code.
     */
    private String businessZip;

    /**
     * Business Phone.
     */
    private String businessPhone;

    /**
     * Business Email.
     */
    private String businessEmail;

    /**
     * Industry.
     */
    private String industry;

    /**
     * Restrictions for the advertised job.
     */
    private String jobRestrictions;

    /**
     * Job posting creation date.
     */
    private Date registerDate;

    /**
     * Date job is expiring.
     */
    private Date expireDate;

    /**
     * Date the job posting was updated.
     */
    private Date updateDate;

    /**
     * Status of job posting.
     */
    private int status;

    /**
     * Username (owner of job posting)
     */
    private String username;

    /**
     * Username (owner of job posting)
     */
    private User owner;

    /**
     * Statistics of this particuliar job posting.
     */
    private Statistics statistics;

    private User user;

    /**
     * Delete Flag.
     */
    private Long delete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(final String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessLocation() {
        return businessLocation;
    }

    public void setBusinessLocation(final String businessLocation) {
        this.businessLocation = businessLocation;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(final String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(final double salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(final String website) {
        this.website = website;
    }

    public String getBusinessAddress1() {
        return businessAddress1;
    }

    public void setBusinessAddress1(final String businessAddress1) {
        this.businessAddress1 = businessAddress1;
    }

    public String getBusinessAddress2() {
        return businessAddress2;
    }

    public void setBusinessAddress2(final String businessAddress2) {
        this.businessAddress2 = businessAddress2;
    }

    public String getBusinessCity() {
        return businessCity;
    }

    public void setBusinessCity(final String businessCity) {
        this.businessCity = businessCity;
    }

    public String getBusinessState() {
        return businessState;
    }

    public void setBusinessState(final String businessState) {
        this.businessState = businessState;
    }

    public String getBusinessZip() {
        return businessZip;
    }

    public void setBusinessZip(final String businessZip) {
        this.businessZip = businessZip;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(final String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessEmail(final String businessEmail) {
        this.businessEmail = businessEmail;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(final String industry) {
        this.industry = industry;
    }

    public String getJobRestrictions() {
        return jobRestrictions;
    }

    public void setJobRestrictions(final String jobRestrictions) {
        this.jobRestrictions = jobRestrictions;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(final Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(final Date expireDate) {
        this.expireDate = expireDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(final Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(final int status) {
        this.status = status;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(final User owner) {
        this.owner = owner;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public Long getDelete() {
        return delete;
    }

    public void setDelete(final Long delete) {
        this.delete = delete;
    }

    /**
     * @return Returns the statistics.
     */
    public Statistics getStatistics() {
        return statistics;
    }

    /**
     * @param statistics The statistics to set.
     */
    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    /**
     * @return Returns the user.
     */
    public final User getUser() {
        return user;
    }

    /**
     * @param user The user to set.
     */
    public final void setUser(User user) {
        this.user = user;
    }


    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof Job))
            return false;
        Job castOther = (Job) other;
        return new EqualsBuilder().append(businessName, castOther.businessName)
                .append(jobTitle, castOther.jobTitle).append(salary,
                        castOther.salary).append(description,
                        castOther.description).append(website,
                        castOther.website).append(businessAddress1,
                        castOther.businessAddress1).append(businessAddress2,
                        castOther.businessAddress2).append(businessCity,
                        castOther.businessCity).append(businessState,
                        castOther.businessState).append(businessZip,
                        castOther.businessZip).append(businessPhone,
                        castOther.businessPhone).append(businessEmail,
                        castOther.businessEmail).append(jobRestrictions,
                        castOther.jobRestrictions).append(registerDate,
                        castOther.registerDate).append(updateDate,
                        castOther.updateDate).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(-136820741, -2007964787)
                .append(businessName).append(jobTitle).append(salary).append(
                        description).append(website).append(businessAddress1)
                .append(businessAddress2).append(businessCity).append(
                        businessState).append(businessZip)
                .append(businessPhone).append(businessEmail).append(
                        jobRestrictions).append(registerDate)
                .append(updateDate).toHashCode();
    }



}

