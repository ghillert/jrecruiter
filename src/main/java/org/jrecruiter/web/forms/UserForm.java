/**
 *
 */
package org.jrecruiter.web.forms;

import org.jrecruiter.model.User;

/**
 * The user form extends the the user. This is necessary on some
 * forms the user must enter the password twice.
 *
 * @author  Gunnar Hillert
 * @version $Id$
 *
 */
public class UserForm extends User {

    /** serialVersionUID. */
	private static final long serialVersionUID = -2648285589713561993L;

	private String password2;

	/**
	 *  Constructor.
	 */
	public UserForm() {
	}

	/**
	 * @return the password2
	 */
	public String getPassword2() {
		return password2;
	}

	/**
	 * @param password2 the password2 to set
	 */
	public void setPassword2(String password2) {
		this.password2 = password2;
	}

}
