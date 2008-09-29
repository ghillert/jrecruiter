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
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import org.jrecruiter.test.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Gunnar Hillert
 * @version $Id: JobServiceTest.java 189 2008-04-21 02:50:07Z ghillert $
 */
public class GoogleMapsStaticTest extends BaseTest {

    private @Autowired DemoService  demoService;

    public void testStaticMapTest() throws Exception {

        sendGetRequest();

    }


    public static void sendGetRequest()
    {
    String result = null;

    // Send a GET request to the servlet
    try
    {
    // Construct data
    StringBuffer data = new StringBuffer();

    URL url = new URL("http://maps.google.com/staticmap?center=40.714728,-73.998672&zoom=12&size=400x400&key=key=ABQIAAAAaRkHCsiKIvvB_UEon-SKORQ7EYV2ourIdp48QYZszNEA7gcaFhQRuKqKuYEC9ss4BL5bATDTf3IeLg");
    URLConnection conn = url.openConnection ();

    BufferedImage img = ImageIO.read(conn.getInputStream());
    
    ImageIO.write(img, "png", new File("/temp/1.png"));


    } catch (Exception e)
    {
    e.printStackTrace();
    }
    }


}