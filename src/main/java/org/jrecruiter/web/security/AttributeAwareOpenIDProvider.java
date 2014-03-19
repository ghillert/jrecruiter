    package org.jrecruiter.web.security;

import org.apache.commons.lang.StringUtils;
import org.jrecruiter.model.User;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.AuthenticationCancelledException;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationProvider;
import org.springframework.security.openid.OpenIDAuthenticationStatus;
import org.springframework.security.openid.OpenIDAuthenticationToken;

/**
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
public class AttributeAwareOpenIDProvider extends OpenIDAuthenticationProvider {

      private UserDetailsService userDetailsService;
      private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AttributeAwareOpenIDProvider.class);

      public AttributeAwareOpenIDProvider(final UserDetailsService userDetailsService) {
          this.userDetailsService = userDetailsService;
          super.setUserDetailsService(userDetailsService);
      }

        /**
         * {@inheritDoc}
         * @see org.springframework.security.providers.openid.OpenIDAuthenticationProvider#authenticate(
         * 	org.springframework.security.Authentication)
         */
        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {

            if (!supports(authentication.getClass())) {
                return null;
            }

            if (authentication instanceof OpenIDAuthenticationToken) {
                OpenIDAuthenticationToken response = (OpenIDAuthenticationToken) authentication;
                OpenIDAuthenticationStatus status = response.getStatus();

                // handle the various possibilites
                if (status == OpenIDAuthenticationStatus.SUCCESS) {
                    // Lookup user details
                        final UserDetails userDetails;
                    try {
                        userDetails = this.userDetailsService.loadUserByUsername(response.getIdentityUrl());
                    } catch (final UsernameNotFoundException e) {

                            LOGGER.error("OpenID authentication successful but but no account exists.");

                            final User user = new User();
                            user.setUsername(response.getIdentityUrl());

                            for (OpenIDAttribute attribute : response.getAttributes()) {

                                LOGGER.debug("OpenIDAttribute: " + attribute.getType() + "; " + attribute.getName() + "; " + attribute.getValues());

                                if (UsedOpenIdAttribute.AX_FIRST_NAME.getOpenIdAttribute().getName().equals(attribute.getName())) {
                                    user.setFirstName(attribute.getValues().get(0));
                                } else if (UsedOpenIdAttribute.AX_LAST_NAME.getOpenIdAttribute().getName().equals(attribute.getName())) {
                                    user.setLastName(attribute.getValues().get(0));
                                } else if (UsedOpenIdAttribute.EMAIL.getOpenIdAttribute().getName().equals(attribute.getName())) {
                                    user.setEmail(attribute.getValues().get(0));
                                } else if (UsedOpenIdAttribute.FIRST_NAME.getOpenIdAttribute().getName().equals(attribute.getName())
                                            && user.getFirstName() == null) {
                                    user.setFirstName(attribute.getValues().get(0));
                                } else if (UsedOpenIdAttribute.LAST_NAME.getOpenIdAttribute().getName().equals(attribute.getName())
                                            && user.getLastName() == null) {
                                    user.setLastName(attribute.getValues().get(0));
                                }

                            }

                            if (StringUtils.isBlank(user.getFirstName()) && StringUtils.isBlank(user.getLastName())) {
                                for (OpenIDAttribute attribute : response.getAttributes()) {
                                    if (UsedOpenIdAttribute.NAME_PERSON.getOpenIdAttribute().getName().equals(attribute.getName())) {
                                        user.setFirstName(attribute.getValues().get(0));
                                    }
                                }
                            }

                            throw new AuthenticationSucessButMissingRegistrationException("User is authenticated via OpenID but no account exists, yet.", user);

                    }

                    return new OpenIDAuthenticationToken(userDetails, userDetails.getAuthorities(), response.getIdentityUrl(), response.getAttributes());
                }

                if (status == OpenIDAuthenticationStatus.CANCELLED) {
                    throw new AuthenticationCancelledException("Log in cancelled");
                }

                if (status == OpenIDAuthenticationStatus.ERROR) {
                    throw new AuthenticationServiceException("Error message from server: $response.message");
                }

                if (status == OpenIDAuthenticationStatus.FAILURE) {
                    throw new BadCredentialsException("Log in failed - identity could not be verified");
                }

                if (status == OpenIDAuthenticationStatus.SETUP_NEEDED) {
                    throw new AuthenticationServiceException(
                            "The server responded setup was needed, which shouldn't happen");
                }

                throw new AuthenticationServiceException("Unrecognized return value $status");
            }

            return null;
        }

}