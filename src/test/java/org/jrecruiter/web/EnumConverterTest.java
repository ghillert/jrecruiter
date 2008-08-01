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

import junit.framework.Assert;
import junit.framework.TestCase;

import org.jrecruiter.common.Constants.JobStatus;

/**
 * Test the Struts 2 custom Enum Converter
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
public class EnumConverterTest extends TestCase {

    public void testConvertFromStringNull() throws Exception {
    	EnumConverter enumConverter = new EnumConverter();

    	Object ret = enumConverter.convertFromString(null, new String[]{"ACTIVE"}, String.class);

    	Assert.assertNull(ret);
    }

    public void testConvertFromString() throws Exception {
    	EnumConverter enumConverter = new EnumConverter();

    	Object ret = enumConverter.convertFromString(null, new String[]{"ACTIVE"}, JobStatus.class);

    	Assert.assertNotNull(ret);
    	Assert.assertTrue(ret instanceof JobStatus);
    	Assert.assertTrue(JobStatus.ACTIVE.equals(ret));
    }

    public void testConvertToString() throws Exception {
    	EnumConverter enumConverter = new EnumConverter();

    	String ret = enumConverter.convertToString(null, JobStatus.ACTIVE);

    	Assert.assertNotNull(ret);
    	Assert.assertTrue("ACTIVE".equals(ret));
    }
}

