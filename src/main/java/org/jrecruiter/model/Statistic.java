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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;

/**
 * Holds statistical information about a job posting.
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
@Entity
@Table(uniqueConstraints = {  }
)
public class Statistic implements Serializable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -6450245841259870972L;

    // Fields

    private Long  id;
    private Job job;
    private Long counter;
    private Date lastAccess;
    private Long uniqueVisits;

    // Constructors

    /** default constructor */
    public Statistic() {
    }

    /** full constructor */
    public Statistic(Long id, Long counter, Date lastAccess, Long uniqueVisits) {
        this.id = id;
        this.counter = counter;
        this.lastAccess = lastAccess;
        this.uniqueVisits = uniqueVisits;
    }

    @Id
    @GeneratedValue(generator="myForeignGenerator")
    @org.hibernate.annotations.GenericGenerator(
        name="myForeignGenerator",
        strategy="foreign",
        parameters=@Parameter(name="property", value="job")
    )
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne(fetch=FetchType.LAZY, optional=false)
    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Column(name="counter", unique=false, nullable=false, insertable=true, updatable=true)
    public Long getCounter() {
        return this.counter;
    }

    public void setCounter(Long counter) {
        this.counter = counter;
    }

    @Column(name="last_access", unique=false, nullable=false, insertable=true, updatable=true, length=8)
    public Date getLastAccess() {
        return this.lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    @Column(name="unique_visits", unique=false, nullable=false, insertable=true, updatable=true)
    public Long getUniqueVisits() {
        return this.uniqueVisits;
    }

    public void setUniqueVisits(Long uniqueVisits) {
        this.uniqueVisits = uniqueVisits;
    }

}

