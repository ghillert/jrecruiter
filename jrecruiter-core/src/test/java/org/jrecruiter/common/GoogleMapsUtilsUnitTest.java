/**
 *
 */
package org.jrecruiter.common;

import java.math.BigDecimal;
import java.net.URL;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This unit test tests the generation of GoogleMaps urls.
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
public class GoogleMapsUtilsUnitTest extends TestCase {

    private final static Logger LOGGER = LoggerFactory.getLogger(GoogleMapsUtilsUnitTest.class);

    public void testNewArrayListInstance() {

        URL url = GoogleMapsUtils.buildGoogleMapsStaticUrl(BigDecimal.TEN, BigDecimal.TEN, 10, "123456789");

        LOGGER.info("http://maps.google.com/staticmap?center=10%2C10&zoom=10&size=400x300&key=123456789&markers=10%2C10%2Cmidorange&sensor=false");
        LOGGER.info(url.toString());

        assertTrue("http://maps.google.com/staticmap?center=10%2C10&zoom=10&size=400x300&key=123456789&markers=10%2C10%2Cmidorange&sensor=false".equals(url.toString()));

    }

}
