package org.jrecruiter.common;

import java.util.Iterator;
import java.util.List;

import de.rrze.idmone.utils.jpwgen.BlankRemover;
import de.rrze.idmone.utils.jpwgen.PwGenerator;

public final class PasswordGenerator {

    private PasswordGenerator() {
        // private utility class constructor
    }

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

