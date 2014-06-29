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
import java.net.URISyntaxException;

/**
 * Provides a basic Java Apis for interacting with the static Google Maps Apis.
 *
 * @author Gunnar Hillert
 *
 */
public class GoogleMapsUtils {

	/** Defines the Google Maps Url. */
	public static final String googleMapsStaticUrl = "http://maps.googleapis.com/maps/api/staticmap";

	/** Defines the default width of the requested image. */
	public static final Integer defaultWidth       = Integer.valueOf(400);

	/** Defines the default height of the requested image. */
	public static final Integer defaultHeight      = Integer.valueOf(300);

	/** Defines the default latitude (Atlanta, Ga). */
	public static final BigDecimal defaultMapLatitude  = BigDecimal.valueOf(33.74);

	/** Defines the default longitude (Atlanta, Ga). */
	public static final BigDecimal defaultMapLongitude = BigDecimal.valueOf(-84.38);

	/** Defines the default map zoom level. */
	public static final Integer    defaultMapZoomLevel = Integer.valueOf(10);

	/**
	 * Defines the available Url parameters.
	 *
	 * @author Gunnar Hillert
	 *
	 */
	public enum GoogleMapsUrlParameterNames {

		CENTER("center"),
		ZOOM("zoom"),
		KEY("key"),
		SIZE("size"),
		SENSOR("sensor"),
		MARKERS("markers");

		private String parameterNames;

		private GoogleMapsUrlParameterNames(final String parameterNames) {
			this.parameterNames = parameterNames;
		}

		public String getParameterName() {
			return parameterNames;
		}
	}

	/**
	 * Defines the available color names.
	 *
	 * @author Gunnar Hillert
	 *
	 */
	public enum GoogleMapsColorNames {

		BLACK("black"),
		BROWN("brown"),
		GREEN("green"),
		PURPLE("purple"),
		YELLOW("yellow"),
		BLUE("blue"),
		GRAY("gray"),
		ORANGE("orange"),
		RED("red"),
		WHITE("white");

		private String colorName;

		private GoogleMapsColorNames(final String colorName) {
			this.colorName = colorName;
		}

		public String getColorName() {
			return colorName;
		}
	}

	/**
	 * Defines the available marker sizes.
	 *
	 * @author Gunnar Hillert
	 *
	 */
	public enum GoogleMapsMarkerSize {

		TINY("tiny"),
		MID("mid"),
		SMALL("small");

		private String markerSize;

		private GoogleMapsMarkerSize(final String markerSize) {
			this.markerSize = markerSize;
		}

		public String getMarkerSize() {
			return markerSize;
		}
	}

	/**
	 * Create the size Url parameter.
	 *
	 * @param width
	 * @param height
	 * @return
	 */
	public static String buildSizeParameterValue(final Integer width, final Integer height) {
		return width + "x" + height;
	}

	/**
	 * Create the latitude/longitude Url parameter.
	 *
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	public static String buildLatLongParameterValue(final BigDecimal latitude,
													final BigDecimal longitude) {
		return String.valueOf(latitude) + "," + String.valueOf(longitude);
	}

	/**
	 * Create the maps marker parameter.
	 *
	 * @param latitude
	 * @param longitude
	 * @param size
	 * @param color
	 * @param character
	 * @return
	 */
	public static String buildMarkerParameterValue(final BigDecimal latitude,
												   final BigDecimal longitude,
												   final GoogleMapsMarkerSize size,
												   final GoogleMapsColorNames color) {
		return String.valueOf(latitude) + "," + String.valueOf(longitude) + ","
			 + size.markerSize + color.colorName;
	}

	/**
	 * Main method to create the complete url to request the image to be generate.
	 *
	 * @param latitude
	 * @param longitude
	 * @param zoomLevel
	 * @param key
	 * @return The URL of the to request the map image.
	 */
	public static URI buildGoogleMapsStaticUrl(final BigDecimal latitude,
											   final BigDecimal longitude,
											   final Integer zoomLevel) {
		final URI uri;

		try {
			uri = new URI(GoogleMapsUtils.googleMapsStaticUrl);
		} catch (URISyntaxException e) {
			throw new IllegalStateException("Error creating Google Maps URL.", e);
		}

		final UrlEncodedQueryString queryString = UrlEncodedQueryString.parse(uri);

		queryString.set(GoogleMapsUrlParameterNames.CENTER.getParameterName(),
				GoogleMapsUtils.buildLatLongParameterValue(latitude, longitude));
		queryString.set(GoogleMapsUrlParameterNames.ZOOM.getParameterName(), zoomLevel);
		queryString.set(GoogleMapsUrlParameterNames.SIZE.getParameterName(),
				GoogleMapsUtils.buildSizeParameterValue(GoogleMapsUtils.defaultWidth, GoogleMapsUtils.defaultHeight));
		queryString.set(GoogleMapsUrlParameterNames.MARKERS.getParameterName(),
						GoogleMapsUtils.buildMarkerParameterValue(latitude, longitude,
																  GoogleMapsMarkerSize.MID,
																  GoogleMapsColorNames.ORANGE));
		queryString.set(GoogleMapsUrlParameterNames.SENSOR.getParameterName(), "false");

		return queryString.apply(uri);

	}


}
