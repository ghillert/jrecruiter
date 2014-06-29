/*
 * Copyright 2006-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jrecruiter.web.security;

import java.util.Arrays;

import org.openid4java.consumer.ConsumerException;
import org.springframework.security.openid.OpenID4JavaConsumer;

/**
 * Custom OpenID4JavaConsumer to retrieve additional OpenID attributes.
 *
 * @author Gunnar Hillert
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
