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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * This class represents a UserToRole (Many-To-Many 'Link Object' between
 * User and Role).
 *
 * @author  Gunnar Hillert
 * @version $Id$
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint( columnNames = { "users_id", "roles_id" } ) }
)
public class UserToRole  implements java.io.Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 5133190927935871627L;


	// Fields

     private Long id;
     private Role role;
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

    // Property accessors
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(unique=true, nullable=false, insertable=true, updatable=true)
	public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(cascade={},
        fetch=FetchType.LAZY)
    @JoinColumn(name="roles_id", unique=false, nullable=false, insertable=true, updatable=true)
    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @ManyToOne(cascade={},
        fetch=FetchType.LAZY)
    @JoinColumn(name="users_id", unique=false, nullable=false, insertable=true, updatable=true)
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }




}