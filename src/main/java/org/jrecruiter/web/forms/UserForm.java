/**
 * 
 */
package org.jrecruiter.web.forms;

import org.jrecruiter.model.User;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;

/**
 * The user form extends the the user. This is necessary on some
 * forms the user must enter the password twice.
 * 
 * @author  Gunnar Hillert
 * @version $Id: User.java 111 2007-03-18 04:53:15Z ghillert $
 *
 */
public class UserForm extends User {

    @NotBlank
    private String password2;
	
	/**
	 *  Constructor.
	 */
	public UserForm() {
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
