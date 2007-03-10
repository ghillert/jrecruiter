/**
 * 
 */
package org.jrecruiter.web.forms;

import org.jrecruiter.model.User;

/**
 * @author hillert
 *
 */
public class UserForm extends User {

	private String password2;
	
	/**
	 * 
	 */
	public UserForm() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param username
	 */
	public UserForm(String username) {
		super(username);
		// TODO Auto-generated constructor stub
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
