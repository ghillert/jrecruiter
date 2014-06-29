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

import org.junit.Assert;

import org.jrecruiter.web.actions.BaseActionTest;

/**
 * Test the Struts 2 Add User Action
 *
 * @author Gunnar Hillert
 */
public class AddUserActionTest extends BaseActionTest {

	public void testExecute() throws Exception {
		AddUserAction addUserAction = new AddUserAction();

		String ret = addUserAction.execute();

		Assert.assertEquals("input", ret);
	}
//FIXME test is disabled
//    public void testSave() throws Exception {
//        AddUserAction addUserAction = new AddUserAction();
//
//        User user = new User();
//        user.setUsername("abc");
//        addUserAction.setUser(user);
//
//
//        final UserService userService = EasyMock.createMock(UserService.class);
//
//        EasyMock.expect(userService.addUser(user)).andReturn(user);
//        EasyMock.replay(userService);
//
//        addUserAction.setUserService(userService);
//
//        String ret = addUserAction.save();
//
//        Assert.assertEquals("success", ret);
//    }
}

