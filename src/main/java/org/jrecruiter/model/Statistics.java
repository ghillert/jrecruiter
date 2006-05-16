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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Holds statistical information about a job posting.
 * 
 * @author Gunnar Hillert
 * @version $Revision: 1.4 $, $Date: 2006/04/12 02:24:49 $, $Author: ghillert $
 */
public class Statistics extends BaseObject implements Serializable {
    
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private Job job;
    private Long counter;
    private Long uniqueVisits;
    private Date lastAccess;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
	 * @return Returns the counter.
	 */
	public Long getCounter() {
		return counter;
	}

	/**
	 * @param counter The counter to set.
	 */
	public void setCounter(Long counter) {
		this.counter = counter;
	}

	/**
	 * @return Returns the job.
	 */
	public Job getJob() {
		return job;
	}

	/**
	 * @param job The job to set.
	 */
	public void setJob(Job job) {
		this.job = job;
	}

	/**
	 * @return Returns the lastAccess.
	 */
	public Date getLastAccess() {
		return lastAccess;
	}

	/**
	 * @param lastAccess The lastAccess to set.
	 */
	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}

	/**
	 * @return Returns the uniqueVisits.
	 */
	public Long getUniqueVisits() {
		return uniqueVisits;
	}

	/**
	 * @param uniqueVisits The uniqueVisits to set.
	 */
	public void setUniqueVisits(Long uniqueVisits) {
		this.uniqueVisits = uniqueVisits;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Statistics)) {
			return false;
		}
		Statistics rhs = (Statistics) object;
		return new EqualsBuilder().appendSuper(super.equals(object)).append(
				this.uniqueVisits, rhs.uniqueVisits).append(this.job, rhs.job)
				.append(this.counter, rhs.counter).append(this.lastAccess,
						rhs.lastAccess).append(this.id, rhs.id).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(670061697, 1690215845).appendSuper(
				super.hashCode()).append(this.uniqueVisits).append(this.job)
				.append(this.counter).append(this.lastAccess).append(this.id)
				.toHashCode();
	}
 
}

