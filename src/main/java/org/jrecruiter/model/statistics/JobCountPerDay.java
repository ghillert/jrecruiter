/**
 *
 */
package org.jrecruiter.model.statistics;

import java.util.Calendar;

import org.apache.log4j.Logger;

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

    /** Initialize Logging. */
    private static final Logger LOGGER = Logger.getLogger(JobCountPerDay.class);

    public JobCountPerDay(Integer year, Integer month, Integer day, Long numberOfJobsPosted) {
        super();

        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setLenient(false);
        cal.set(year, month-1, day);

        this.jobDate = cal.getTime();

        this.numberOfJobsPosted = numberOfJobsPosted;

        LOGGER.info("year=" + year + "; month=" + month + "; day=" + day + "; numberOfJobsPosted=" + numberOfJobsPosted
            + "--> jobDate=" + this.jobDate + "; numberOfJobsPosted=" + this.numberOfJobsPosted);
    }


    public java.util.Date getJobDate() {
        return this.jobDate;
    }

    public Long getNumberOfJobsPosted() {
        return numberOfJobsPosted;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return String.format("%s[jobDate=%s, numberOfJobsPosted=%s]",
                super.toString(),
                this.jobDate,
                this.numberOfJobsPosted);
    }



}
