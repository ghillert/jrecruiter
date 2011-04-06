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
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.jrecruiter.common.Constants.UserAuthenticationType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * This class represents a user.
 *
 * @author  Gunnar Hillert
 * @version $Id$
 */
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(uniqueConstraints = { @UniqueConstraint( columnNames = { "email" } ),
        @UniqueConstraint( columnNames = { "username" } ) }
)
public class User implements Serializable, UserDetails {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -1530641858689315559L;

    //~~~~~Fields~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @XmlAttribute
    @Id @GeneratedValue(generator="hibseq")
    private Long id;

    @XmlID
    @NotNull
    @Size(min=5, max=50)
    @Column(unique=true)
    private String username;

    @NotNull
    @Size(max=120)
    private String password;

    @NotNull
    @Size(max=50)
    private String firstName;

    @NotNull
    @Size(max=50)
    private String lastName;

    @Size(max=50)
    private String company;

    @Size(max=25)
    private String phone;

    @Size(max=25)
    private String fax;

    @Size(max=50)
    @Email
    private String email;

    @XmlAttribute
    @XmlJavaTypeAdapter(JaxbDateAdapter.class)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    @XmlAttribute
    @XmlJavaTypeAdapter(JaxbDateAdapter.class)
    private Date updateDate;

    @XmlAttribute
    @XmlJavaTypeAdapter(JaxbDateAdapter.class)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginDate;

    @XmlTransient
    @OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="user")
    private Set<Job> jobs = new HashSet<Job>(0);

    @XmlElement(name="roles")
    @OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="user")
    private Set<UserToRole> userToRoles = new HashSet<UserToRole>(0);

    @XmlAttribute
    private Boolean enabled  = Boolean.FALSE;

    @Size(max=36)
    @Column(unique=true)
    private String  verificationKey;

    @Type(
        type = "org.jrecruiter.common.GenericEnumUserType",
        parameters = {
                @Parameter(name  = "enumClass", value = "org.jrecruiter.common.Constants$UserAuthenticationType")}
    )
    @NotNull
    private UserAuthenticationType userAuthenticationType = UserAuthenticationType.USERNAME_PASSWORD;

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
            String email, Date registerDate,
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
        this.updateDate = updateDate;
        this.jobs = jobs;
        this.userToRoles = userToRoles;
    }

    // Property accessors
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

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

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Set<Job> getJobs() {
        return this.jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }

    public Set<UserToRole> getUserToRoles() {
        return this.userToRoles;
    }

    public void setUserToRoles(Set<UserToRole> userToRoles) {
        this.userToRoles = userToRoles;
    }

    public UserAuthenticationType getUserAuthenticationType() {
        return userAuthenticationType;
    }

    public void setUserAuthenticationType(
            UserAuthenticationType userAuthenticationType) {
        this.userAuthenticationType = userAuthenticationType;
    }

    //~~~~~~~~ Implemented from Spring Security ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
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
        return true;
    }

    /**
     * @see org.acegisecurity.userdetails.UserDetails#isEnabled()
     * @return Returns the enabled.
     */
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getVerificationKey() {
        return verificationKey;
    }

    public void setVerificationKey(String verificationKey) {
        this.verificationKey = verificationKey;
    }

    /**
     * @return the lastLoginDate
     */
    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    /**
     * @param lastLoginDate the lastLoginDate to set
     */
    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    /* (non-Javadoc)
     * @see org.acegisecurity.userdetails.UserDetails#getAuthorities()
     */
    @Transient
    @Override
    public Collection<GrantedAuthority> getAuthorities() {

        final Set <GrantedAuthority> roles = new HashSet<GrantedAuthority>();

        for (UserToRole userToRole : this.userToRoles) {
            roles.add(userToRole.getRole());
        }

        return roles;

    }

    @Override
    public String toString()
    {
        final String TAB = " | ";

        final StringBuilder retValue = new StringBuilder();

        retValue.append("User ( ")
            .append(super.toString()).append(TAB)
            .append("id = ").append(this.getId()).append(TAB)
            .append("Username = ").append(this.getUsername()).append(TAB)
            .append("Email = ").append(this.getEmail()).append(TAB)
            .append("FirstName = ").append(this.getFirstName()).append(TAB)
            .append("LastName = ").append(this.getLastName()).append(TAB)
            .append(" )");

        return retValue.toString();
    }

}
