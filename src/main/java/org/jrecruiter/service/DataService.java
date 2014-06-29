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

import java.awt.Image;
import java.math.BigDecimal;

/**
 * Provides methods that call external data services such as Google's.
 *
 * @author Gunnar Hillert
 */
public interface DataService {

	/**
	 * Method for an images of a Google Map
	 *
	 * @param longitude
	 * @param latitude
	 * @param zoomLevel
	 *
	 */
	Image getGoogleMapImage(BigDecimal latitude, BigDecimal longitude, Integer zoomLevel);

}
