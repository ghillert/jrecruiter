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
package org.jrecruiter.dao;

import java.util.Date;
import java.util.List;

import org.jrecruiter.model.statistics.JobCountPerDay;

/**
 * Interface for any jobCountPerDay    	final Calendar today     = CalendarUtils.getCalendarWithoutTime();
    	final Calendar yesterday = CalendarUtils.getCalendarWithoutTime();
    	yesterday.add(Calendar.DAY_OF_YEAR, -1);-related persistence calls.
 *
 * @author Gunnar Hillert
 * @version @version $Id$
 */
public interface JobCountPerDayDao extends GenericDao < JobCountPerDay, Long >{

    /**
     * Method for getting a single job count per day object.
     *
     * @param day The day for which the object shall be retrieved
     *
     * @return JobCountPerDay object
     */
    JobCountPerDay getByDate(Date day);

    /**
     *
     * @param fromDate
     * @param toDate
     * @return
     */
    List<JobCountPerDay> getJobCountPerDayAndPeriod(Date fromDate, Date toDate);

    /**
     * 
     * @return
     */
	List<JobCountPerDay> getLatestTwoJobCounts();

}
