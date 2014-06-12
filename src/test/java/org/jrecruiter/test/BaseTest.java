package org.jrecruiter.test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jrecruiter.web.config.DefaultApplicationContextInitializer;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Base class for Dao Test Cases.
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(initializers=DefaultApplicationContextInitializer.class,
					locations={ "classpath:org/jrecruiter/core/spring/applicationContext-core-basic.xml"})
@ActiveProfiles({"demo"})
@Transactional
public abstract class BaseTest {

	protected @PersistenceContext(unitName="base") EntityManager entityManager;
}
