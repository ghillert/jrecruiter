package org.jrecruiter.test;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

/**
 * Base class for Dao Test Cases.
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
public abstract class BaseTest extends AbstractTransactionalDataSourceSpringContextTests {

    private final static Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    protected @PersistenceContext(unitName="base") EntityManager entityManager;

    protected String[] getConfigLocations() {
        setAutowireMode(AUTOWIRE_BY_NAME);
        return new String[] {
        "/spring/applicationContext.xml",
        "/spring/applicationContext-jpa.xml",
        "/test-applicationContext-mail.xml",
        "/spring/applicationContext-resources.xml",
        "/spring/applicationContext-security.xml"
        };
    }

}
