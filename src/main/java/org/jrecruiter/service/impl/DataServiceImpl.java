/*
*	http://www.jrecruiter.org
*
*	Disclaimer of Warranty.
*
*	Unless required by applicable law or agreed to in writing, Licensor provides
*	the Work (and each Contributor provides its Contributions) on an "AS IS" BASIS,
*	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied,
*	including, without limitation, any warranties or conditions of TITLE,
*	NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE. You are
*	solely responsible for determining the appropriateness of using or
*	redistributing the Work and assume any risks associated with Your exercise of
*	permissions under this License.
*
*/
package org.jrecruiter.service.impl;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.text.MessageFormat;
import java.util.Map;

import javax.imageio.ImageIO;

import org.jrecruiter.common.CollectionUtils;
import org.jrecruiter.web.ApiKeysHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
@Service("dataService")
@Transactional
public class DataServiceImpl implements org.jrecruiter.service.DataService {

    /**
     * Logger Declaration.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(DataServiceImpl.class);

    private @Autowired ApiKeysHolder apiKeysHolder;

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
    public Image getGoogleMapImage(final BigDecimal latitude,
                                   final BigDecimal longitude,
                                   final Integer zoomLevel) {

        final Integer width  = 300;
        final Integer height = 300;
        final String  color  = "orange";
        final String  letter = "J";

         final String urlString       = "http://maps.google.com/staticmap?center={0},{1}&zoom={2}&size={3}x{4}&key={5}&markers={0},{1},{6}{7}";
         MessageFormat mf = new MessageFormat(urlString);

         final Object[] urlParams = {String.valueOf(latitude),
                                      String.valueOf(longitude),
                                      zoomLevel, width, height,
                                      apiKeysHolder.getGoogleMapsKey(), color, letter};

        if (longitude == null) {
            throw new IllegalArgumentException("Longitude cannot be null.");
        }

        if (latitude == null) {
            throw new IllegalArgumentException("Latitude cannot be null.");
        }

        if (zoomLevel == null) {
            throw new IllegalArgumentException("ZoomLevel cannot be null.");
        }

        final URL url;

        try {
            url = new URL(mf.format(urlParams));
        } catch (MalformedURLException e) {
            throw new IllegalStateException(e);
        }

        BufferedImage img;
        try {
            URLConnection conn = url.openConnection ();
            img = ImageIO.read(conn.getInputStream());
        } catch (UnknownHostException e) {
            LOGGER.error("Google static MAPS web service is not reachable (UnknownHostException).", e );
            img = new BufferedImage(width, 100, BufferedImage.TYPE_INT_RGB);

            final Graphics2D graphics = img.createGraphics();

            final Map<Object, Object> renderingHints = CollectionUtils.getHashMap();
            renderingHints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            graphics.addRenderingHints(renderingHints);
            graphics.setBackground(Color.WHITE);
            graphics.setColor(Color.GRAY);
            graphics.clearRect(0, 0, width, 100);

            graphics.drawString("Not Available", 30, 30);

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        return img;
    }


}
