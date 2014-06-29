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

import org.springframework.security.openid.OpenIDAttribute;

/**
 * Provides access to the JVM System Properties in a type-safe manner.
 *
 * @author Gunnar Hillert
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

