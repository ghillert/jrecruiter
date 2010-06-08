package org.jrecruiter.common;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public final class AcegiUtil {

    private AcegiUtil() {
        // private utility class constructor
    }

    private static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static boolean containsRole(final Collection<GrantedAuthority> grantedAuthorities, final String role) {
        for (final GrantedAuthority grantedAuthority : grantedAuthorities)
            if (grantedAuthority.getAuthority().equals(role)) {
                return true;
            }
        return false;
    }

    public static boolean hasRole(final String role) {
        final Authentication authentication = getAuthentication();

        if (authentication == null) {
            return false;
        }

        return containsRole(authentication.getAuthorities(), role);
    }

    public static boolean hasAnyRole(final String... roles) {

        final Authentication authentication = getAuthentication();

        if (authentication == null) {
            throw new AccessDeniedException("User is not authenticated");
        }

        final Collection<GrantedAuthority> authorities = authentication.getAuthorities();

        for (final String role : roles) {
            if (containsRole(authorities, role)) {
                return true;
            }
        }

        return false;
    }

    public static boolean hasAllRoles(final String... roles) {
        final Authentication authentication = getAuthentication();

        if (authentication == null) {
            throw new AccessDeniedException("User is not authenticated");
        }

        final Collection<GrantedAuthority> authorities = authentication.getAuthorities();

        for (final String role : roles) {
            if (!containsRole(authorities, role)) {
                return false;
            }
        }
        return true;
    }

    public static void ensureAllRoles(final String... roles) {
        if (!hasAllRoles(roles)) {
            throw new AccessDeniedException("User has not all of these roles: " + Arrays.toString(roles));
        }
    }

    public static void ensureAnyRole(final String... roles) {
        if (!hasAnyRole(roles)) {
            throw new AccessDeniedException("User has none of these roles: " + Arrays.toString(roles));
        }
    }

}

