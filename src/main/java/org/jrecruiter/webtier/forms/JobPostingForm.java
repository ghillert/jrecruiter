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
package org.jrecruiter.webtier.forms;

import org.apache.struts.validator.ValidatorForm;

/**
 * Struts Action Form that holds a job posting
 *
 * @author Gunnar Hillert
 * @version $Revision$, $Date$, $Author$
 */
public class JobPostingForm extends ValidatorForm {

    /**
     * Comment for <code>serialVersionUID.</code>
     */
    private static final long serialVersionUID = 1L;

    /**
     * Job Posting Id.
     */
    private String id = "";

    /**
     * Job Title of the job posting.
     */
    private String jobTitle = "";

    /**
     * Location of the job posting.
     */
    private String location = "";

    /**
     * Industry of the job posting.
     */
    private String industry = "";

    /**
     * Salary of the job posting.
     */
    private String salary = "0.00";

    /**
     * Phone number of the job posting.
     */
    private String phone = "";

    /**
     * Email address of the job posting.
     */
    private String email = "";

    /**
     * Business name of the job posting.
     */
    private String businessName = "";

    /**
     * Address field 1 of the job posting.
     */
    private String address1;

    /**
     * Address field 2 of the job posting.
     */
    private String address2 = "";

    /**
     * city of the job posting.
     */
    private String city = "";

    /**
     * state of the job posting.
     */
    private String state = "";

    /**
     * zip code of the job posting.
     */
    private String zip = "";

    /**
     * website of the job posting.
     */
    private String website = "";

    /**
     * Job description of the job posting.
     */
    private String jobDescription = "";

    /**
     * Job restriction of the job posting.
     */
    private String jobRestrictions = "";

    private String[] deleteIds;

    /**
     * Constructor.
     */
    public JobPostingForm() {
        super();
    }

    /**
     * @return Returns the id.
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            The id to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return Returns the address1.
     */
    public final String getAddress1() {
        return address1;
    }

    /**
     * @param address1
     *            The address1 to set.
     */
    public final void setAddress1(final String address1) {
        this.address1 = address1;
    }

    /**
     * @return Returns the address2.
     */
    public final String getAddress2() {
        return address2;
    }

    /**
     * @param address2
     *            The address2 to set.
     */
    public final void setAddress2(final String address2) {
        this.address2 = address2;
    }

    /**
     * @return Returns the businessName.
     */
    public final String getBusinessName() {
        return businessName;
    }

    /**
     * @param businessName
     *            The businessName to set.
     */
    public final void setBusinessName(final String businessName) {
        this.businessName = businessName;
    }

    /**
     * @return Returns the city.
     */
    public final String getCity() {
        return city;
    }

    /**
     * @param city
     *            The city to set.
     */
    public final void setCity(final String city) {
        this.city = city;
    }

    /**
     * @return Returns the email.
     */
    public final String getEmail() {
        return email;
    }

    /**
     * @param email
     *            The email to set.
     */
    public final void setEmail(final String email) {
        this.email = email;
    }

    /**
     * @return Returns the industry.
     */
    public final String getIndustry() {
        return industry;
    }

    /**
     * @param industry
     *            The industry to set.
     */
    public final void setIndustry(final String industry) {
        this.industry = industry;
    }

    /**
     * @return Returns the jobDescription.
     */
    public final String getJobDescription() {
        return jobDescription;
    }

    /**
     * @param jobDescription
     *            The jobDescription to set.
     */
    public final void setJobDescription(final String jobDescription) {
        this.jobDescription = jobDescription;
    }

    /**
     * @return Returns the jobRestrictions.
     */
    public final String getJobRestrictions() {
        return jobRestrictions;
    }

    /**
     * @param jobRestrictions
     *            The jobRestrictions to set.
     */
    public final void setJobRestrictions(final String jobRestrictions) {
        this.jobRestrictions = jobRestrictions;
    }

    /**
     * @return Returns the jobTitle.
     */
    public final String getJobTitle() {
        return jobTitle;
    }

    /**
     * @param jobTitle
     *            The jobTitle to set.
     */
    public final void setJobTitle(final String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * @return Returns the location.
     */
    public final String getLocation() {
        return location;
    }

    /**
     * @param location
     *            The location to set.
     */
    public final void setLocation(final String location) {
        this.location = location;
    }

    /**
     * @return Returns the phone.
     */
    public final String getPhone() {
        return phone;
    }

    /**
     * @param phone
     *            The phone to set.
     */
    public final void setPhone(final String phone) {
        this.phone = phone;
    }

    /**
     * @return Returns the salary.
     */
    public final String getSalary() {
        return salary;
    }

    /**
     * @param salary
     *            The salary to set.
     */
    public final void setSalary(final String salary) {
        this.salary = salary;
    }

    /**
     * @return Returns the state.
     */
    public final String getState() {
        return state;
    }

    /**
     * @param state
     *            The state to set.
     */
    public final void setState(final String state) {
        this.state = state;
    }

    /**
     * @return Returns the website.
     */
    public final String getWebsite() {
        return website;
    }

    /**
     * @param website
     *            The website to set.
     */
    public final void setWebsite(final String website) {
        this.website = website;
    }

    /**
     * @return Returns the zip.
     */
    public final String getZip() {
        return zip;
    }

    /**
     * @param zip
     *            The zip to set.
     */
    public final void setZip(final String zip) {
        this.zip = zip;
    }
    /**
     * @return Returns the deleteIds.
     */
    public String[] getDeleteIds() {
        return deleteIds;
    }
    /**
     * @param deleteIds The deleteIds to set.
     */
    public void setDeleteIds(String[] deleteIds) {
        this.deleteIds = deleteIds;
    }
}
