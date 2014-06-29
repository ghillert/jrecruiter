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

import java.util.Date;

import org.junit.Assert;

import org.jrecruiter.model.Role;
import org.jrecruiter.model.User;
import org.jrecruiter.model.UserToRole;
import org.jrecruiter.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Gunnar Hillert
 */
public class UserToRoleDaoTest extends BaseTest {

	private @Autowired UserDao       userDao;
	private @Autowired UserToRoleDao userToRoleDao;
	private @Autowired RoleDao       roleDao;

	@Test
	public void testSaveUserToRole() {

		final Role role = new Role(null, "TEST");
		Role savedRole = roleDao.save(role);
		entityManager.flush();

		final User user = userDao.getUserByUsernameOrEmail("admin");

		Assert.assertNotNull(user);

		UserToRole userToRole = new UserToRole(null, savedRole, user);

		UserToRole savedUserToRole = userToRoleDao.save(userToRole);
		entityManager.flush();

		Assert.assertNotNull(savedUserToRole.getId());
	}

	@Test
	public void testSaveUserToRole2() {

		final Role role = new Role(null, "TEST");
		Role savedRole = roleDao.save(role);

		User user = getUser();

		UserToRole userToRole = new UserToRole(null, savedRole, user);

		Assert.assertTrue(user.getUserToRoles().size() == 0);

		user.getUserToRoles().add(userToRole);

		Assert.assertTrue(user.getUserToRoles().size() == 1);

		User savedUser = userDao.save(user);
		entityManager.flush();

		Assert.assertTrue(savedUser.getUserToRoles().size() == 1);
	}


	private User getUser() {

		final User user = new User();
		user.setUsername("demo44");
		user.setEmail("demo@demo.com");
		user.setFirstName("Demo First Name");
		user.setLastName("Demo Last Name");
		user.setPassword("demo");
		user.setPhone("123456");
		user.setRegistrationDate(new Date());

		return user;
	}
}
