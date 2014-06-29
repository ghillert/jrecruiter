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
package org.jrecruiter.common;

import de.rrze.jpwgen.IPwGenConstants;
import de.rrze.jpwgen.impl.PwGenerator;

/**
 * Helper class to generate a temporary password. Uses jpwgen
 * @see http://jpwgen.berlios.de/
 *
 * @author Gunnar Hillert
 *
 */
public final class PasswordGenerator {

	private PasswordGenerator() {
		// private utility class constructor
	}

	/**
	 * Generate a temporary password.
	 *
	 * @return The generated password.
	 */
	public static String generatePassword() {
		//String flags = "-N 1 -M 100 -B -n -c -y -s 10 -o ";'
		final int flags = IPwGenConstants.DEFAULT_FLAGS;
		final String password = PwGenerator.generatePassword(10, flags, 100, null);
		return password;
	}

}

