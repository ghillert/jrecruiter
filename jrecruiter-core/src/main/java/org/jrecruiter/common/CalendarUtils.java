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

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.time.DateUtils;

/**
 * Provide common helper methods to work around the issues with Java Calendar and Date
 *
 * @author Gunnar Hillert
 * @version $Id$
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

    /** Returns the current date without time information.
     *
     * Parameters:
     * @param year the value used to set the YEAR calendar field in the calendar.
     * @param month the value used to set the MONTH calendar field in the calendar. Month value is 0-based. e.g., 0 for January.
     * @param dayOfMonth the value used to set the DAY_OF_MONTH calendar field in the calendar.
     * @param hourOfDay the value used to set the HOUR_OF_DAY calendar field in the calendar.
     * @param minute the value used to set the MINUTE calendar field in the calendar.
     * @param second the value used to set the SECOND calendar field in the calendar.
     *
     *
     */
    public static Calendar getCalendarInUTC(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second) {

        final Calendar calendar = Calendar.getInstance(DateUtils.UTC_TIME_ZONE);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);

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
        final Calendar calendar = Calendar.getInstance(DateUtils.UTC_TIME_ZONE);
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

        final GregorianCalendar gregorianCalendar = new GregorianCalendar(calendar.getTimeZone());
        gregorianCalendar.setTime(calendar.getTime());


        final DatatypeFactory dataTypeFactory;

        try {
            dataTypeFactory = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            throw new IllegalStateException(e.getMessage(),e);
        }

        final XMLGregorianCalendar xmlCalendar = dataTypeFactory.newXMLGregorianCalendar(gregorianCalendar);

        return xmlCalendar.toXMLFormat();
    }

}
