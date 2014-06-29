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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * Holder Object for Api keys such as for webservices like Google Maps.
 *
 * @author Gunnar Hillert
 * @since 2.0
 *
 */
public class ApiKeysHolder {

	private final boolean twitterEnabled;

	private final String twitterConsumerKey;
	private final String twitterConsumerSecret;
	private final String twitterAccessToken;
	private final String twitterAccessTokenSecret;

	private final boolean reCaptchaEnabled;
	private final String reCaptchaPublicKey;
	private final String reCaptchaPrivateKey;

	private final boolean bitlyEnabled;
	private final String bitlyUsername;
	private final String bitlyPassword;

	@Autowired
	public ApiKeysHolder(Environment environment) {

		this.twitterEnabled = environment.getProperty("twitter.enabled", Boolean.class, false);
		this.twitterConsumerKey = environment.getProperty("twitter.oauth.consumerKey", String.class, "");
		this.twitterConsumerSecret = environment.getProperty("twitter.oauth.consumerSecret", String.class, "");
		this.twitterAccessToken = environment.getProperty("twitter.oauth.accessToken", String.class, "");
		this.twitterAccessTokenSecret = environment.getProperty("twitter.oauth.accessTokenSecret", String.class, "");

		this.bitlyEnabled = environment.getProperty("bitly.enabled", boolean.class, false);
		this.bitlyUsername = environment.getProperty("bitly.username", String.class, "");
		this.bitlyPassword = environment.getProperty("bitly.password", String.class, "");

		this.reCaptchaEnabled = environment.getProperty("recaptcha.enabled", boolean.class, false);
		this.reCaptchaPublicKey = environment.getProperty("recaptcha.publicKey", String.class, "");
		this.reCaptchaPrivateKey = environment.getProperty("recaptcha.privateKey", String.class, "");

	}

	/**
	 * @return the twitterEnabled
	 */
	public boolean isTwitterEnabled() {
		return twitterEnabled;
	}

	/**
	 * @return the twitterConsumerKey
	 */
	public String getTwitterConsumerKey() {
		return twitterConsumerKey;
	}

	/**
	 * @return the twitterConsumerSecret
	 */
	public String getTwitterConsumerSecret() {
		return twitterConsumerSecret;
	}

	/**
	 * @return the twitterAccessToken
	 */
	public String getTwitterAccessToken() {
		return twitterAccessToken;
	}

	/**
	 * @return the twitterAccessTokenSecret
	 */
	public String getTwitterAccessTokenSecret() {
		return twitterAccessTokenSecret;
	}

	/**
	 * @return the reCaptchaEnabled
	 */
	public boolean isReCaptchaEnabled() {
		return reCaptchaEnabled;
	}

	/**
	 * @return the reCaptchaPublicKey
	 */
	public String getReCaptchaPublicKey() {
		return reCaptchaPublicKey;
	}

	/**
	 * @return the reCaptchaPrivateKey
	 */
	public String getReCaptchaPrivateKey() {
		return reCaptchaPrivateKey;
	}

	/**
	 * @return the bitlyEnabled
	 */
	public boolean isBitlyEnabled() {
		return bitlyEnabled;
	}

	/**
	 * @return the bitlyUsername
	 */
	public String getBitlyUsername() {
		return bitlyUsername;
	}

	/**
	 * @return the bitlyPassword
	 */
	public String getBitlyPassword() {
		return bitlyPassword;
	}

}
