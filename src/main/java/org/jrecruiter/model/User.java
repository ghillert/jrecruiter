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
import java.util.List;
import java.util.Set;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * This class represents a user.
 *
 * @author Dorota Puchala, Gunnar Hillert
 * @version $Revision: 1.5 $, $Date: 2006/03/19 21:55:18 $, $Author: ghillert $
 */
public class User extends BaseObject implements Serializable, UserDetails{

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = -8704683609547598214L;

    private String firstName;
    private String lastName;
    private String company;
    private String phone;
    private String fax;
    private String email;
    private Date registerDate;
    private Date updateDate;
    private Set < UserRole > roles;
    private List userJobs;

    //~~ Acegi
    private GrantedAuthority[] authorities;
    private String password;
    private String username;
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean credentialsExpired;
    private boolean enabled = true;


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
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
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
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return Returns the company.
     */
    public String getCompany() {
        return company;
    }
    /**
     * @param company The company to set.
     */
    public void setCompany(final String company) {
        this.company = company;
    }

    /**
     *
     * @return
     */
    public String getPhone() {
        return phone;
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
     * @return
     */
    public String getFax() {
        return fax;
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
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public Date getRegisterDate() {
        return registerDate;
    }

    /**
     *
     * @param registerDate
     */
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    /**
     *
     * @return
     */
    public Date getUpdateDate() {
        return updateDate;
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
     * @return
     */
    public List getUserJobs() {
        return userJobs;
    }

    /**
     *
     * @param userJobs
     */
    public void setUserJobs(List userJobs) {
        this.userJobs = userJobs;
    }


    /**
     * @return Returns the roles.
     */
    public Set<UserRole> getRoles() {
        return roles;
    }

    /**
     * @param roles The roles to set.
     */
    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    //~~~~~~~~ Implemented from Acegi Seurity ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    /**
     * @see org.acegisecurity.userdetails.UserDetails#isAccountNonExpired()
     * @return Returns the accountNonExpired.
     */
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    /**
     * @see org.acegisecurity.userdetails.UserDetails#isAccountNonLocked()
     * @return Returns the accountNonLocked.
     */
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    /**
     * @see org.acegisecurity.userdetails.UserDetails#getAuthorities()
     * @return Returns the authorities.
     */
    public GrantedAuthority[] getAuthorities() {
        if (roles != null) {
            return (GrantedAuthority[]) roles.toArray(new GrantedAuthority[0]);
        } else {
            return null;
        }
    }

    /**
     * @see org.acegisecurity.userdetails.UserDetails#isCredentialsNonExpired()
     * @return Returns the credentialsNonExpired.
     */
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    /**
     * @see org.acegisecurity.userdetails.UserDetails#isEnabled()
     * @return Returns the enabled.
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled The enabled to set.
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @see org.acegisecurity.userdetails.UserDetails#getPassword()
     * @return Returns the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @see org.acegisecurity.userdetails.UserDetails#getUsername()
     * @return Returns the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return Returns the accountExpired.
     */
    public boolean isAccountExpired() {
        return accountExpired;
    }

    /**
     * @param accountExpired The accountExpired to set.
     */
    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    /**
     * @return Returns the accountLocked.
     */
    public boolean isAccountLocked() {
        return accountLocked;
    }

    /**
     * @param accountLocked The accountLocked to set.
     */
    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    /**
     * @return Returns the credentialsExpired.
     */
    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }

    /**
     * @param credentialsExpired The credentialsExpired to set.
     */
    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
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
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(-1294575639, 45234427).append(firstName)
                .append(lastName).append(company).append(phone).append(fax)
                .append(email).append(registerDate).append(updateDate).append(
                        password).append(username).toHashCode();
    }


}
