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
package org.jrecruiter.web.actions;

import junit.framework.TestCase;

/**
 * Test the Struts 2 Show Jobs Action
 *
 * @author Gunnar Hillert
 */
public class ShowJobsActionTest extends TestCase {

	public void testExecute() throws Exception {

//        ShowJobsAction showJobsAction = new ShowJobsAction();
//
//        JobService jobService = EasyMock.createMock(JobService.class);
//
//        MockHttpServletRequest request = new MockHttpServletRequest();
//
//        showJobsAction.setJobService(jobService);
//        showJobsAction.setServletRequest(request);
//
//        List<Job>jobs = new ArrayList<Job>();
//
//        Map<String, String> sortOrders = CollectionUtils.getHashMap();
//        Map<String, String> jobFilters = CollectionUtils.getHashMap();
//
//        EasyMock.expect(jobService.getJobsCount()).andReturn(10L);
//        EasyMock.expect(jobService.getJobs(15, 1, sortOrders, jobFilters)).andReturn(jobs);
//
//        EasyMock.replay(jobService);
//
//        String ret = showJobsAction.execute();
//
//        Assert.assertEquals("success", ret);
	}

	public void testExecuteAjax() throws Exception {
//        ShowJobsAction showJobsAction = new ShowJobsAction();
//        showJobsAction.setAjax("true");
//
//        MockHttpServletRequest request = new MockHttpServletRequest();
//
//        showJobsAction.setServletRequest(request);
//
//        JobService jobService = EasyMock.createMock(JobService.class);
//
//        showJobsAction.setJobService(jobService);
//
//        List<Job>jobs = new ArrayList<Job>();
//
//        Map<String, String> sortOrders = CollectionUtils.getHashMap();
//        Map<String, String> jobFilters = CollectionUtils.getHashMap();
//
//        EasyMock.expect(jobService.getJobsCount()).andReturn(10L);
//        EasyMock.expect(jobService.getJobs(15, 1, sortOrders, jobFilters)).andReturn(jobs);
//
//        EasyMock.replay(jobService);
//
//        String ret = showJobsAction.execute();
//
//        Assert.assertEquals("ajax", ret);
	}
}

