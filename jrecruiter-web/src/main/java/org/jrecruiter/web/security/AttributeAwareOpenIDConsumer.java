package org.jrecruiter.web.security;

import java.util.Arrays;

import org.openid4java.consumer.ConsumerException;
import org.springframework.security.openid.OpenID4JavaConsumer;

/**
 * Custom OpenID4JavaConsumer to retrieve additional OpenID attributes.
 * 
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
public class AttributeAwareOpenIDConsumer extends OpenID4JavaConsumer {
	 
	  public AttributeAwareOpenIDConsumer() throws ConsumerException {
	     super(Arrays.asList(UsedOpenIdAttribute.FIRST_NAME.getOpenIdAttribute(), 
	    		             UsedOpenIdAttribute.LAST_NAME.getOpenIdAttribute(), 
	    		             UsedOpenIdAttribute.EMAIL.getOpenIdAttribute(),
	    		             UsedOpenIdAttribute.AX_FIRST_NAME.getOpenIdAttribute(),
	    		             UsedOpenIdAttribute.AX_LAST_NAME.getOpenIdAttribute(),
	    		             UsedOpenIdAttribute.NAME_PERSON.getOpenIdAttribute()));
	  }

}
