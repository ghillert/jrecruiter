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
 * @version $Id: BaseTest.java 598 2010-08-22 20:18:58Z ghillert $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ActiveProfiles({"demo"})
@ContextConfiguration(initializers=DefaultApplicationContextInitializer.class,
locations={
		"classpath:org/jrecruiter/core/spring/applicationContext-core-basic.xml",
		"classpath:org/jrecruiter/core/spring/applicationContext-core-services.xml",
		"classpath:org/jrecruiter/core/spring/test-applicationContext-mail.xml"
		 })
public abstract class BaseServiceIntegrationTest {

	protected @PersistenceContext(unitName="base") EntityManager entityManager;
}
