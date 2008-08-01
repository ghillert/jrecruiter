/*
 *  http://www.jrecruiter.org
 *
 *  Disclaimer of Warranty.
 *
 *  Unless required by applicable law or agreed to in writing, Licensor provides
 *  the Work (and each Contributor provides its Contributions) on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied,
 *  including, without limitation, any warranties or conditions of TITLE,
 *  NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE. You are
 *  solely responsible for determining the appropriateness of using or
 *  redistributing the Work and assume any risks associated with Your exercise of
 *  permissions under this License.
 *
 */
package org.jrecruiter.common;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Contains utility methods, e.g. factory methods for creating list.
 *
 * @author Gunnar Hillert
 * @version $Id$
 */
public class CollectionUtils {

    /** Private Constructor.
     *  Supress default constructor for non-instantiability */
    private CollectionUtils() {
        throw new AssertionError();
    }

    /** Return a basic ArrayList */
    public static <T> ArrayList<T> getArrayList() {
        return new ArrayList<T>(0);
    }

    /** Return a basic HashSet */
    public static <T> java.util.HashSet<T> getHashSet() {
        return new HashSet<T>(0);
    }
}
