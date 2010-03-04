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
import java.util.HashSet;
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
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;

/**
 * Represents a User Role.
 *
 * @author Gunnar Hillert
 * @version @version $Id$
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint( columnNames = { "name" } ) }
)
public class Role implements Serializable, GrantedAuthority {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 7447568690062928081L;

    // Fields

    private Long id;

    /** Name of the role. */
    private String name;

    /** Description. */
    private String description;
    private Set<UserToRole> userToRoles = new HashSet<UserToRole>(0);

    // Constructors

    /** default constructor */
    public Role() {
    }

    /** minimal constructor */
    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    /** full constructor */
    public Role(Long id, String name, String description, Set<UserToRole> userToRoles) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userToRoles = userToRoles;
    }

    // Property accessors
    @Id @GeneratedValue(generator="hibseq")
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(unique=true, nullable=false, insertable=true, updatable=true, length=50)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(unique=false, nullable=true, insertable=true, updatable=true)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="role")
    public Set<UserToRole> getUserToRoles() {
        return this.userToRoles;
    }

    public void setUserToRoles(Set<UserToRole> userToRoles) {
        this.userToRoles = userToRoles;
    }

    // For ACEGI

    /**
     * @see org.acegisecurity.GrantedAuthority#getAuthority()
     * @return name of the role
     */
    @Transient
    public String getAuthority() {
        return name;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.getAuthority();
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Role other = (Role) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    public int compareTo(Object o) {

        if (o == null) {
            return -1;
        } else {
            final Role role = (Role) o;
            return this.name.compareTo(role.getName());
        }

    }

}

