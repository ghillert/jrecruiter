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
package org.jrecruiter;

import java.util.ArrayList;

/**
 * Contains utility methods, e.g. factory methods for creating list.
 *
 * @author Gunnar Hillert
 * @version $Id: Constants.java 136 2008-01-13 15:39:09Z ghillert $
 */
public class Utils {

    /** Private Constructor.
     *  Supress default constructor for non-instantiability */
    private Utils() {
        throw new AssertionError();
    }

    /** Return a basic ArrayList */
    public static <T> ArrayList<T> getArrayList() {
        return new ArrayList<T>(0);
    }

}
