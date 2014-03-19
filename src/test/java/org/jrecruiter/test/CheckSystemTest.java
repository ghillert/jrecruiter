package org.jrecruiter.test;


import junit.framework.Assert;

import org.jrecruiter.spring.ApplicationConfiguration;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Base class for Dao Test Cases.
 *
 * @author Gunnar Hillert
 * @version $Id: BaseTest.java 455 2009-07-18 05:02:15Z ghillert $
 */
public class CheckSystemTest extends BaseServiceIntegrationTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(CheckSystemTest.class);

    private @Autowired ApplicationConfiguration applicationConfiguration;

    @Test
    public void VerifyJrecruiterHomeExistsTest() {

        Assert.assertNotNull(applicationConfiguration);

        LOGGER.info("AppHomePath: " + applicationConfiguration.getApplicationHome().getAppHomePath());
        LOGGER.info("SpringContextMode: " + applicationConfiguration.getSpringContextMode().name());
    }
}