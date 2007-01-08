package org.jrecruiter.dao;
  
import org.apache.log4j.Logger;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * Base class for Dao Test Cases.
 *
 * @author Gunnar Hillert
 */
public abstract class BaseDaoTNG extends AbstractTransactionalDataSourceSpringContextTests {
	
	public static final Logger LOGGER = Logger.getLogger(BaseDaoTNG.class);

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
    
    @BeforeClass
    public void NGsetUp() throws Exception{
    super.setUp();
    }

    @AfterClass
    public void NGtearDown() throws Exception{
    super.tearDown();
    }

    
}
