/*
 * Copyright 2006-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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

