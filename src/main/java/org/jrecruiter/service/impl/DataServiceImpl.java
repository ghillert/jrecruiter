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

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
@Service("dataService")
public class DataServiceImpl implements org.jrecruiter.service.DataService {

    public Image getGoogleMapImage(final BigDecimal longitude,
                                   final BigDecimal latitude,
                                   final Integer zoomLevel) {

        final Integer width  = 300;
        final Integer height = 300;
        final String  color  = "orange";
        final String  letter = "J";
        final String mapKey  = "ABQIAAAAaRkHCsiKIvvB_UEon-SKORQ7EYV2ourIdp48QYZszNEA7gcaFhQRuKqKuYEC9ss4BL5bATDTf3IeLg";

         final String urlString       = "http://maps.google.com/staticmap?center={0},{1}&zoom={2}&size={3}x{4}&key={5}&markers={0},{1},{6}{7}";
         MessageFormat mf = new MessageFormat(urlString);

         final Object[] urlParams = {String.valueOf(longitude), String.valueOf(latitude), zoomLevel, width, height, mapKey, color, letter};

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

        final BufferedImage img;
        try {
            URLConnection conn = url.openConnection ();
            img = ImageIO.read(conn.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return img;
    }


}
