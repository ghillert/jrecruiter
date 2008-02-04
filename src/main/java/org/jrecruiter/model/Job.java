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
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.jrecruiter.Constants.JobStatus;
import org.jrecruiter.Constants.OfferedBy;

/**
* This class represents a job posting.
*
* @author Gunnar Hillert
* @version $Id$
*/
@Entity
@Table(uniqueConstraints = {  })
public class Job implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 8966612480290486159L;

    /** Job posting id. */
    private Long id;

    /** Industry. */
    private Industry industry;

    /** Region where the job is located. */
    private Region region;

    /** Owner of job posting) */
    private User user;

    /** Business name. */
    private String businessName;

    /** If no suitable region can be selected,
     *  allow to provide for a custom region name. */
    private String regionOther;

    /** Job title. */
    private String jobTitle;

    /** Salary. */
    private BigDecimal salary = BigDecimal.ZERO;

    /** Description of the job posting. */
    private String description;

    /** Website of the company. */
    private String website;

    /** Address field 1. */
    private String businessAddress1;

    /** Address field 2. */
    private String businessAddress2;

    /** City. */
    private String businessCity;

    /** State. */
    private String businessState;

    /** Postal Code. */
    private String businessZip;

    /** Business Phone. */
    private String businessPhone;

    /** Business Email. */
    private String businessEmail;

    /** Industry - Free Form Text Field. */
    private String industryOther;

    /** Restrictions for the advertised job. */
    private String jobRestrictions;

    /** Job posting creation date. */
    private Date registrationDate;

    /** Date the job posting was updated. */
    private Date updateDate;

    /** Status of job posting. */
    private JobStatus status;

    /** */
    private OfferedBy offeredBy;

    /** Used to map the Location of the job. */
    private BigDecimal longitude = BigDecimal.ZERO;

    /** Used to map the Location of the job.*/
    private BigDecimal latitude = BigDecimal.ZERO;

    /** Statistic of this particuliar job posting. */
    private Statistic statistic;

    Boolean usesMap;

    // Constructors
    /** default constructor */
    public Job() {
    }

    /** Constructor with just the job id. */
    public Job(Long jobId) {
        this.id = jobId;
    }

    /** minimal constructor */
    public Job(Long id, User user, String businessName, String jobTitle, String description, JobStatus status) {
        this.id = id;
        this.user = user;
        this.businessName = businessName;
        this.jobTitle = jobTitle;
        this.description = description;
        this.status = status;
    }
    /** full constructor */
    public Job(Long id, Industry industry, User user, String businessName,
               String businessLocation, String jobTitle, BigDecimal salary,
               String description, String website, String businessAddress1,
               String businessAddress2, String businessCity,
               String businessState, String businessZip, String businessPhone,
               String businessEmail, String industryOther,
               String jobRestrictions, Date registrationDate,
               Date expirationDate, Date updateDate, JobStatus status,
               OfferedBy offeredBy, BigDecimal longitude, BigDecimal latitude,
               Statistic statistic) {
       this.id = id;
       this.industry = industry;
       this.user = user;
       this.businessName = businessName;
       this.regionOther = businessLocation;
       this.jobTitle = jobTitle;
       this.salary = salary;
       this.description = description;
       this.website = website;
       this.businessAddress1 = businessAddress1;
       this.businessAddress2 = businessAddress2;
       this.businessCity = businessCity;
       this.businessState = businessState;
       this.businessZip = businessZip;
       this.businessPhone = businessPhone;
       this.businessEmail = businessEmail;
       this.industryOther = industryOther;
       this.jobRestrictions = jobRestrictions;
       this.registrationDate = registrationDate;
       this.updateDate = updateDate;
       this.status = status;
       this.offeredBy = offeredBy;
       this.longitude = longitude;
       this.latitude = latitude;
       this.statistic = statistic;
    }

    /**
     * @return the id
     */
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(unique=true, nullable=false, insertable=true, updatable=true)
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the industry
     */
    @ManyToOne(cascade={}, fetch=FetchType.LAZY)
    @JoinColumn(name="industries_id", unique=false, nullable=true, insertable=true, updatable=true)
    public Industry getIndustry() {
        return industry;
    }

    /**
     * @param industry the industry to set
     */
    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    /**
     * @return the user
     */
    @ManyToOne(cascade={}, fetch=FetchType.LAZY)
    @JoinColumn(name="regions_id", unique=false, nullable=true, insertable=true, updatable=true)
    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @ManyToOne(cascade={},
            fetch=FetchType.LAZY)
    @JoinColumn(name="users_id", unique=false, nullable=false, insertable=true, updatable=true)
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the businessName
     */
    @Column(unique=false, nullable=false, insertable=true, updatable=true, length=50)
    public String getBusinessName() {
        return businessName;
    }

    /**
     * @param businessName the businessName to set
     */
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    /**
     * @return the businessLocation
     */
    @Column(name="region_other", unique=false, nullable=true, insertable=true, updatable=true, length=50)
    public String getRegionOther() {
        return regionOther;
    }

    /**
     * @param businessLocation the businessLocation to set
     */
    public void setRegionOther(String businessLocation) {
        this.regionOther = businessLocation;
    }

    /**
     * @return the jobTitle
     */
    @Column(name="job_title", unique=false, nullable=false, insertable=true, updatable=true, length=50)
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * @param jobTitle the jobTitle to set
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    /**
     * @return the salary
     */
    @Column(name="salary", unique=false, nullable=true, insertable=true, updatable=true)
    public BigDecimal getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    /**
     * @return the description
     */
    @Column(name="description", unique=false, nullable=false, insertable=true, updatable=true, length=2000)
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the website
     */
    @Column(name="website", unique=false, nullable=true, insertable=true, updatable=true, length=50)
    public String getWebsite() {
        return website;
    }

    /**
     * @param website the website to set
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * @return the businessAddress1
     */
    @Column(name="business_address1", unique=false, nullable=true, insertable=true, updatable=true, length=50)
    public String getBusinessAddress1() {
        return businessAddress1;
    }

    /**
     * @param businessAddress1 the businessAddress1 to set
     */
    public void setBusinessAddress1(String businessAddress1) {
        this.businessAddress1 = businessAddress1;
    }

    /**
     * @return the businessAddress2
     */
    @Column(name="business_address2", unique=false, nullable=true, insertable=true, updatable=true, length=50)
    public String getBusinessAddress2() {
        return businessAddress2;
    }

    /**
     * @param businessAddress2 the businessAddress2 to set
     */
    public void setBusinessAddress2(String businessAddress2) {
        this.businessAddress2 = businessAddress2;
    }

    /**
     * @return the businessCity
     */
    @Column(name="business_city", unique=false, nullable=true, insertable=true, updatable=true, length=30)
    public String getBusinessCity() {
        return businessCity;
    }

    /**
     * @param businessCity the businessCity to set
     */
    public void setBusinessCity(String businessCity) {
        this.businessCity = businessCity;
    }

    /**
     * @return the businessState
     */
    @Column(name="business_state", unique=false, nullable=true, insertable=true, updatable=true, length=20)
    public String getBusinessState() {
        return businessState;
    }

    /**
     * @param businessState the businessState to set
     */
    public void setBusinessState(String businessState) {
        this.businessState = businessState;
    }

    /**
     * @return the businessZip
     */
    @Column(name="business_zip", unique=false, nullable=true, insertable=true, updatable=true, length=15)
    public String getBusinessZip() {
        return businessZip;
    }

    /**
     * @param businessZip the businessZip to set
     */
    public void setBusinessZip(String businessZip) {
        this.businessZip = businessZip;
    }

    /**
     * @return the businessPhone
     */
    @Column(name="business_phone", unique=false, nullable=true, insertable=true, updatable=true, length=15)
    public String getBusinessPhone() {
        return businessPhone;
    }

    /**
     * @param businessPhone the businessPhone to set
     */
    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    /**
     * @return the businessEmail
     */
    @Column(name="business_email", unique=false, nullable=true, insertable=true, updatable=true, length=50)
    public String getBusinessEmail() {
        return businessEmail;
    }

    /**
     * @param businessEmail the businessEmail to set
     */
    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }

    /**
     * @return the industryOther
     */
    @Column(name="industry_other", unique=false, nullable=true, insertable=true, updatable=true, length=50)
    public String getIndustryOther() {
        return industryOther;
    }

    /**
     * @param industryOther the industryOther to set
     */
    public void setIndustryOther(String industryOther) {
        this.industryOther = industryOther;
    }

    /**
     * @return the jobRestrictions
     */
    @Column(name="job_restrictions", unique=false, nullable=true, insertable=true, updatable=true, length=2000)
    public String getJobRestrictions() {
        return jobRestrictions;
    }

    /**
     * @param jobRestrictions the jobRestrictions to set
     */
    public void setJobRestrictions(String jobRestrictions) {
        this.jobRestrictions = jobRestrictions;
    }

    /**
     * @return the registrationDate
     */
    @Column(name="registration_date", unique=false, nullable=true, insertable=true, updatable=true, length=8)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getRegistrationDate() {
        return registrationDate;
    }

    /**
     * @param registrationDate the registrationDate to set
     */
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    /**
     * @return the updateDate
     */
    @Column(name="update_date", unique=false, nullable=true, insertable=true, updatable=true, length=8)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate the updateDate to set
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return the status
     */
    @Column(name="status", unique=false, nullable=false, insertable=true, updatable=true, precision=2, scale=0)
    public JobStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(JobStatus status) {
        this.status = status;
    }

    /**
     * @return the offeredBy
     */
    @Column(name="offered_by", unique=false, nullable=true, insertable=true, updatable=true, precision=2, scale=0)
    public OfferedBy getOfferedBy() {
        return offeredBy;
    }

    /**
     * @param offeredBy the offeredBy to set
     */
    public void setOfferedBy(OfferedBy offeredBy) {
        this.offeredBy = offeredBy;
    }

    /**
     * @return the longitude
     */
    @Column(name="longitude", unique=false, nullable=true, insertable=true, updatable=true)
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Column(name="uses_map", unique=false, nullable=true, insertable=true, updatable=true)
    public Boolean getUsesMap() {
        return usesMap;
    }

    public void setUsesMap(Boolean usesMap) {
        this.usesMap = usesMap;
    }

    /**
     * @return the latitude
     */
    @Column(name="latitude", unique=false, nullable=true, insertable=true, updatable=true)
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the statistics
     */
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    public Statistic getStatistic() {
        return statistic;
    }

    /**
     * @param statistics the statistics to set
     */
    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
    }


}

