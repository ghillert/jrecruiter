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
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.jmesa.facade.TableFacade;
import org.jmesa.facade.TableFacadeFactory;
import org.jmesa.limit.Filter;
import org.jmesa.limit.FilterSet;
import org.jmesa.limit.Limit;
import org.jmesa.limit.Sort;
import org.jmesa.limit.SortSet;
import org.jrecruiter.common.CollectionUtils;
import org.jrecruiter.model.User;
import org.jrecruiter.web.actions.BaseAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.Preparable;

/**
 * List all the users.
 *
 * @author Gunnar Hillert
 *
 */
@Results(
	{
		@Result(name="success",         location="index", type="redirectAction"),
		@Result(name="ajaxUsersTable",  location="/WEB-INF/jsp/admin/user-list-table.jsp"),
		@Result(name="cancel",          location="index", type="redirectAction")
	}

)
public class ShowUsersAction extends BaseAction implements Preparable, ServletRequestAware {

	/** serialVersionUID. */
	private static final long serialVersionUID = -2208374563094039361L;

	/**
	 * Logger Declaration.
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(ShowUsersAction.class);

	private transient HttpServletRequest request;

	private Limit limit;
	private List<User>users;

	/**
	 *
	 */
	@Action("show-users")
	public String execute() {
		populateTable();
		return INPUT;
	}

	@SkipValidation
	@Override
	public String cancel() {
		return "cancel";
	}

	@Action("show-users-ajax")
	public String executeAjaxUsersTable() {
		populateTable();
		return "ajaxUsersTable";
	}

	private void populateTable() {
		final TableFacade tableFacade = TableFacadeFactory.createTableFacade("usersTable", request);

		limit                     = tableFacade.getLimit();
		final FilterSet filterSet = limit.getFilterSet();
		final SortSet sortSet     = limit.getSortSet();


		final int totalRows = userService.getUsersCount().intValue();
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
			sortOrders.put("lastName", "ASC");
		}

		if (filterSet.isFiltered()) {
			for (Filter filter : filterSet.getFilters()) {
				jobFilters.put(filter.getProperty(), filter.getValue());
			}
		}

		LOGGER.info("Retrieving all users - "
						+ ";Total Size: " + totalRows
						+ ";Results per Page: " + maxRows
						+ ";Page: " + page);

		this.users = userService.getUsers(maxRows, page, sortOrders, jobFilters);
	}

	public void prepare() throws Exception {

	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Limit getLimit() {
		return limit;
	}

	public void setLimit(Limit limit) {
		this.limit = limit;
	}

}
