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
import java.util.Set;

import org.acegisecurity.GrantedAuthority;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;


/**
 * @author Dorota Puchala, Gunnar Hillert
 * @version $Revision: 1.4 $, $Date: 2006/03/01 05:19:15 $, $Author: ghillert $
 */
public class UserRole extends BaseObject implements Serializable, GrantedAuthority {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -4630353981807266697L;

    /**
     * Name of the role.
     */
    private String name;

    /**
     * Description.
     */
    private String description;

    /**
     * Set of usr that are associated to that role.
     */
    private Set < User > users;

    /**
     * Contructor.
     */
    public UserRole() {
        super();
    }

    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Returns the users.
     */
    public Set<User> getUsers() {
        return users;
    }

    /**
     * @param users The users to set.
     */
    public void setUsers(Set<User> users) {
        this.users = users;
    }

    /**
     * @see org.acegisecurity.GrantedAuthority#getAuthority()
     * @return name of the role
     */
    public String getAuthority() {
        return name;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof UserRole))
            return false;
        UserRole castOther = (UserRole) other;
        return new EqualsBuilder().append(name, castOther.name).append(
                description, castOther.description).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(420509049, 439043407).append(name).append(
                description).toHashCode();
    }

}

