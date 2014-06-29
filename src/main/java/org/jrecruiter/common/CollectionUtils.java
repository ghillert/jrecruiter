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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Contains utility methods, e.g. factory methods for creating list.
 *
 * @author Gunnar Hillert
 */
public final class CollectionUtils {

	/** Private Constructor.
	 *  Supress default constructor for non-instantiability */
	private CollectionUtils() {
		throw new AssertionError();
	}

	/** Return a basic ArrayList */
	public static < T > ArrayList < T > getArrayList() {
		return new ArrayList < T >(0);
	}

	/** Return a basic HashSet */
	public static < T > java.util.HashSet<T> getHashSet() {
		return new HashSet < T >(0);
	}

	/** Return a basic HashMap */
	public static < T, S > java.util.HashMap < T, S > getHashMap() {
		return new HashMap < T,S > (0);
	}
}
