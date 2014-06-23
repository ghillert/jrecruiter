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

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This class represents a UserToRole (Many-To-Many 'Link Object' between
 * User and Role).
 *
 * @author  Gunnar Hillert
 * @version $Id$
 */
@Entity
@Table()
//uniqueConstraints = { @UniqueConstraint( columnNames = { "users_id", "roles_id" } ) }
@XmlAccessorType(XmlAccessType.FIELD)
public class UserToRole extends BaseModelObject {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 5133190927935871627L;


     //~~~~~Variable Declarations~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @XmlTransient
    @ManyToOne
    @JoinColumn(name="roles_id", unique=false, nullable=false, insertable=true, updatable=true)
     private Role role;

    @XmlTransient
    @ManyToOne
    @JoinColumn(name="users_id", unique=false, nullable=false, insertable=true, updatable=true)
     private User user;

     // Constructors

    /** default constructor */
    public UserToRole() {
    }

    /** full constructor */
    public UserToRole(Long id, Role role, User user) {
       this.id = id;
       this.role = role;
       this.user = user;
    }

    //~~~~~Getters and Setters~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @XmlID
    public String getRoleName() {
    	return role.getName();
    }

    public void setRoleName(String roleName) {
    	if (this.role == null) {
    		this.role = new Role(roleName);
    	} else {
    		this.role.setName(roleName);
    	}

    }
}
