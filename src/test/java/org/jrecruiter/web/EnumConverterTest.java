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
package org.jrecruiter.web;

import org.junit.Assert;
import junit.framework.TestCase;

import org.jrecruiter.common.Constants.JobStatus;

/**
 * Test the Struts 2 custom Enum Converter
 *
 * @author Gunnar Hillert
 */
public class EnumConverterTest extends TestCase {

	public void testConvertFromStringNull() throws Exception {
		EnumConverter enumConverter = new EnumConverter();

		Object ret = enumConverter.convertFromString(null, new String[]{"ACTIVE"}, String.class);

		Assert.assertNull(ret);
	}

	public void testConvertFromString() throws Exception {
		EnumConverter enumConverter = new EnumConverter();

		Object ret = enumConverter.convertFromString(null, new String[]{"ACTIVE"}, JobStatus.class);

		Assert.assertNotNull(ret);
		Assert.assertTrue(ret instanceof JobStatus);
		Assert.assertTrue(JobStatus.ACTIVE.equals(ret));
	}

	public void testConvertToString() throws Exception {
		EnumConverter enumConverter = new EnumConverter();

		String ret = enumConverter.convertToString(null, JobStatus.ACTIVE);

		Assert.assertNotNull(ret);
		Assert.assertTrue("ACTIVE".equals(ret));
	}
}

