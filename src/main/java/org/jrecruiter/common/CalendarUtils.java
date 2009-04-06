/*
 *  http://www.jrecruiter.org
 *
 *  Disclaimer of Warranty.
 *
 *  Unless required by applicable law or agreed to in writing, Licensor provides
 *  the Work (and each Contributor provides its Contributions) on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied,
 *  including, without limitation, any warranties or conditions of TITLE,
 *  NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE. You are
 *  solely responsible for determining the appropriateness of using or
 *  redistributing the Work and assume any risks associated with Your exercise of
 *  permissions under this License.
 *
 */
package org.jrecruiter.common;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.XMLGregorianCalendar;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

/**
 * Provide common helper methods to work around the issues with Java Calendar and Date
 *
 * @author Gunnar Hillert
 * @version $Id: CollectionUtils.java 291 2008-11-13 02:20:08Z ghillert $
 */
public final class CalendarUtils {

    /** Private Constructor.
     *  Supress default constructor for non-instantiability */
    private CalendarUtils() {
        throw new AssertionError();
    }

    /** Returns the current date without time information. */
    public static Calendar getCalendarWithoutTime() {
    	
    	final Calendar calendar = Calendar.getInstance();
    	calendar.set(Calendar.MILLISECOND, 0);
    	calendar.set(Calendar.SECOND, 0);
    	calendar.set(Calendar.MINUTE, 0);
    	calendar.set(Calendar.HOUR_OF_DAY, 0);
        return calendar;
        
    }
    
    /**
     * Get a XML-date representation (ISO 8601) of the current date/time.
     * 
     * For more details @see http://www.w3.org/TR/xmlschema-2/#dateTime
     * 
     * @return XML-date as String
     */
    public static String getXmlFormatedDate() {
    	
    	final Calendar calendar = Calendar.getInstance();
    	return CalendarUtils.getXmlFormatedDate(calendar);
    }
    
    /**
     * Get a XML-date representation (ISO 8601) of the provided date.
     * 
     * For more details @see http://www.w3.org/TR/xmlschema-2/#dateTime
     * 
     * @return XML-date as String
     */
    public static String getXmlFormatedDate(final Date date) {
    	final Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	return CalendarUtils.getXmlFormatedDate(calendar);
    }
    
    /**
     * Get a XML-date representation (ISO 8601) of the provided calendar object.
     * 
     * For more details @see http://www.w3.org/TR/xmlschema-2/#dateTime
     * 
     * @return XML-date as String
     */
    public static String getXmlFormatedDate(final Calendar calendar) {
    	
    	if (calendar == null) {
    		throw new IllegalArgumentException("Calendar is a required parameter");
    	}
    	
    	final GregorianCalendar gregorianCalendar = new GregorianCalendar();
    	gregorianCalendar.setTime(calendar.getTime());
		final XMLGregorianCalendar xmlCalendar = new XMLGregorianCalendarImpl(gregorianCalendar);
		
		return xmlCalendar.toXMLFormat();
    }

}
