package org.jrecruiter.web.interceptor;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks an Action method to restore Action Messages from the
 * session
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(METHOD)
public @interface RetrieveMessages {
}