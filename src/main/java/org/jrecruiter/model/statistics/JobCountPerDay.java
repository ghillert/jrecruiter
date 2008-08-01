/**
 *
 */
package org.jrecruiter.model.statistics;

import java.util.Calendar;

/**
 * Used for portraying statistical information regarding how many jobs have been
 * posted for that day.
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
public class JobCountPerDay {

    private java.util.Date jobDate;
    private Long numberOfJobsPosted;

    public JobCountPerDay(Integer year, Integer month, Integer day, Long numberOfJobsPosted) {
        super();

        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(year, month, day);

        this.jobDate = cal.getTime();

        this.numberOfJobsPosted = numberOfJobsPosted;
    }


    public java.util.Date getJobDate() {
        return this.jobDate;
    }

    public Long getNumberOfJobsPosted() {
        return numberOfJobsPosted;
    }

}
