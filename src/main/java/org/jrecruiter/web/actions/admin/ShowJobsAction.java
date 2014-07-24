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
package org.jrecruiter.web.actions.admin;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.jrecruiter.common.CollectionUtils;
import org.jrecruiter.model.Job;
import org.jrecruiter.web.actions.BaseAction;

import com.opensymphony.xwork2.Preparable;

/**
 * List all the jobs.
 *
 * @author Gunnar Hillert
 *
 */
@Results(
	{
		@Result(name="inputRedirected", location="show-jobs", type="redirectAction"),
		@Result(name="success",         location="index", type="redirectAction"),
		@Result(name="ajaxJobsTable",   location="/WEB-INF/jsp/admin/joblistTable.jsp")
	}
)
public class ShowJobsAction extends BaseAction implements Preparable {

	/** serialVersionUID. */
	private static final long serialVersionUID = -6536348867574805926L;

	private List<Job> jobs;

	private List<Long> jobsToDelete = CollectionUtils.getArrayList();

	private int maxRows = 15;

	//~~~~~Action Methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/**
	 *
	 */
	@Action("show-jobs")
	public String execute() {
		return INPUT;
	}

	@Action("show-jobs-ajax")
	public String executeAjaxJobsTable() {
		return "ajaxJobsTable";
	}

	public void prepare() throws Exception {
		this.jobs = jobService.getUsersJobs(super.getLoggedInUser().getUsername());
	}

	/**
	 *  Delete any selected jobs.
	 */
	@Action("delete-jobs")
	public String delete() {

		//TODO improve security

		 if(!jobsToDelete.isEmpty()){

			  int validJobIds = 0;

			 for (Long jobId : jobsToDelete) {
				 if (jobId != null) {
					 jobService.deleteJobForId(jobId);
					 validJobIds++;
				 }
			 }

			 if (validJobIds == 1) {
				 super.addActionMessage(getText("class.ShowJobsAction.job_delete_success_one", new String[]{String.valueOf(validJobIds)}));
			 } else {
				 super.addActionMessage(getText("class.ShowJobsAction.job_delete_success_multiple", new String[]{String.valueOf(validJobIds)}));
			 }

		 } else {
			 super.addActionMessage("No Job Posting was deleted.");
		 }

		 return "inputRedirected";
	}

	//~~~~~Getters and Setters~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public List<Long> getJobsToDelete() {
		return jobsToDelete;
	}

	public void setJobsToDelete(List<Long> jobsToDelete) {
		this.jobsToDelete = jobsToDelete;
	}

	/**
	 * @return the maxRows
	 */
	public int getMaxRows() {
		return maxRows;
	}

	/**
	 * @param maxRows the maxRows to set
	 */
	public void setMaxRows(int maxRows) {
		this.maxRows = maxRows;
	}

}
