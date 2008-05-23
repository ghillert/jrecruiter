/**
 *
 */
package org.jrecruiter;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

/**
 * @author hillert
 *
 */
public class UtilsUnitTest extends TestCase {

    public void testNewArrayListInstance() {

        final List<Integer>integers = Utils.getArrayList();
        assertTrue(integers instanceof ArrayList);

    }

}
