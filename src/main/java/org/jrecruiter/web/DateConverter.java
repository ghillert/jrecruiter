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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* From Appfuse
* @author Gunnar Hillert
*/
public class DateConverter extends StrutsTypeConverter {

	public static final String format = "MM/dd/yyyy"; //"11/11/2008"

	/**
	 *   Initialize Logging.
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(DateConverter.class);

	@Override
	public Object convertFromString(Map ctx, String[] value, Class arg2) {

		if (value == null || value[0] == null || value[0].trim().equals("")) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setLenient(false);

		try {
			return sdf.parse(value[0]);
		} catch (ParseException pe) {
			LOGGER.warn("Error parsing date: " + value[0] + "; error: " + pe.getMessage() + "; expected format: " + format);
		}
		return null;
	}

	@Override
	public String convertToString(Map ctx, Object data) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(data);
	}
}
