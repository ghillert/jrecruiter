/**
 * 
 */
package org.jrecruiter.dao;
import java.util.List;

import org.acegisecurity.providers.dao.DaoAuthenticationProvider;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.log4j.Logger;
import org.jrecruiter.model.UserRole;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Configuration;
import org.testng.annotations.Test;

/**
 * @author Gunnar Hillert
 *
 */
public class daoAuthenticationProviderTest {

	ApplicationContext ctx;
	
	/**
     *   Initialize Logging.
     */
    public static final Logger LOGGER = Logger.getLogger(daoAuthenticationProviderTest.class);

	  @Configuration(beforeTestClass = true)
	  public void configure() {
	    System.out.println("Initialization");
	    ctx = new ClassPathXmlApplicationContext("testApplicationContext.xml");
	  }
	  
	  @Test(groups = {"exec-group"})
	    public void getUser() {
	    System.out.println("exec");
	    DaoAuthenticationProvider daoAuthenticationProvider = (DaoAuthenticationProvider) ctx.getBean("daoAuthenticationProvider");
	    UserDetails user = daoAuthenticationProvider.getUserDetailsService().loadUserByUsername("6744");
	    
	    
	    LOGGER.info(user);

	  }

}
