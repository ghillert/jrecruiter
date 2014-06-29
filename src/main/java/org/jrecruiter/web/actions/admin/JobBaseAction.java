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

import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jrecruiter.common.ApiKeysHolder;
import org.jrecruiter.common.Constants.OfferedBy;
import org.jrecruiter.model.Industry;
import org.jrecruiter.web.JobForm;
import org.jrecruiter.web.actions.BaseAction;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import org.jrecruiter.model.Region;

/**
 * Edit a job. Save the changes or delete the job posting altogether.
 *
 * @author Gunnar Hillert
 *
 */
public abstract class JobBaseAction extends BaseAction implements Preparable, ModelDriven<JobForm> {

	/** serialVersionUID. */
	private static final long serialVersionUID = 5109535527147122330L;


	private ApiKeysHolder apiKeysHolder;

	protected JobForm model = new JobForm();

	private Set<OfferedBy>offeredBySet;
	private List<Region> regions;
	private List<Industry>industries;
	private Map<Boolean, String>yesNoList;

	protected Long id;

	/** Prepare the select boxes of the form. */
	public void prepare() throws Exception {

		this.offeredBySet = EnumSet.allOf(OfferedBy.class);
		this.offeredBySet.remove(OfferedBy.UNDEFINED);

		this.regions = jobService.getRegions();
		this.industries = jobService.getIndustries();
		this.yesNoList = new HashMap<Boolean, String>();
		this.yesNoList.put(Boolean.FALSE, "False");
		this.yesNoList.put(Boolean.TRUE, "True");

	}

	//~~~~~Getters and Setters~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public JobForm getModel() {
		return model;
	}

	public void setModel(JobForm job) {
		this.model = job;
	}

	public Set<OfferedBy> getOfferedBySet() {
		return offeredBySet;
	}

	public void setOfferedBySet(Set<OfferedBy> offeredBySet) {
		this.offeredBySet = offeredBySet;
	}

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public List<Industry> getIndustries() {
		return industries;
	}

	public void setIndustries(List<Industry> industries) {
		this.industries = industries;
	}

	public Map<Boolean, String> getYesNoList() {
		return yesNoList;
	}

	public void setYesNoList(Map<Boolean, String> yesNoList) {
		this.yesNoList = yesNoList;
	}

	/**
	 * @return the apiKeysHolder
	 */
	public ApiKeysHolder getApiKeysHolder() {
		return apiKeysHolder;
	}

	/**
	 * @param apiKeysHolder the apiKeysHolder to set
	 */
	public void setApiKeysHolder(ApiKeysHolder apiKeysHolder) {
		this.apiKeysHolder = apiKeysHolder;
	}

}
