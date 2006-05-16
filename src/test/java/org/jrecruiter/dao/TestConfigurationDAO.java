/**
 * 
 */
package org.jrecruiter.dao;
import java.util.List;

import org.apache.log4j.Logger;
import org.jrecruiter.model.Job;
import org.jrecruiter.persistent.dao.SettingsDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Configuration;
import org.testng.annotations.Test;

/**
 * @author Gunnar Hillert
 *
 */
public class TestConfigurationDAO {

	ApplicationContext ctx;
	
	/**
     *   Initialize Logging.
     */
    public static final Logger LOGGER = Logger.getLogger(TestConfigurationDAO.class);

	  @Configuration(beforeTestClass = true)
	  public void configure() {
	    System.out.println("Initialization");
	    ctx = new ClassPathXmlApplicationContext("testApplicationContext.xml");
	  }

	  @Test(groups = {"exec-group"})
	    public void getAllJobs() {
	    System.out.println("exec");
	    SettingsDAO settingsDAO = (SettingsDAO) ctx.getBean("settingsDAO");
	    List < org.jrecruiter.model.Configuration > conf = settingsDAO.getAllConfigurations();
	    
	    for (org.jrecruiter.model.Configuration setting : conf) {
	    	
	    	LOGGER.info(setting);
	    	
	    }
	  }

}
