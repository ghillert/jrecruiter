package org.jrecruiter.common;

import java.util.Iterator;
import java.util.List;

import de.rrze.idmone.utils.jpwgen.BlankRemover;
import de.rrze.idmone.utils.jpwgen.PwGenerator;

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
     * Generate a temporary password. Using the jpwgen parameters:
     *
     * "-N 1 -M 100 -B -n -c -y -s 10 -o"
     *
     * @return The generated password.
     */
    public static String generatePassword() {
        String flags = "-N 1 -M 100 -B -n -c -y -s 10 -o ";
        flags = BlankRemover.itrim(flags);
        final String[] ar = flags.split(" ");
        final PwGenerator generator = new PwGenerator();
        final List <String> passwords = generator.process(ar);

        String password = null;

        for (Iterator<String> iterator = passwords.iterator(); iterator.hasNext();) {
            password = iterator.next();
        }

        return password;
    }


}

