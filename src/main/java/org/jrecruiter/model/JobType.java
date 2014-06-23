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

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlValue;

import org.jrecruiter.common.CollectionUtils;

/**
* This class represents an industry (assignable to a job posting).
*
* @author Gunnar Hillert
* @version $Id$
*/
@Entity
@Table(uniqueConstraints = {  })
@XmlAccessorType(XmlAccessType.FIELD)
public class JobType extends BaseModelObject {

    /** serialVersionUID. */
    private static final long serialVersionUID = -6878911371987087795L;

    // Fields

    @XmlValue
    @Column(unique=false, nullable=false, insertable=true, updatable=true)
    private String name;

    @OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="job")
    @Transient
    private Set<Job> jobs = CollectionUtils.getHashSet();

    // Constructors

    /** default constructor */
    public JobType() {}

    /** minimal constructor */
    public JobType(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    /** full constructor */
    public JobType(Long id, String name, Set<Job> jobs) {
       this.id = id;
       this.name = name;
       this.jobs = jobs;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Job> getJobs() {
        return this.jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }

}


