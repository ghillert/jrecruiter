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
package org.jrecruiter.dao.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.jrecruiter.common.Constants.StatsMode;
import org.jrecruiter.dao.JobDao;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.statistics.JobCountPerDay;
import org.springframework.stereotype.Repository;

/**
 * This DAO provides job-related database methods.
 *
 * @author 	 Puchala, Gunnar Hillert
 * @version $Id$
 */
@Repository("jobDao")
public final class JobDaoJpa extends GenericDaoJpa< Job, Long>
implements JobDao {

    /**
     * Constructor.
     *
     */
    private JobDaoJpa() {
        super(Job.class);
    }

    /**
     * Method for returning list of all jobs.
     *
     * @return List of Jobs
     *
     */
    @SuppressWarnings("unchecked")
    public List < Job > getAllJobs() {

        List < Job > jobs = entityManager
        .createQuery("select job from Job job "
                + "left outer join fetch job.statistic "
                + " order by job.updateDate DESC")
                .getResultList();

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
    @SuppressWarnings("unchecked")
    public List < Job > getAllUserJobs(final String username) {

        List < Job > jobs = entityManager
        .createQuery("from Job j where j.user.username=:username")
        .setParameter("username", username)
        .getResultList();
        return jobs;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.jrecruiter.persistent.dao.
     *      JobReqDAO#getAllUserJobs(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List < Job > getAllUserJobsForStatistics(Long userId) {

        List < Job > jobs = entityManager
        .createQuery("from Job j left outer join fetch j.statistic where j.user.id=:userId")
        .setParameter("userId", userId)
        .getResultList();
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
     *      java.lang.Integer, org.jrecruiter.common.Constants.StatsMode)
     */
    @SuppressWarnings("unchecked")
    public List < Job > getUsersJobsForStatistics(final Long userId,
            final Integer maxResult,
            final StatsMode statsMode,
            final Boolean administrator) {

        final List < Job > jobs;

            javax.persistence.Query query = null;

            if (statsMode == StatsMode.PAGE_HITS) {

                if (administrator) {

                    query = entityManager
                    .createQuery("select j from Job j left outer join fetch j.statistic as stats "
                            + "where stats is not null order by stats.counter desc");

                } else {

                    query = entityManager
                    .createQuery("select j from Job j left outer join fetch j.statistic as stats "
                            + "where j.user.id=:userId and stats is not null "
                            + "order by stats.counter desc");
                    query.setParameter("userId", userId);
                }
            } else {

                if (administrator) {
                    query = entityManager
                    .createQuery("select j from Job j left outer join fetch j.statistic as stats "
                            + "where stats is not null order by stats.uniqueVisits desc");
                } else {

                    query = entityManager
                    .createQuery("select j from Job j left outer join fetch j.statistic as stats "
                            + "where j.user.id=:userId and stats is not null "
                            + "order by stats.uniqueVisits desc");
                    query.setParameter("userId", userId);
                }
            }

            query.setMaxResults(maxResult);

            jobs = query.getResultList();

            return jobs;
        }

        /**
         * Perform a simple search within the persistence store.
         *
         * @param keyword
         *            The search keyword
         * @return List of job postings representing the search results.
         */
        @SuppressWarnings("unchecked")
        public List<Job> searchByKeyword(final String keyword) {

            FullTextEntityManager fullTextEntityManager = Search.createFullTextEntityManager(entityManager);

            MultiFieldQueryParser parser = new MultiFieldQueryParser( new String[]{"description"},
              new StandardAnalyzer());
            try {
            org.apache.lucene.search.Query query = parser.parse(keyword);

            javax.persistence.Query hibQuery = fullTextEntityManager.createFullTextQuery( query, Job.class );
            List<Job> result = hibQuery.getResultList();

            return result;

              } catch ( org.apache.lucene.queryParser.ParseException e) {
                throw new IllegalStateException(e);
            }

        }

        /**
         * Method for returning list of available job postings.
         * @param pageSize Max number of results returned
         * @param pageNumber Which page are you one?
         * @param fieldSorted Which field shall be sorted
         * @param sortOrder What is the sort order?
         * @return List of jobs.
         */
        @SuppressWarnings("unchecked")
        public List < Job > getJobs(
                final Integer pageSize,
                final Integer pageNumber,
                String fieldSorted,
                String sortOrder) {
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

            Session session = (Session)entityManager.getDelegate();
            final Criteria criteria = session.createCriteria(Job.class);
            criteria.setFetchMode("statistics", FetchMode.JOIN);
            criteria.setFetchMode("region", FetchMode.JOIN);

            if (sortOrder.equalsIgnoreCase("DESC")) {
                criteria.addOrder(Order.desc(fieldSorted));
            } else if (sortOrder.equalsIgnoreCase("ASC")) {
                criteria.addOrder(Order.asc(fieldSorted));
            }

            criteria.setFirstResult((pageNumber - 1) * pageSize);
            criteria.setMaxResults(pageSize);

            jobs = criteria.list();

            return jobs;
        }


        /**
         * Returns the number of totally available jobs in the system.
         *
         * @return Total number of jobs
         * @see org.jrecruiter.dao.JobsDao#getJobsCount()
         */
        public Long getJobsCount() {

            Long numberOfJobs = null;

            Session session = (Session)entityManager.getDelegate();
            Query query = session.createQuery("select count(*) from Job");
            numberOfJobs = (Long) query.uniqueResult();

            return numberOfJobs;
        }

        public Long getJobCount(Date day) {
            Long numberOfJobs = null;

            Session session = (Session)entityManager.getDelegate();
            Query query = session.createQuery("select count(*) from Job job where job.registrationDate < :day");
            query.setDate("day", day);

            numberOfJobs = (Long) query.uniqueResult();

            return numberOfJobs;
        }

        public List<JobCountPerDay> getJobCountPerDayAndPeriod(Date fromDate, Date toDate) {

            Session session = (Session)entityManager.getDelegate();
            Query query = session.createQuery("select new org.jrecruiter.model.statistics.JobCountPerDay(year(job.registrationDate), month(job.registrationDate), day(job.registrationDate), count(*)) from Job job "
                    + " where job.registrationDate >= :fromDate and job.registrationDate <= :toDate "
                    + "group by day(job.registrationDate), year(job.registrationDate), month(job.registrationDate)"
                    + "order by year(job.registrationDate) asc, month(job.registrationDate) asc, day(job.registrationDate) asc");
            query.setDate("fromDate", fromDate);
            query.setDate("toDate", toDate);

            List<JobCountPerDay> jobCountPerDayList = query.list();

            return jobCountPerDayList;
        }

        /** {@inheritDoc} */
        public void reindexSearch() {

            FullTextEntityManager fullTextEntityManager = Search.createFullTextEntityManager(entityManager);
            fullTextEntityManager.setFlushMode(FlushModeType.COMMIT);

            List<Job> jobs = entityManager.createQuery("select job from Job as job").getResultList();
            for (Job job : jobs) {
                fullTextEntityManager.index(job);
            }

        }

    }


