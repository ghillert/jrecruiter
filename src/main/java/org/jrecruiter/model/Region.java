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
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;

import org.hibernate.annotations.BatchSize;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

/**
* This class represents an industry (assignable to a job posting).
*
* @author Gunnar Hillert
*
*/
@Entity
@BatchSize(size=15)
@Indexed
@Analyzer(impl = org.apache.lucene.analysis.standard.StandardAnalyzer.class)
@XmlAccessorType(XmlAccessType.FIELD)
public class Region extends BaseModelObject {

    /** serialVersionUID. */
    private static final long serialVersionUID = 5352730251720839547L;

    // Fields

    @XmlValue
    @XmlID
    @Column(unique=true, nullable=false, insertable=true, updatable=true)
    @Field(index = Index.YES, store = Store.YES)
    private String name;

    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="job")
    @Transient
    @ContainedIn
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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Region [id=" + id + ", name=" + name + "]";
    }

}


