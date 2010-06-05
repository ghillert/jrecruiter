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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.jrecruiter.common.CollectionUtils;

/**
* This class represents an industry (assignable to a job posting).
*
* @author Gunnar Hillert
* @version $Id$
*/
@Entity
@BatchSize(size=15)
@Table(uniqueConstraints = {  })
@Indexed
@Analyzer(impl = org.apache.lucene.analysis.standard.StandardAnalyzer.class)
public class Region  implements java.io.Serializable {

    /** serialVersionUID. */
    private static final long serialVersionUID = -6878911371987087795L;

    // Fields

    /** Primary id of the industry */
    private Long id;
    private String name;

    @XmlTransient
    private Set<Job> jobs = CollectionUtils.getHashSet();

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
    @Id @GeneratedValue(generator="hibseq")
        @DocumentId
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="name", unique=false, nullable=false, insertable=true, updatable=true)
    @Field(index=Index.TOKENIZED, store=Store.YES)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="job")
    @Transient
    @ContainedIn
    public Set<Job> getJobs() {
        return this.jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }




}


