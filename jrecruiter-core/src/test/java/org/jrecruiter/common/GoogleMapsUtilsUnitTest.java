/**
 *
 */
package org.jrecruiter.common;

import java.math.BigDecimal;
import java.net.URI;

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

        URI uri = GoogleMapsUtils.buildGoogleMapsStaticUrl(BigDecimal.TEN, BigDecimal.TEN, 10);

        LOGGER.info("http://maps.google.com/staticmap?center=10%2C10&zoom=10&size=400x300&markers=10%2C10%2Cmidorange&sensor=false");

        String uriAsString = uri.toString();

        LOGGER.info(uriAsString);

        assertEquals("http://maps.google.com/staticmap?center=10%2C10&zoom=10&size=400x300&markers=10%2C10%2Cmidorange&sensor=false", uriAsString);

    }

}
