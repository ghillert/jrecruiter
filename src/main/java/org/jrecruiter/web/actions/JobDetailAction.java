package org.jrecruiter.web.actions;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.SessionAware;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.Statistic;
import org.jrecruiter.service.JobService;

/**
 * @author Gunnar Hillert
 * @version $Id:UserService.java 128 2007-07-27 03:55:54Z ghillert $
 */
public class JobDetailAction extends BaseAction implements SessionAware {


    /** serialVersionUID. */
    private static final long serialVersionUID = 369806210598096582L;

    private Long jobId;

    private Job job;

    private JobService jobService;

    private Map<String, Object>session;

    /**
     * Logger Declaration.
     */
    private final Log LOGGER = LogFactory.getLog(JobDetailAction.class);

    @SuppressWarnings("unchecked")
    public void setSession(Map session) {
        this.session = session;
    }

    /* (non-Javadoc)
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    @Override
    @SuppressWarnings("unchecked")
    public String execute() throws Exception {

        this.job = jobService.getJobForId(jobId);

        if (this.job==null){

            LOGGER.warn("Requested jobposting with id " + jobId + " was not found.");

        } else {

             Statistic statistics = job.getStatistic();

             if (statistics == null) {

                 statistics = new Statistic();
                 statistics.setJob(job);
                 statistics.setCounter(new Long(0));
                 statistics.setUniqueVisits(new Long(0));
                 job.setStatistic(statistics);
             }

             Set<Long> viewedPostings = new HashSet<Long>();

             if (session.get("visited") != null){

                 viewedPostings = (Set<Long>)session.get("visited");

                 if (viewedPostings.contains(jobId)){


                 } else {
                     long counter = statistics.getUniqueVisits().longValue() + 1 ;
                     statistics.setUniqueVisits(new Long (counter));
                     viewedPostings.add(jobId);
                 }

             } else {

                 long counter;

                 if (statistics.getUniqueVisits() != null)
                 {
                     counter = statistics.getUniqueVisits().longValue() + 1 ;
                 } else {
                     counter = 1;
                 }


                 statistics.setUniqueVisits(new Long (counter));

                 viewedPostings.add(jobId);
                 session.put("visited", viewedPostings);

             }

             Long counter = statistics.getCounter().longValue();
             counter ++;

             statistics.setCounter(new Long(counter));
             statistics.setLastAccess(new Date());
             //service.updateJobStatistic(statistics);

            }

    return SUCCESS;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }

}
