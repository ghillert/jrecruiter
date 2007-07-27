package org.jrecruiter.test;

  
import org.apache.log4j.Logger;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 * Base class for Dao Test Cases.
 *
 * @author Gunnar Hillert
 */
public abstract class BaseTest extends AbstractTransactionalDataSourceSpringContextTests {
	
	public static final Logger LOGGER = Logger.getLogger(BaseTest.class);

    protected String[] getConfigLocations() {
        setAutowireMode(AUTOWIRE_BY_NAME);
        return new String[] {
        "/applicationContext-acegi-base.xml",
        "/applicationContext-acegi-security.xml",
        "/applicationContext-authentication.xml",
        "/applicationContext-authorization.xml",
        "/applicationContext-hibernate.xml",
        "/applicationContext.xml",
        "/test-applicationContext-datasource.xml"};
    }
        
}
