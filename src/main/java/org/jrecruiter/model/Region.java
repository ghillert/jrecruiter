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

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
* This class represents an industry (assignable to a job posting).
*
* @author Gunnar Hillert
* @version $Id: Job.java 117 2007-04-15 23:05:06Z ghillert $
*/
@Entity
@Table(name="regions"
    , uniqueConstraints = {  }
)
public class Region  implements java.io.Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = -6878911371987087795L;

    // Fields

    /** Primary id of the industry */
    private Long id;
    private String name;
    private Set<Job> jobs = new HashSet<Job>(0);

    // Constructors

    /** default constructor */
    public Region() {}

    /** minimal constructor */
    public Region(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    /** full constructor */
    public Region(Long id, String name, Set<Job> jobs) {
       this.id = id;
       this.name = name;
       this.jobs = jobs;
    }

    // Property accessors
    @Id
    @Column(name="id", unique=true, nullable=false, insertable=true, updatable=true)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="name", unique=false, nullable=false, insertable=true, updatable=true)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="job")
    @Transient
    public Set<Job> getJobs() {
        return this.jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }




}


