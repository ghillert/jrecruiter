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
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.UserDetails;

/**
 * This class represents a user.
 *
 * @author  Gunnar Hillert
 * @version $Id$
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint( columnNames = { "email" } ),
		@UniqueConstraint( columnNames = { "username" } ) }
)
public class User implements Serializable, UserDetails{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -1530641858689315559L;

	// Fields
	private Long id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String company;
	private String phone;
	private String fax;
	private String email;
	private Date registrationDate;
	private Date expirationDate;
	private Date updateDate;
	private Set<Job> jobs = new HashSet<Job>(0);
	private Set<UserToRole> userToRoles = new HashSet<UserToRole>(0);

	private GrantedAuthority[] authorities;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(Long id, String username, String password, String firstName,
			String lastName, String email, Date registerDate) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.registrationDate = registerDate;
	}
	/** full constructor */
	public User(Long id, String username, String password, String firstName,
			String lastName, String company, String phone, String fax,
			String email, Date registerDate, Date expireDate,
			Date updateDate, Set<Job> jobs,
			Set<UserToRole> userToRoles) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.company = company;
		this.phone = phone;
		this.fax = fax;
		this.email = email;
		this.registrationDate = registerDate;
		this.expirationDate = expireDate;
		this.updateDate = updateDate;
		this.jobs = jobs;
		this.userToRoles = userToRoles;
	}

	// Property accessors
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(unique=true, nullable=false, insertable=true,
			updatable=true, length=50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(unique=false, nullable=false, insertable=true,
			updatable=true, length=120)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(unique=false, nullable=false, insertable=true,
			updatable=true, length=50)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(unique=false, nullable=false, insertable=true,
			updatable=true, length=50)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(unique=false, nullable=true, insertable=true,
			updatable=true, length=50)
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(unique=false, nullable=true, insertable=true,
			updatable=true, length=25)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(unique=false, nullable=true, insertable=true,
			updatable=true, length=25)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(unique=true, nullable=false, insertable=true,
			updatable=true, length=50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(unique=false, nullable=false, insertable=true,
			updatable=true, length=8)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getRegistrationDate() {
		return this.registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Column(unique=false, nullable=true, insertable=true,
			updatable=true, length=8)
	public Date getExpirationDate() {
		return this.expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Column(unique=false, nullable=true, insertable=true,
			updatable=true, length=8)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="user")
	public Set<Job> getJobs() {
		return this.jobs;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}
	@OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="user")
	public Set<UserToRole> getUserToRoles() {
		return this.userToRoles;
	}

	public void setUserToRoles(Set<UserToRole> userToRoles) {
		this.userToRoles = userToRoles;
	}

	//~~~~~~~~ Implemented from Acegi Seurity ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	/**
	 * @see org.acegisecurity.userdetails.UserDetails#isAccountNonExpired()
	 * @return Returns the accountNonExpired.
	 */
	@Transient
	public boolean isAccountNonExpired() {
		return true;
	}


	/**
	 * @see org.acegisecurity.userdetails.UserDetails#isAccountNonLocked()
	 * @return Returns the accountNonLocked.
	 */
	@Transient
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * @see org.acegisecurity.userdetails.UserDetails#isCredentialsNonExpired()
	 * @return Returns the credentialsNonExpired.
	 */
	@Transient
	public boolean isCredentialsNonExpired() {
		if (this.expirationDate == null ||
				this.expirationDate.getTime() <= new Date().getTime()) {
			return true;
		}

		return false;
	}

	/**
	 * @see org.acegisecurity.userdetails.UserDetails#isEnabled()
	 * @return Returns the enabled.
	 */
	@Transient
	public boolean isEnabled() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.acegisecurity.userdetails.UserDetails#getAuthorities()
	 */
    @Transient
    public GrantedAuthority[] getAuthorities() {

    	if (this.userToRoles != null) {

        	final Set <GrantedAuthority> roles = new HashSet<GrantedAuthority>();

        	for (UserToRole userToRole : this.userToRoles) {
        		roles.add(userToRole.getRole());
        	}

            return (GrantedAuthority[]) roles.toArray(new GrantedAuthority[0]);
        } else {
        	return new GrantedAuthority[0];
        }
    }

}
