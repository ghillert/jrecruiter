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
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
* This class represents configuration data.
*
* @author Gunnar Hillert
* @version $Revision: 1.5 $, $Date: 2006/03/19 22:51:55 $, $Author: ghillert $
*/
public class Configuration extends BaseObject implements Serializable {
    
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	private String key;
    private String text;
    private Date lastModified;


    /**
	 * Constructor.
	 */
	public Configuration() {
		super();

	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Configuration))
			return false;
		Configuration castOther = (Configuration) other;
		return new EqualsBuilder().append(key, castOther.key).append(text,
				castOther.text).append(lastModified, castOther.lastModified)
				.isEquals();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder(387190309, 1466501611).append(key).append(
				text).append(lastModified).toHashCode();
	}

	
	
}

