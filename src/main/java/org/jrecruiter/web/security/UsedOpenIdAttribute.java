/*
 *  http://www.jrecruiter.org
 *
 *  Disclaimer of Warranty.
 *
 *  Unless required by applicable law or agreed to in writing, Licensor provides
 *  the Work (and each Contributor provides its Contributions) on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied,
 *  including, without limitation, any warranties or conditions of TITLE,
 *  NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE. You are
 *  solely responsible for determining the appropriateness of using or
 *  redistributing the Work and assume any risks associated with Your exercise of
 *  permissions under this License.
 *
 */
package org.jrecruiter.web.security;

import org.springframework.security.openid.OpenIDAttribute;

/**
 * Provides access to the JVM System Properties in a type-safe manner.
 *
 * @author Gunnar Hillert
 * @version $Id: Constants.java 532 2009-08-28 03:50:24Z ghillert $
 */
public enum UsedOpenIdAttribute {

	AX_FIRST_NAME ("AX_FirstName", "http://axschema.org/namePerson/first"),
    AX_LAST_NAME  ("AX_LastName",  "http://axschema.org/namePerson/last"),
    EMAIL         ("EMAIL",        "http://schema.openid.net/contact/email"),
    LAST_NAME     ("LAST_NAME",    "http://schema.openid.net/namePerson/last"),
	FIRST_NAME    ("FIRST_NAME",   "http://schema.openid.net/namePerson/first"),
	NAME_PERSON   ("NAME_PERSON",  "http://schema.openid.net/namePerson");
			
		
    private OpenIDAttribute openIdAttribute;
    
    private UsedOpenIdAttribute(final String name, final String type) {
    	openIdAttribute = new OpenIDAttribute(name, type);
    	openIdAttribute.setRequired(true);
    }

	public OpenIDAttribute getOpenIdAttribute() {
		return openIdAttribute;
	}

}

