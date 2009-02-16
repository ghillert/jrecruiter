/**
 * 
 */
package org.jrecruiter.web.forms;

import java.io.Serializable;

/**
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
public class ConfigurationForm implements Serializable {

    /** serialVersionUID. */
	private static final long serialVersionUID = 7593027618105623364L;
	
	private String mailingListSubject;
    private String mailingListTemplate;
    private String mailingListEmail;
    private String mailFrom;
    private String passwordSubject;
    private String passwordTemplate;
	
    /**
	 * @return the mailFrom
	 */
	public String getMailFrom() {
		return mailFrom;
	}
	/**
	 * @param mailFrom the mailFrom to set
	 */
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}
	/**
	 * @return the mailingListEmail
	 */
	public String getMailingListEmail() {
		return mailingListEmail;
	}
	/**
	 * @param mailingListEmail the mailingListEmail to set
	 */
	public void setMailingListEmail(String mailingListEmail) {
		this.mailingListEmail = mailingListEmail;
	}
	/**
	 * @return the mailingListSubject
	 */
	public String getMailingListSubject() {
		return mailingListSubject;
	}
	/**
	 * @param mailingListSubject the mailingListSubject to set
	 */
	public void setMailingListSubject(String mailingListSubject) {
		this.mailingListSubject = mailingListSubject;
	}
	/**
	 * @return the mailingListTemplate
	 */
	public String getMailingListTemplate() {
		return mailingListTemplate;
	}
	/**
	 * @param mailingListTemplate the mailingListTemplate to set
	 */
	public void setMailingListTemplate(String mailingListTemplate) {
		this.mailingListTemplate = mailingListTemplate;
	}
	/**
	 * @return the passwordSubject
	 */
	public String getPasswordSubject() {
		return passwordSubject;
	}
	/**
	 * @param passwordSubject the passwordSubject to set
	 */
	public void setPasswordSubject(String passwordSubject) {
		this.passwordSubject = passwordSubject;
	}
	/**
	 * @return the passwordTemplate
	 */
	public String getPasswordTemplate() {
		return passwordTemplate;
	}
	/**
	 * @param passwordTemplate the passwordTemplate to set
	 */
	public void setPasswordTemplate(String passwordTemplate) {
		this.passwordTemplate = passwordTemplate;
	}
	


	
	
}
