/**
 *
 */
package org.jrecruiter.scheduler;

import org.jrecruiter.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Task that periodically deletes jobs that are deemed "too old".
 *
 * @author Gunnar Hillert
 * @version $Id$
 *
 */
@Component
public class RemoveOldJobsTask {

    private @Autowired JobService jobService;

    /** Default Constructor. */
    public RemoveOldJobsTask() {
        super();
    }

    @Scheduled(cron = "0 26 1 * * *")
    public void removeOldJobs() {
        System.out.println("IT works!!");
        jobService.removeOldJobs(470);
    }


}
