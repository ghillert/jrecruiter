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

import org.hibernate.Query;
import org.hibernate.Session;
import org.jrecruiter.dao.JobCountPerDayDao;
import org.jrecruiter.model.statistics.JobCountPerDay;
import org.springframework.stereotype.Repository;

/**
 * This DAO provides job-related database methods.
 *
 * @author 	 Puchala, Gunnar Hillert
 * @version $Id: JobDaoJpa.java 264 2008-10-25 21:36:15Z ghillert $
 */
@Repository("jobCountPerDayDao")
public final class JobCountPerDayDaoJpa extends GenericDaoJpa< JobCountPerDay, Long>
implements JobCountPerDayDao {

    /**
     * Constructor.
     *
     */
    private JobCountPerDayDaoJpa() {
        super(JobCountPerDay.class);
    }

    /** {@inheritDoc} */
	public JobCountPerDay getByDate(Date day) {
        Session session = (Session)entityManager.getDelegate();
        Query query = session.createQuery("select jcpd from JobCountPerDay jcpd "
                + " where jcpd.jobDate = :jobDate ");
        query.setDate("jobDate", day);

        return (JobCountPerDay)query.uniqueResult();
	}

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    public List<JobCountPerDay> getJobCountPerDayAndPeriod(Date fromDate, Date toDate) {

        Session session = (Session)entityManager.getDelegate();
        Query query = session.createQuery("select jcpd from JobCountPerDay jcpd "
                + " where jcpd.jobDate >= :fromDate and jcpd.jobDate <= :toDate "
                + " order by jobDate asc");
        query.setDate("fromDate", fromDate);
        query.setDate("toDate", toDate);

        List<JobCountPerDay> jobCountPerDayList = query.list();

        return jobCountPerDayList;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
	public List<JobCountPerDay> getLatestTwoJobCounts() {
        Session session = (Session)entityManager.getDelegate();
        Query query = session.createQuery("select jcpd from JobCountPerDay jcpd "
                + " order by jobDate desc");

        query.setMaxResults(2);
        final List<JobCountPerDay> jobCountPerDayList = query.list();

        return jobCountPerDayList;
	}

}


