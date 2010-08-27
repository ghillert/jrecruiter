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
package org.jrecruiter.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.jrecruiter.common.CollectionUtils;
import org.junit.Test;

/**
 * Validate object creation of the job type object
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
public class JobTypeTest {

	@Test
    public void testCreateJobType() throws Exception {

        JobType jobType = new JobType();

        assertNull(jobType.getId());
        assertNotNull(jobType.getJobs());
        assertNull(jobType.getName());
        assertTrue(jobType.getJobs().size() == 0);



    }

	@Test
    public void testCreateJobType2() throws Exception {

        JobType jobType = new JobType(999L, "jobType2");

        assertNotNull(jobType.getId());
        assertNotNull(jobType.getJobs());
        assertNotNull(jobType.getName());

        assertEquals(Long.valueOf(999L), jobType.getId());
        assertEquals("jobType2", jobType.getName());

        assertTrue(jobType.getJobs().size() == 0);

    }

	@Test
    public void testCreateJobType3() throws Exception {

        Job job = new Job();

        Set<Job> jobs = CollectionUtils.getHashSet();
        jobs.add(job);

        JobType jobType = new JobType(999L, "jobType3", jobs);

        assertNotNull(jobType.getId());
        assertNotNull(jobType.getJobs());
        assertNotNull(jobType.getName());

        assertEquals(Long.valueOf(999L), jobType.getId());
        assertEquals("jobType3", jobType.getName());

        assertTrue(jobType.getJobs().size() == 1);

    }

	@Test
    public void testCreateJobType4() throws Exception {

        Job job = new Job();

        Set<Job> jobs = CollectionUtils.getHashSet();
        jobs.add(job);

        JobType jobType = new JobType();
        jobType.setId(888L);
        jobType.setName("jobType4");
        jobType.setJobs(jobs);

        assertNotNull(jobType.getId());
        assertNotNull(jobType.getJobs());
        assertNotNull(jobType.getName());

        assertEquals(Long.valueOf(888L), jobType.getId());
        assertEquals("jobType4", jobType.getName());

        assertTrue(jobType.getJobs().size() == 1);

    }

}

