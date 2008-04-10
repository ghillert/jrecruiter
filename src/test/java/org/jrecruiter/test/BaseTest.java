package org.jrecruiter.test;


import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 * Base class for Dao Test Cases.
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
public abstract class BaseTest extends AbstractTransactionalDataSourceSpringContextTests {

	public static final Logger LOGGER = Logger.getLogger(BaseTest.class);

	protected SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    public void flushSession() {
    	sessionFactory.getCurrentSession().flush();
    }

	protected String[] getConfigLocations() {
        setAutowireMode(AUTOWIRE_BY_NAME);
        return new String[] {
        "/spring/applicationContext-acegi-base.xml",
        "/spring/applicationContext-authentication.xml",
        "/spring/applicationContext-authorization.xml",
        "/spring/applicationContext-hibernate.xml",
        "/spring/applicationContext.xml",
        "/test-applicationContext-datasource.xml",
        "/spring/applicationContext-security.xml",
        "/spring/applicationContext-dao.xml",
        "/test-applicationContext-mail.xml"};
    }

}
