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
package org.jrecruiter.dao;

import java.util.List;
import java.util.Map;

import org.jrecruiter.model.User;

/**
 * @author Dorota Puchala, Gunnar Hillert
 */
public interface UserDao extends GenericDao < User, Long >{

	/**
	 * Get a user from persistence store.
	 * @param username
	 * @return A single user
	 */
	User getUser(String username);

	/**
	 * Get a user from persistence store either by its username or the provided
	 * email address.
	 *
	 * @param username
	 * @return A single user
	 */
	User getUserByUsernameOrEmail(String usernameOrEmail);

	/**
	 * Return all users from persistence store.
	 * @return List of users
	 */
	List < User > getAllUsers();

	/**
	 * Delete an array of users from persistence store.
	 *
	 * @param usernameList list of user names.
	 */
	void deleteUser(String[] usernameList);

	/**
	 * Method for returning list of available users.
	 * @param pageSize Max number of results returned
	 * @param pageNumber Which page are you one?
	 * @param fieldSorted Which field shall be sorted
	 * @param sortOrder What is the sort order?
	 * @return List of users.
	 */
	List < User > getUsers(Integer pageSize, Integer pageNumber, Map<String, String> sortOrders, Map<String, String> userFilters);

	/**
	 * Returns the number of totally available users in the system.
	 *
	 * @return Total number of jobs
	 */
	Long getUsersCount();

	/**
	 * Get a user by its verification key. This method is used to verify user
	 * account creation.
	 *
	 * @param key Key for which the corresponding user supposedly exists
	 * @return Return a user for the existing key
	 */
	User getUserByVerificationKey(String key);
}
