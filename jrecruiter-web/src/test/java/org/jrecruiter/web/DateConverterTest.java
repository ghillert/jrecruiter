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

import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Test the Struts 2 custom Date Converter
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
public class DateConverterTest extends TestCase {

    public void testConvertFromStringWithNull() throws Exception {

    	DateConverter dateConverter = new DateConverter();

    	Object ret = dateConverter.convertFromString(null, null, null);

    	Assert.assertNull(ret);
    }

    public void testConvertFromStringWithNull2() throws Exception {

    	DateConverter dateConverter = new DateConverter();

    	Object ret = dateConverter.convertFromString(null, new String[]{null}, null);

    	Assert.assertNull(ret);
    }

    public void testConvertFromStringWithWrongFormat() throws Exception {

    	DateConverter dateConverter = new DateConverter();

    	final String dateAsString = "2008-1-30";

    	Object ret = dateConverter.convertFromString(null, new String[]{dateAsString}, null);
    	Assert.assertNull(ret);
    }

    public void testConvertFromString() throws Exception {
    	DateConverter dateConverter = new DateConverter();

    	final String dateAsString = "01/30/2008";

    	Object ret = dateConverter.convertFromString(null, new String[]{dateAsString}, null);

    	Assert.assertNotNull(ret);
    	Assert.assertTrue(ret instanceof Date);

    	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    	Date expectedDate = formatter.parse(dateAsString);
    	Assert.assertTrue(expectedDate.equals(ret));
    }

    public void testConvertToString() throws Exception {
    	DateConverter dateConverter = new DateConverter();
    	final String dateAsString = "01/30/2008";
    	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    	Date expectedDate = formatter.parse(dateAsString);

    	String ret = dateConverter.convertToString(null, expectedDate);

    	Assert.assertNotNull(ret);
    	Assert.assertTrue(dateAsString.equals(ret));
    }
}

