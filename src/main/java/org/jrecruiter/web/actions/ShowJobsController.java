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

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jmesa.facade.TableFacade;
import org.jmesa.limit.Filter;
import org.jmesa.limit.FilterSet;
import org.jmesa.limit.Limit;
import org.jmesa.limit.Sort;
import org.jmesa.limit.SortSet;
import org.jrecruiter.common.CollectionUtils;
import org.jrecruiter.model.Job;
import org.jrecruiter.service.JobService;
import org.jrecruiter.web.controller.BaseFormController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Show a list of jobs.
 *
 * @author Gunnar Hillert
 * @since 3.0
 */
@Controller
@RequestMapping(value={"/show-jobs", "/show-jobs-ajax"})
public class ShowJobsController extends BaseFormController {

	/** serialVersionUID. */
	private static final long serialVersionUID = 369806210598096582L;

	/**
	 * Logger Declaration.
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(ShowJobsController.class);

	@Autowired
	private JobService jobService;

	@RequestMapping
	public String execute(ModelMap model, HttpServletRequest request, @RequestParam(defaultValue="false") boolean ajax) throws Exception {
		Limit limit;
		List<Job> jobs           = CollectionUtils.getArrayList();

		final TableFacade tableFacade = new TableFacade("jobsTable", request);
		tableFacade.setStateAttr("restore");
		limit                     = tableFacade.getLimit();
		final FilterSet filterSet = limit.getFilterSet();
		final SortSet sortSet     = limit.getSortSet();

		final int totalRows = jobService.getJobsCount().intValue();
		tableFacade.setTotalRows(totalRows);

		int page     = limit.getRowSelect().getPage();
		int maxRows  = limit.getRowSelect().getMaxRows();

		final Map<String, String> sortOrders = CollectionUtils.getHashMap();
		final Map<String, String> jobFilters = CollectionUtils.getHashMap();

		if (sortSet.isSorted()) {
			for (Sort sort : sortSet.getSorts()) {
				sortOrders.put(sort.getProperty(), sort.getOrder().name());
			}
		}

		if (sortOrders.isEmpty()) {
			sortOrders.put("updateDate", "DESC");
		}

		if (filterSet.isFiltered()) {
			for (Filter filter : filterSet.getFilters()) {
				jobFilters.put(filter.getProperty(), filter.getValue());
			}
		}

		LOGGER.info("Retrieving all jobs - "
						+ ";Total Size: " + totalRows
						+ ";Results per Page: " + maxRows
						+ ";Page: " + page);

		jobs = jobService.getJobs(maxRows, page, sortOrders, jobFilters);

		model.put("limit", limit);
		model.put("jobs", jobs);
		model.put("isAjax", ajax);

		if (ajax) {
			return "/jobsTable";
		}

		return "/show-jobs";
	}

	//~~~~~Getters and Setters~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

}
