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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Gunnar Hillert
 */
public class WebUtil {

	/**
	 * Constructor.
	 */
	public WebUtil() {
		super();
	}

	public static String stripTags(String text, String tags){


			tags = " " + tags + " ";
			final Pattern p = Pattern.compile("</?(.*?)(s.*?)?>");
			final Matcher m = p.matcher(text);

			final StringBuffer out = new StringBuffer();
			int lastPos = 0;
			while (m.find()) {
				final String tag = m.group(1);
				if (tags.indexOf(tag) == -1) {
					out.append(text.substring(lastPos, m.start()))
					   .append(" ");
				} else {
					out.append(text.substring(lastPos, m.end()));
				}
				lastPos = m.end();
			}
			if (lastPos > 0) {
				out.append(text.substring(lastPos));
				return out.toString().trim();
			} else {
				return text;
			}
	}


}
