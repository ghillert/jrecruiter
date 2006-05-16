/**
 * 
 */
package org.jrecruiter.dao;
import java.util.List;

import org.apache.log4j.Logger;
import org.jrecruiter.model.User;
import org.jrecruiter.model.UserRole;
import org.jrecruiter.persistent.dao.UserDAO;
import org.jrecruiter.service.impl.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Configuration;
import org.testng.annotations.Test;

/**
 * @author Gunnar Hillert
 *
 */
public class TestUserDAO {

	ApplicationContext ctx;
	
	/**
     *   Initialize Logging.
     */
    public static final Logger LOGGER = Logger.getLogger(TestUserDAO.class);

	  @Configuration(beforeTestClass = true)
	  public void configure() {
	    System.out.println("Initialization");
	    ctx = new ClassPathXmlApplicationContext("testApplicationContext.xml");
	  }

	  @Test(groups = {"exec-group"})
	    public void getAllUsersTest() {
	    System.out.println("exec");
	    UserDAO userDAO = (UserDAO) ctx.getBean("userDAO");
	    List < User > users = userDAO.getAllUsers();
	    
	    for (User user : users) {
	    	
	    	LOGGER.info(user);
	    	
	    }
	  }

	@Test(groups = {"exec-group"})
	    public void getSingleUserTest() {
	    System.out.println("exec");
	    UserDAO userDAO = (UserDAO) ctx.getBean("userDAO");
	    
	    User user = userDAO.getUser("admin");
	   
	    	LOGGER.info(user);

	  }
	  
	  @Test(groups = {"exec-group"})
	    public void getAllRolesTest() {
	    System.out.println("exec");
	    UserDAO userDAO = (UserDAO) ctx.getBean("userDAO");
	    
	    List < UserRole > roles = userDAO.getAllRoles();
	    
	    for (UserRole role : roles) {
	    	
	    	LOGGER.info(role);
	    	
	    }
	  }

	  @Test(groups = {"exec-group"})
	    public void addNewUserTest() {
	    System.out.println("exec");
	    UserDAO userDAO = (UserDAO) ctx.getBean("userDAO");
	    
	    User user = new User();
	    user.setUsername("demo");
	    user.setEmail("demo@demo.com");
	    user.setFirstName("Demo First Name");
	    user.setLastName("Demo Last Name");
	    user.setPassword("demo");
	    user.setPhone("123456");
	    
	    userDAO.addUser(user, "manager");
	    

	  }
	  
	  @Test(groups = {"exec-group"})
	    public void deleteUserTest() {
	    System.out.println("exec");
	    UserDAO userDAO = (UserDAO) ctx.getBean("userDAO");
	    
	    String user[] = new String[1];
	    user[0]="demo";
	    userDAO.deleteUser(user);
	    

	  }
}
