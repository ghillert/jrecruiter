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
package org.jrecruiter.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import org.jrecruiter.common.GoogleMapsUtils;
import org.jrecruiter.test.BaseTest;
import org.jrecruiter.web.ApiKeysHolder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
public class GoogleMapsStaticTest extends BaseTest {

    private @Autowired ApiKeysHolder apiKeysHolder;

    @Test
    public void testStaticMapTest() throws Exception {
        sendGetRequest();
    }


    private void sendGetRequest() {

    // Send a GET request to the servlet
    try {

        final URL url = GoogleMapsUtils.buildGoogleMapsStaticUrl(BigDecimal.TEN, BigDecimal.TEN, 10, this.apiKeysHolder.getGoogleMapsKey());
        URLConnection conn = url.openConnection ();

        BufferedImage img = ImageIO.read(conn.getInputStream());

        ImageIO.write(img, "png", new File("/temp/1.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}