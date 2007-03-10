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
package org.jrecruiter.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Gunnar Hillert
 * @version $Id: Util.java 24 2006-05-18 03:09:15Z ghillert $
 */
public class WebUtil {

    /**
     * Constructor.
     */
    public WebUtil() {
        super();
    }

    public static String stripTags(String text, String tags){


            tags = " " + tags + " ";
            final Pattern p = Pattern.compile("</?(.*?)(s.*?)?>");
            final Matcher m = p.matcher(text);

            final StringBuffer out = new StringBuffer();
            int lastPos = 0;
            while (m.find()) {
                final String tag = m.group(1);
                if (tags.indexOf(tag) == -1) {
                    out.append(text.substring(lastPos, m.start()))
                       .append(" ");
                } else {
                    out.append(text.substring(lastPos, m.end()));
                }
                lastPos = m.end();
            }
            if (lastPos > 0) {
                out.append(text.substring(lastPos));
                return out.toString().trim();
            } else {
                return text;
            }
    }


}
