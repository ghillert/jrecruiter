/**
 *
 */
package org.jrecruiter.dao;

import org.acegisecurity.providers.dao.DaoAuthenticationProvider;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.log4j.Logger;
import org.jrecruiter.test.BaseTest;

/**
 * @author Gunnar Hillert
 *
 */
public class DaoAuthenticationProviderTest extends BaseTest {

    DaoAuthenticationProvider daoAuthenticationProvider;

	public void setDaoAuthenticationProvider(
			DaoAuthenticationProvider daoAuthenticationProvider) {
		this.daoAuthenticationProvider = daoAuthenticationProvider;
	}
	
    /**
     *   Initialize Logging.
     */
    public static final Logger LOGGER = Logger.getLogger(DaoAuthenticationProviderTest.class);

        public void getUserTest() {

        UserDetails user = daoAuthenticationProvider.getUserDetailsService().loadUserByUsername("6744");

        LOGGER.info(user);

      }



}
