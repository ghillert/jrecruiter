package org.jrecruiter.test;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

    protected @PersistenceContext(unitName="base") EntityManager entityManager;

    protected String[] getConfigLocations() {
        setAutowireMode(AUTOWIRE_BY_NAME);
        return new String[] {
        "/spring/applicationContext.xml",
        "/spring/applicationContext-acegi-base.xml",
        "/spring/applicationContext-authentication.xml",
        "/spring/applicationContext-authorization.xml",
        "/spring/applicationContext-jpa.xml",
        "/test-applicationContext-mail.xml",
        "/spring/applicationContext-resources.xml",
        "/spring/applicationContext-security.xml"

        };
    }

}
