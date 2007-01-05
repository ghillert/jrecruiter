package org.jrecruiter.dao;

import java.util.Properties;

import javax.mail.Session;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;

/**
 * Base class for Dao Test Cases.
 *
 * @author Gunnar Hillert
 */
public abstract class BaseDaoTest extends AbstractTransactionalDataSourceSpringContextTests {
	
	public static final Logger LOGGER = Logger.getLogger(BaseDaoTest.class);

    protected String[] getConfigLocations() {
        setAutowireMode(AUTOWIRE_BY_NAME);
        return new String[] {"/applicationContext*.xml"};
    }
    
    @BeforeGroups(value = { "daoIntegrationTest" }, groups = { "daoIntegrationTest" } )
    public void testNGsetUp() throws Exception {
    	
    	   String driver="org.postgresql.Driver";
    	   String url="jdbc:postgresql://10.10.0.102/postgres";
    	   String username="postgres";
    	   String password="hillert";
    	   
    	   SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
    	   
    	   DriverManagerDataSource ds = new DriverManagerDataSource();
    	   ds.setDriverClassName(driver);
    	   ds.setUrl(url);
    	   ds.setUsername(username);
    	   ds.setPassword(password);
    	   
    	   builder.bind("java:comp/env/jdbc/postgres", ds);

    	   Properties props = System.getProperties();

    	   props.put("mail.smtp.auth", "true");
    	   props.put("mail.smtp.host", "smtp.1and1.com");
    	   props.put("mail.smtp.password", "12345");
    	   props.put("mail.smtp.username", "u");

    	   Session session = Session.getDefaultInstance(props, null);

    	   builder.bind("java:comp/env/mail/session", session);

    	   super.setUp();
    }


    @AfterGroups(value = { "daoIntegrationTest" }, groups = { "daoIntegrationTest" } )
    public void testNGtearDown() throws Exception {
    super.tearDown();
    }
    
}
