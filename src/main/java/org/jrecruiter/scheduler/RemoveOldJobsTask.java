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
package org.jrecruiter.scheduler;

import org.jrecruiter.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Task that periodically deletes jobs that are deemed "too old".
 *
 * @author Gunnar Hillert
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
