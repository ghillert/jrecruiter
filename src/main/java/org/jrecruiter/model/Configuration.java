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
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * This class represents configuration data.
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
@Entity
@Table(uniqueConstraints = {  }
)
public class Configuration implements Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 7279232706235477101L;

	// Fields

	private String messageKey;
	private String messageText;
	private Date lastModified;

	// Constructors

	/** default constructor */
	public Configuration() {
	}

	/** minimal constructor */
	public Configuration(String messageKey) {
		this.messageKey = messageKey;
	}
	/** full constructor */
	public Configuration(String messageKey, String messageText, Date lastModified) {
		this.messageKey = messageKey;
		this.messageText = messageText;
		this.lastModified = lastModified;
	}

	// Property accessors
	@Id
	@Column(name="message_key", unique=true, nullable=false, insertable=true, updatable=true, length=200)
	public String getMessageKey() {
		return this.messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	@Column(name="message_text", unique=false, nullable=true, insertable=true, updatable=true)
	public String getMessageText() {
		return this.messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	@Column(name="last_modified", unique=false, nullable=true, insertable=true, updatable=true, length=8)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastModified() {
		return this.lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
}

