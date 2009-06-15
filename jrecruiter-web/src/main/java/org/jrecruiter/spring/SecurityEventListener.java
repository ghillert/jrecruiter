package org.jrecruiter.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.event.authentication.AbstractAuthenticationEvent;
import org.springframework.security.event.authentication.AbstractAuthenticationFailureEvent;
import org.springframework.security.event.authentication.AuthenticationSuccessEvent;
import org.springframework.security.event.authentication.InteractiveAuthenticationSuccessEvent;

public class SecurityEventListener implements
                                    ApplicationListener < AbstractAuthenticationEvent > {

    /**
     *   Initialize Logging.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(SecurityEventListener.class);

    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent event) {

        if (event instanceof AuthenticationSuccessEvent) {

            final AuthenticationSuccessEvent successEvent = (AuthenticationSuccessEvent) event;
            LOGGER.info("Successful Authentication of User: " + successEvent.getAuthentication().getName());

        } else if (event instanceof InteractiveAuthenticationSuccessEvent) {

            final InteractiveAuthenticationSuccessEvent successEvent = (InteractiveAuthenticationSuccessEvent) event;
            LOGGER.info("Successful Interactive Authentication of User: " + successEvent.getAuthentication().getName());

        } else if (event instanceof AbstractAuthenticationFailureEvent) {

            final AbstractAuthenticationFailureEvent failureEvent = (AbstractAuthenticationFailureEvent) event;
            LOGGER.warn("Authentication Failure for User '"
                    + failureEvent.getAuthentication().getPrincipal() + "' "
                    + failureEvent.getException().getMessage(), failureEvent.getException());

        } else {

            /*
             * Since we really don't care about other events, we log it for
             *  now, but because of that we don't throw an explicit exception.
             */
            LOGGER.error("Unhandled AuthenticationEvent (" + event.getClass().getName() + ") for user '"
                    + event.getAuthentication().getPrincipal() + "':"
                    + event.toString());
        }


    }

}
