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

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import junit.framework.TestCase;

/**
 * Test the Struts 2 custom Date Converter
 *
 * @author Gunnar Hillert
 */
public class DateConverterTest extends TestCase {

	public void testConvertFromStringWithNull() throws Exception {

		DateConverter dateConverter = new DateConverter();

		Object ret = dateConverter.convertFromString(null, null, null);

		Assert.assertNull(ret);
	}

	public void testConvertFromStringWithNull2() throws Exception {

		DateConverter dateConverter = new DateConverter();

		Object ret = dateConverter.convertFromString(null, new String[]{null}, null);

		Assert.assertNull(ret);
	}

	public void testConvertFromStringWithWrongFormat() throws Exception {

		DateConverter dateConverter = new DateConverter();

		final String dateAsString = "2008-1-30";

		Object ret = dateConverter.convertFromString(null, new String[]{dateAsString}, null);
		Assert.assertNull(ret);
	}

	public void testConvertFromString() throws Exception {
		DateConverter dateConverter = new DateConverter();

		final String dateAsString = "01/30/2008";

		Object ret = dateConverter.convertFromString(null, new String[]{dateAsString}, null);

		Assert.assertNotNull(ret);
		Assert.assertTrue(ret instanceof Date);

		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date expectedDate = formatter.parse(dateAsString);
		Assert.assertTrue(expectedDate.equals(ret));
	}

	public void testConvertToString() throws Exception {
		DateConverter dateConverter = new DateConverter();
		final String dateAsString = "01/30/2008";
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date expectedDate = formatter.parse(dateAsString);

		String ret = dateConverter.convertToString(null, expectedDate);

		Assert.assertNotNull(ret);
		Assert.assertTrue(dateAsString.equals(ret));
	}
}

