/**
 *
 */
package org.jrecruiter.dao;
import org.acegisecurity.providers.dao.DaoAuthenticationProvider;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.Test;

/**
 * @author Gunnar Hillert
 *
 */
public class DaoAuthenticationProviderTNG extends BaseDaoTNG {

    ApplicationContext ctx;

    DaoAuthenticationProvider daoAuthenticationProvider;
    
	public DaoAuthenticationProvider getDaoAuthenticationProvider() {
		return daoAuthenticationProvider;
	}

	public void setDaoAuthenticationProvider(
			DaoAuthenticationProvider daoAuthenticationProvider) {
		this.daoAuthenticationProvider = daoAuthenticationProvider;
	}
	
    /**
     *   Initialize Logging.
     */
    public static final Logger LOGGER = Logger.getLogger(DaoAuthenticationProviderTNG.class);

      @Test(groups = {"daoIntegrationTest"})
        public void getUser() {

        UserDetails user = daoAuthenticationProvider.getUserDetailsService().loadUserByUsername("6744");

        LOGGER.info(user);

      }



}
