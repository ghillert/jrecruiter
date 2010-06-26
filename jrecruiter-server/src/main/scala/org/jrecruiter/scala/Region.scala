package org.jrecruiter.scala

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

import scala.reflect.BeanProperty

import org.jrecruiter.common.CollectionUtils;

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
class Region {

    /** Primary id of the industry */
    @Id @GeneratedValue(generator="hibseq")
    @DocumentId
    @BeanProperty var id:Long = -1;

    @Column(name="name", unique=false, nullable=false, insertable=true, updatable=true)
 //   @Field(index=Index.TOKENIZED, store=Store.YES)
    @BeanProperty var name:String = "";

    @OneToMany(val cascade = CascadeType.ALL, fetch=Array(FetchType.LAZY), mappedBy="job")
    @Transient
    @ContainedIn
    @XmlTransient
    @BeanProperty var jobs = CollectionUtils.getHashSet();

}
