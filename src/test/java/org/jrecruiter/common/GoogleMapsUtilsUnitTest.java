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
package org.jrecruiter.common;

import java.math.BigDecimal;
import java.net.URI;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This unit test tests the generation of GoogleMaps urls.
 *
 * @author Gunnar Hillert
 */
public class GoogleMapsUtilsUnitTest extends TestCase {

	private final static Logger LOGGER = LoggerFactory.getLogger(GoogleMapsUtilsUnitTest.class);

	public void testNewArrayListInstance() {

		URI uri = GoogleMapsUtils.buildGoogleMapsStaticUrl(BigDecimal.TEN, BigDecimal.TEN, 10);

		LOGGER.info("http://maps.googleapis.com/maps/api/staticmap?center=10%2C10&zoom=10&size=400x300&markers=10%2C10%2Cmidorange&sensor=false");

		String uriAsString = uri.toString();

		LOGGER.info(uriAsString);

		assertEquals("http://maps.googleapis.com/maps/api/staticmap?center=10%2C10&zoom=10&size=400x300&markers=10%2C10%2Cmidorange&sensor=false", uriAsString);

	}

}
