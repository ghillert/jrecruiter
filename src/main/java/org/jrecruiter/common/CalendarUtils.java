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

    /** Return a basic ArrayList */
    public static Calendar getCalendarWithoutTime() {
    	
    	final Calendar calendar = Calendar.getInstance();
    	calendar.set(Calendar.MILLISECOND, 0);
    	calendar.set(Calendar.SECOND, 0);
    	calendar.set(Calendar.MINUTE, 0);
    	calendar.set(Calendar.HOUR_OF_DAY, 0);
        return calendar;
        
    }

}
