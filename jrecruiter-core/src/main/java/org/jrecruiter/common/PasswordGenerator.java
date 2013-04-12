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

