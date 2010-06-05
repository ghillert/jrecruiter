/**
 *
 */
package org.jrecruiter.common;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This unit test tests the generation of GoogleMaps urls.
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
public class SystemInformationUtilsUnitTest extends TestCase {

    private final static Logger LOGGER = LoggerFactory.getLogger(SystemInformationUtilsUnitTest.class);

    public void testNewArrayListInstance() {

        Apphome apphome = SystemInformationUtils.retrieveBasicSystemInformation();

        LOGGER.info("Apphome: " + apphome.getAppHomePath());

        assertNotNull(apphome);

    }

    public void testGetAllEnvironmentVariables() {

        final String environmentVariablesAsString = SystemInformationUtils.getAllEnvironmentVariables();
        assertNotNull(environmentVariablesAsString);

    }

    public void testGetAllSystemProperties() {

        final String systemPropertiesAsString = SystemInformationUtils.getAllSystemProperties();
        assertNotNull(systemPropertiesAsString);

    }


}
