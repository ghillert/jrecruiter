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

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Email;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;
import org.springmodules.validation.bean.conf.loader.annotation.handler.MaxLength;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * This class represents a user.
 *
 * @author  Gunnar Hillert
 * @version $Id$
 */

@Entity
@Table(name="users"
    ,schema="public"
    , uniqueConstraints = {  }
)
public class User extends BaseObject implements Serializable, UserDetails{

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -8704683609547598214L;

    private boolean accountExpired;
    private boolean accountLocked;
    //~~ Acegi
    private GrantedAuthority[] authorities;
    
    @Length(min = 0, max = 25)
    private String company;
    private boolean credentialsExpired;
    
    @NotBlank
    @Email
    @MaxLength(value=50)
    private String email;
    private boolean enabled = true;
    
    @Length(min = 0, max = 25)
    private String fax;
    
    @NotBlank
    @Length(min = 0, max = 50)
    private String firstName;
    
    @NotBlank
    @Length(min = 0, max = 50)
    private String lastName;

    @NotBlank
    @Length(min = 1, max = 25)
    private String password;
    
    @Length(min = 0, max = 25)
    private String phone;
    
    private Date registerDate;
    private Set < UserRole > roles;
    private Date updateDate;
    private List < Job > jobs;
    
    @NotBlank
    @Length(min = 1, max = 25)
    private String username;


    /**
     * Constructor.
     *
     */
    public User() {
        super();
    }

    /**
     * Constructor.
     *
     * @param username
     */
    public User(String username) {
        super();
        this.username = username;

    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof User))
            return false;
        User castOther = (User) other;
        return new EqualsBuilder().append(firstName, castOther.firstName)
                .append(lastName, castOther.lastName).append(company,
                        castOther.company).append(phone, castOther.phone)
                .append(fax, castOther.fax).append(email, castOther.email)
                .append(registerDate, castOther.registerDate).append(
                        updateDate, castOther.updateDate).append(password,
                        castOther.password)
                .append(username, castOther.username).isEquals();
    }

    /**
     * @see org.acegisecurity.userdetails.UserDetails#getAuthorities()
     * @return Returns the authorities.
     */
    @Transient
    public GrantedAuthority[] getAuthorities() {
        if (roles != null) {
            return (GrantedAuthority[]) roles.toArray(new GrantedAuthority[0]);
        } else {
            return null;
        }
    }

    /**
     * @return Returns the company.
     */
    
    @Column(name="company", unique=false, nullable=true, insertable=true, updatable=true, length=25)
    public String getCompany() {
        return company;
    }

    /**
     *
     * @return
     */
    @Column(name="email", unique=false, nullable=true, insertable=true, updatable=true, length=50)
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return
     */
    @Column(name="fax", unique=false, nullable=true, insertable=true, updatable=true, length=25)
    public String getFax() {
        return fax;
    }
    /**
     *
     * @return
     */
    @Column(name="first_name", unique=false, nullable=true, insertable=true, updatable=true, length=50)
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @return
     */
    @Column(name="last_name", unique=false, nullable=true, insertable=true, updatable=true, length=50)
    public String getLastName() {
        return lastName;
    }

    /**
     * @see org.acegisecurity.userdetails.UserDetails#getPassword()
     * @return Returns the password.
     */
    @Column(name="user_passwd", unique=false, nullable=false, insertable=true, updatable=true, length=25)
    public String getPassword() {
        return password;
    }

    /**
     *
     * @return
     */
    @Column(name="phone", unique=false, nullable=true, insertable=true, updatable=true, length=25)
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @return
     */
    @Column(name="register_date", unique=false, nullable=true, insertable=true, updatable=true, length=8)
    public Date getRegisterDate() {
        return registerDate;
    }

    @ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "users")
    public Set<UserRole> getRoles() {
        return roles;
    }

    /**
     *
     * @return
     */
    @Column(name="update_date", unique=false, nullable=true, insertable=true, updatable=true, length=8)
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     *
     * @return
     */
  //  @OneToMany(mappedBy="job")
    @Transient
    public List < Job >getUserJobs() {
        return jobs;
    }

    /**
     * @see org.acegisecurity.userdetails.UserDetails#getUsername()
     * @return Returns the username.
     */
    @Id
    @Column(name="user_name", unique=true, nullable=false, insertable=true, updatable=true, length=25)
    public String getUsername() {
        return username;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(-1294575639, 45234427).append(firstName)
                .append(lastName).append(company).append(phone).append(fax)
                .append(email).append(registerDate).append(updateDate).append(
                        password).append(username).toHashCode();
    }

    /**
     * @return Returns the accountExpired.
     */
    @Transient
    public boolean isAccountExpired() {
        return accountExpired;
    }

    /**
     * @return Returns the accountLocked.
     */
    @Transient
    public boolean isAccountLocked() {
        return accountLocked;
    }

    //~~~~~~~~ Implemented from Acegi Seurity ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     * @see org.acegisecurity.userdetails.UserDetails#isAccountNonExpired()
     * @return Returns the accountNonExpired.
     */
    @Transient
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }


    /**
     * @see org.acegisecurity.userdetails.UserDetails#isAccountNonLocked()
     * @return Returns the accountNonLocked.
     */
    @Transient
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    /**
     * @return Returns the credentialsExpired.
     */
    @Transient
    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }

    /**
     * @see org.acegisecurity.userdetails.UserDetails#isCredentialsNonExpired()
     * @return Returns the credentialsNonExpired.
     */
    @Transient
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    /**
     * @see org.acegisecurity.userdetails.UserDetails#isEnabled()
     * @return Returns the enabled.
     */
    @Transient
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param accountExpired The accountExpired to set.
     */
    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    /**
     * @param accountLocked The accountLocked to set.
     */
    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    /**
     * @param company The company to set.
     */
    public void setCompany(final String company) {
        this.company = company;
    }

    /**
     * @param credentialsExpired The credentialsExpired to set.
     */
    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    /**
     *
     * @param email
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * @param enabled The enabled to set.
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     *
     * @param fax
     */
    public void setFax(final String fax) {
        this.fax = fax;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @param phone
     */
    public void setPhone(final String phone) {
        this.phone = phone;
    }

    /**
     *
     * @param registerDate
     */
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * @param roles The roles to set.
     */
    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    /**
     *
     * @param updateDate
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     *
     * @param userJobs
     */
    public void setUserJobs(List < Job > userJobs) {
        this.jobs = userJobs;
    }

    /**
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }


}
