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
package org.jrecruiter.service;

import java.util.Set;

import org.jrecruiter.model.User;
import org.jrecruiter.model.UserToRole;
import org.jrecruiter.model.export.Backup;
import org.jrecruiter.test.BaseServiceIntegrationTest;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

/**
 *
 * @author Gunnar Hillert
 */
@Ignore
public class DemoServiceTest extends BaseServiceIntegrationTest {

	@Autowired SystemSetupService demoService;

	@Test
	public void testCreateDatabase(){
		demoService.createDatabase();
	};

	@Test
	public void testUpdateDatabase(){

		demoService.updateDatabase();
	};

	@Test
	@Rollback(false)
	public void testLoadAndRestoreSeedData(){
		demoService.loadAndRestoreSeedData();
	};

	@Test
	@Rollback(false)
	public void testRestore(){

		final java.io.InputStream inputStream =  DemoServiceTest.class.getResourceAsStream("/org/jrecruiter/core/seeddata/demodata.xml");

		final Backup backup = demoService.convertToBackupData(inputStream);

		for (User user : backup.getUsers()) {

			final Set<UserToRole> userToRoles = user.getUserToRoles();
			Assert.assertTrue("Every user should have at least one role.", !userToRoles.isEmpty());

			for (final UserToRole userToRole : userToRoles) {
				Assert.assertNotNull(userToRole.getRoleName());
			}
		}

	};

}
