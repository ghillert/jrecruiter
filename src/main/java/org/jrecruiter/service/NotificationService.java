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
package org.jrecruiter.service;

import java.util.Map;

/**
 * Repsonsible for handling any type of notifications e.g. password reminder email,
 * emails containing job posting information etc.
 *  
 * @author Gunnar Hillert
 * @version $Id:JobService.java 128 2007-07-27 03:55:54Z ghillert $
 */
public interface NotificationService {

	/**
	 * Method for sending emails.
	 *
	 * @param email The email address
	 * @param context Map that contains data that needs to be rendered in the email
	 * @param templatePrefix Freemarker template
	 */
	void sendEmail(String email, Map context, String templatePrefix);
	
}
