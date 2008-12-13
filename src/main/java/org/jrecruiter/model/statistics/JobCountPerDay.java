/**
 *
 */
package org.jrecruiter.model.statistics;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Used for portraying statistical information regarding how many jobs have been
 * posted for that day.
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames="jobDate"))
public class JobCountPerDay {

    /** Initialize Logging. */
    private final static Logger LOGGER = LoggerFactory.getLogger(JobCountPerDay.class);

    //~~~~~Fields~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
    
    @Column(unique=false, nullable=true, insertable=true, updatable=true, length=8)
    @Temporal(TemporalType.DATE)
    private java.util.Date jobDate;
    
    @Column(unique=false, nullable=false, insertable=true, updatable=true, precision=0, scale=10)
    private Long numberOfJobsPosted  = Long.valueOf(0);
    
    @Column(unique=false, nullable=false, insertable=true, updatable=true, precision=0, scale=10)
    private Long numberOfJobsDeleted = Long.valueOf(0);
    
    @Column(unique=false, nullable=false, insertable=true, updatable=true, precision=0, scale=10)
    private Long totalNumberOfJobs   = Long.valueOf(0);

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return String.format("%s[jobDate=%s, numberOfJobsPosted=%s]",
                super.toString(),
                this.jobDate,
                this.numberOfJobsPosted);
    }

    //~~~~~Getters and Setters~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    public java.util.Date getJobDate() {
        return this.jobDate;
    }

    public Long getNumberOfJobsPosted() {
        return numberOfJobsPosted;
    }

	public Long getNumberOfJobsDeleted() {
		return numberOfJobsDeleted;
	}

	public void setNumberOfJobsDeleted(Long numberOfJobsDeleted) {
		this.numberOfJobsDeleted = numberOfJobsDeleted;
	}

	public Long getTotalNumberOfJobs() {
		return totalNumberOfJobs;
	}

	public void setTotalNumberOfJobs(Long totalNumberOfJobs) {
		this.totalNumberOfJobs = totalNumberOfJobs;
	}

	public void setJobDate(java.util.Date jobDate) {
		this.jobDate = jobDate;
	}

	public void setNumberOfJobsPosted(Long numberOfJobsPosted) {
		this.numberOfJobsPosted = numberOfJobsPosted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
