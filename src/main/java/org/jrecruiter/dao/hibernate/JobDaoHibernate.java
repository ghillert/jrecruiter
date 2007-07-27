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
package org.jrecruiter.dao.hibernate;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.jrecruiter.Constants.Roles;
import org.jrecruiter.Constants.StatsMode;
import org.jrecruiter.dao.JobDao;
import org.jrecruiter.dao.UserDao;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.User;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * This DAO provides job-related database methods.
 *
 * @author Jerzy Puchala, Gunnar Hillert
 * @version $Id$
 */
public final class JobDaoHibernate extends GenericDaoHibernate< Job, Long>
                                        implements JobDao {

    /**
     * User Dao.
     */
    private UserDao userDao;

    /**
     * Constructor.
     *
     */
    private JobDaoHibernate() {
    	super(Job.class);
    }

    /**
     * Get a gob from the persistence store.
     *
     * @param jobId
     *            Id of the job posting.
     * @return A single job posting
     *
     * @see org.jrecruiter.persistent.dao.JobReqDAO#get(java.lang.Integer)
     */
    public Job get(final Long jobId) {

        Job job = (Job) getHibernateTemplate().get(Job.class, jobId);
        return job;
    }

    /**
     * Method for returning list of all jobs.
     *
     * @return List of Jobs
     *
     */
    public List < Job > getAllJobs() {

        List < Job > jobs = (List < Job >) getHibernateTemplate()
                .find(
                        "select job from Job job "
                                + "left outer join fetch job.statistic "
                                + " order by job.updateDate DESC");

        return jobs;
    }

    /**
     * Method for getting users jobs.
     *
     * @param username name of user owning the job.
     * @return List of Job objects for given User
     * @see org.jrecruiter.persistent.dao.
     *      JobReqDAO#getAllUserJobs(java.lang.String)
     */
    public List < Job > getAllUserJobs(final String username) {

        List < Job > jobs;

        User user = userDao.getUser(username);

        boolean administrator = false;

        int index = Arrays.binarySearch(user.getAuthorities(), Roles.ROLE_ADMIN.name());

        if (index >= 0) {
        	administrator = true;
        }

        if (administrator) {
            jobs = this.getAllJobs();
        } else {

            jobs = getHibernateTemplate().find(
                    "from Job j where j.owner.username=?", username);

        }

        return jobs;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.jrecruiter.persistent.dao.
     *      JobReqDAO#getAllUserJobs(java.lang.String)
     */
    public List < Job > getAllUserJobsForStatistics(String username) {

        List < Job > jobs;

        User user = userDao.getUser(username);

        boolean administrator = false;

        int index = Arrays.binarySearch(user.getAuthorities(), Roles.ROLE_ADMIN.name());

        if (index >= 0) {
        	administrator = true;
        }

        if (administrator) {
            jobs = this.getAllJobs();
        } else {

            jobs = getHibernateTemplate()
                    .find(
                            "from Job j left outer join fetch j.statistics where j.owner.username=?",
                            username);

        }

        return jobs;
    }

    /**
     * Method for returning list of jobs owned by the user for statistical
     * purposes.
     *
     * @param username username for which statistics shall be obtained
     * @param maxResult maximum number of statistics objects returned
     * @param statsMode  what type of statistical information to be generated
     * @return List of jobs.
     *
     * @see org.jrecruiter.dao.JobsDao#getUsersJobsForStatistics(java.lang.String,
     *      java.lang.Integer, org.jrecruiter.Constants.StatsMode)
     */
    public List < Job > getUsersJobsForStatistics(final String username,
                                                 final Integer maxResult,
                                                 final StatsMode statsMode) {

        List < Job > jobs;

        User user = userDao.getUser(username);

        boolean administrator = false;

        int index = Arrays.binarySearch(user.getAuthorities(), Roles.ROLE_ADMIN.name());

        if (index >= 0) {
        	administrator = true;
        }

        final Session session = getSession(false);

        try {

            Query query = null;

            if (statsMode == StatsMode.PAGE_HITS) {

                if (administrator) {

                    query = session
                            .createQuery("select j from Job j left outer join fetch j.statistics as stats "
                                    + "where stats is not null order by stats.counter desc");

                } else {

                    query = session
                            .createQuery("select j from Job j left outer join fetch j.statistics as stats "
                                    + "where j.owner.username=:username and stats is not null "
                                    + "order by stats.counter desc");
                    query.setString("username", username);
                }
            } else {

                if (administrator) {
                    query = session
                            .createQuery("select j from Job j left outer join fetch j.statistics as stats "
                                    + "where stats is not null order by stats.uniqueVisits desc");
                } else {

                    query = session
                            .createQuery("select j from Job j left outer join fetch j.statistics as stats "
                                    + "where j.owner.username=:username and stats is not null "
                                    + "order by stats.uniqueVisits desc");
                    query.setString("username", username);
                }
            }

            query.setMaxResults(maxResult);

            jobs = query.list();

        } catch (HibernateException ex) {
            throw convertHibernateAccessException(ex);
        }

        return jobs;
    }

    /**
     * Perform a simple search within the persistence store.
     *
     * @param keyword
     *            The search keyword
     * @return List of job postings representing the search results.
     */
    public List searchByKeyword(final String keyword) {

        List list = (List) getHibernateTemplate().execute(
                new HibernateCallback() {

                    public Object doInHibernate(final Session session)
                            throws HibernateException {
                        Query q = session.createQuery("from Job j where "
                                + "lower(j.jobTitle) like :keyword or "
                                + "lower(j.description) like :keyword or "
                                + "lower(j.jobRestrictions) like :keyword or "
                                + "lower(j.businessLocation) like :keyword");
                        q.setString("keyword", "%" + keyword + "%");

                        return q.list();
                    }

                });

        return list;
    }

    /**
     * @param userDao
     *            The userDao to set.
     */
    public void setUserDao(final UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Method for returning list of available job postings.
     * @param pageSize Max number of results returned
     * @param pageNumber Which page are you one?
     * @param fieldSorted Which field shall be sorted
     * @param sortOrder What is the sort order?
     * @return List of jobs.
     */
    public List < Job > getJobs(
                            final Integer pageSize,
                            final Integer pageNumber,
                                  String fieldSorted,
                                  String sortOrder) {

        final Session session = getSession(false);
        List < Job > jobs;

        if (fieldSorted == null || fieldSorted.length() == 0) {
            fieldSorted = "updateDate";
        }

        if (sortOrder == null) {
            sortOrder = "DESC";
        } else if (!sortOrder.equalsIgnoreCase("ASC")
                && !sortOrder.equalsIgnoreCase("DESC")) {
            sortOrder = "DESC";
        }

        try {

            final Criteria criteria = session.createCriteria(Job.class);
            criteria.setFetchMode("statistics", FetchMode.JOIN);

            if (sortOrder.equalsIgnoreCase("DESC")) {
                criteria.addOrder(Order.desc(fieldSorted));
            } else if (sortOrder.equalsIgnoreCase("ASC")) {
                criteria.addOrder(Order.asc(fieldSorted));
            }

            criteria.setFirstResult((pageNumber - 1) * pageSize);
            criteria.setMaxResults(pageSize);

            jobs = criteria.list();

        } catch (HibernateException ex) {
            throw convertHibernateAccessException(ex);
        }

        return jobs;
    }

    /**
     * Returns the number of totally available jobs in the system.
     *
     * @return Total number of jobs
     * @see org.jrecruiter.dao.JobsDao#getJobsCount()
     */
    public Integer getJobsCount() {

        final Session session = getSession(false);
        Long numberOfJobs = null;

        try {
            Query query = session.createQuery("select count(*) from Job");
            numberOfJobs = (Long) query.uniqueResult();
        } catch (HibernateException ex) {
            throw convertHibernateAccessException(ex);
        }
        //FIXME
        return Integer.valueOf(numberOfJobs.toString());
    }
}
