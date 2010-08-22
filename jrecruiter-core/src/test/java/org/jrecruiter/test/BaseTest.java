package org.jrecruiter.test;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * Base class for Dao Test Cases.
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
@ContextConfiguration(
        locations={
                "classpath:org/jrecruiter/server/spring/applicationContext-core-basic.xml",
                "classpath:org/jrecruiter/server/spring/DemoContextConfiguration.xml"
                 })
public abstract class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {

    protected @PersistenceContext(unitName="base") EntityManager entityManager;
}
