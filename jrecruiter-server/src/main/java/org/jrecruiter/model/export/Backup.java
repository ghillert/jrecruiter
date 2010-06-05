package org.jrecruiter.model.export;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.jrecruiter.common.CollectionUtils;
import org.jrecruiter.model.Industry;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.Region;
import org.jrecruiter.model.Role;
import org.jrecruiter.model.User;
import org.jrecruiter.model.statistics.JobCountPerDay;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Backup {

    @XmlElementWrapper(name = "industries")
    @XmlElement(name = "industry")
    private List<Industry> industries = CollectionUtils.getArrayList();

    @XmlElementWrapper(name = "regions")
    @XmlElement(name = "region")
    private List<Region>   regions    = CollectionUtils.getArrayList();

    @XmlElementWrapper(name = "roles")
    @XmlElement(name = "role")
    private List<Role>     roles      = CollectionUtils.getArrayList();

    @XmlElementWrapper(name = "users")
    @XmlElement(name = "user")
    private List<User>     users      = CollectionUtils.getArrayList();

    @XmlElementWrapper(name = "jobs")
    @XmlElement(name = "job")
    private List<Job>      jobs       = CollectionUtils.getArrayList();

    @XmlElementWrapper(name = "statistics")
    @XmlElement(name = "jobCountPerDay")
    private List<JobCountPerDay>      jobCountPerDay = CollectionUtils.getArrayList();

    //~~~~~Getters and Setters~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /**
     * @return the industries
     */
    public List<Industry> getIndustries() {
        return industries;
    }
    /**
     * @param industries the industries to set
     */
    public void setIndustries(List<Industry> industries) {
        this.industries = industries;
    }
    /**
     * @return the regions
     */
    public List<Region> getRegions() {
        return regions;
    }
    /**
     * @param regions the regions to set
     */
    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }
    /**
     * @return the roles
     */
    public List<Role> getRoles() {
        return roles;
    }
    /**
     * @param roles the roles to set
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    /**
     * @return the users
     */
    public List<User> getUsers() {
        return users;
    }
    /**
     * @param users the users to set
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }
    /**
     * @return the jobs
     */
    public List<Job> getJobs() {
        return jobs;
    }
    /**
     * @param jobs the jobs to set
     */
    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
    /**
     * @return the jobCountPerDay
     */
    public List<JobCountPerDay> getJobCountPerDay() {
        return jobCountPerDay;
    }
    /**
     * @param jobCountPerDay the jobCountPerDay to set
     */
    public void setJobCountPerDay(List<JobCountPerDay> jobCountPerDay) {
        this.jobCountPerDay = jobCountPerDay;
    }



}
