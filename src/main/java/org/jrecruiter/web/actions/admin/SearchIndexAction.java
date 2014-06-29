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

import org.apache.struts2.convention.annotation.Result;
import org.jrecruiter.web.actions.BaseAction;

/**
 * Re-index the Hibernate Search Index
 *
 * @author Gunnar Hillert
 *
 */
@Result(name="success", location="index", type="redirectAction")
public class SearchIndexAction extends BaseAction {

	/** serialVersionUID. */
	private static final long serialVersionUID = 4614516114027504626L;

	/**
	 *
	 */
	@Override
	public String execute() {
			jobService.reindexSearch();
			super.addActionMessage("Re-indexing of the search index triggered.");
			return SUCCESS;
	}
}
