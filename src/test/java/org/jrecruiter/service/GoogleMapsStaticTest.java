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

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import org.jrecruiter.common.ApiKeysHolder;
import org.jrecruiter.common.GoogleMapsUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Gunnar Hillert
 */
public class GoogleMapsStaticTest {

	private @Autowired ApiKeysHolder apiKeysHolder;

	@Test
	public void testStaticMapTest() throws Exception {
		sendGetRequest();
	}


	private void sendGetRequest() {

	// Send a GET request to the servlet
	try {

		final URI url = GoogleMapsUtils.buildGoogleMapsStaticUrl(BigDecimal.TEN, BigDecimal.TEN, 10);
		final URLConnection conn = url.toURL().openConnection ();

		final BufferedImage img = ImageIO.read(conn.getInputStream());

		Assert.assertNotNull(img);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
