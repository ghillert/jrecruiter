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
package org.jrecruiter.service.impl;

import java.util.Calendar;
import java.util.List;

import org.jrecruiter.common.CalendarUtils;
import org.jrecruiter.dao.JobDao;
import org.jrecruiter.model.Job;
import org.jrecruiter.model.statistics.JobCountPerDay;
import org.jrecruiter.service.FlexService;
import org.jrecruiter.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
@Transactional
@Service("flexService")
public class FlexServiceImpl implements FlexService {

    /** Job Dao. */
    private @Autowired JobDao jobDao;

    private @Autowired JobService jobService;

    //~~~~~Business Methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    /** {@inheritDoc} */
    @Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
    public List<Job> getJobSummaries() {
        return jobDao.getJobSummaries();
    }

    @Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
    public Job getJob(Long jobId) {
        return jobDao.get(jobId);
    }

    public List<JobCountPerDay> getJobCountPerDayAndPeriod() {

        final Calendar calendarToday = CalendarUtils.getCalendarWithoutTime();

        final Calendar calendar30    = CalendarUtils.getCalendarWithoutTime();
        calendar30.add(Calendar.MONTH, -36);

        return jobService.getJobCountPerDayAndPeriod(calendar30.getTime(), calendarToday.getTime());
    }
}
