/**
 *
 */
package org.jrecruiter;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.jrecruiter.common.CollectionUtils;

/**
 * @author hillert
 *
 */
public class UtilsUnitTest extends TestCase {

    public void testNewArrayListInstance() {

        final List<Integer>integers = CollectionUtils.getArrayList();
        assertTrue(integers instanceof ArrayList);

    }

}
