package org.jrecruiter.scala

import javax.persistence.CascadeType
import javax.xml.bind.annotation.{XmlElementRef, XmlTransient}
import org.jrecruiter.model.Job
import collection.mutable.HashSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlID;

import org.hibernate.annotations.BatchSize;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import scala.reflect.BeanProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import org.jrecruiter.common.CollectionUtils;
import javax.xml.bind.annotation.XmlValue;

/**
 * Created by IntelliJ IDEA.
 * User: hillert
 * Date: Jun 18, 2010
 * Time: 1:05:29 AM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@BatchSize(size=15)
@Indexed
@Analyzer(impl = classOf[org.apache.lucene.analysis.standard.StandardAnalyzer])
@XmlAccessorType(XmlAccessType.FIELD)
class Region (
    myid : java.lang.Long,
    myname : String) {


    def this() = this(null, null)

    /** Primary id of the industry */
    @Id @GeneratedValue(generator="hibseq")
    @DocumentId
    @BeanProperty
    @XmlAttribute
    var id : java.lang.Long = myid

    @Column(name="name", unique=false, nullable=false, insertable=true, updatable=true)
    @Field(index=Index.TOKENIZED, store=Store.YES)
    @BeanProperty
    @XmlValue
    @XmlID
    var name : String = myname


    @OneToMany(cascade = Array(CascadeType.ALL), fetch=FetchType.LAZY, mappedBy="job")
    @Transient
    @ContainedIn
    @XmlTransient
    var jobs: java.util.Set[Job] = new java.util.HashSet[Job]


}
