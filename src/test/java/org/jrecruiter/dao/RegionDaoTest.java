/**
 *
 */
package org.jrecruiter.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.jrecruiter.model.Region;
import org.jrecruiter.test.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
public class RegionDaoTest extends BaseTest {

    private @Autowired RegionDao regionDao;

    /**
     * Initialize Logging.
     */
    public static final Logger LOGGER = Logger
            .getLogger(RegionDaoTest.class);

    public void testGetAll() {

        List<Region> regions = regionDao.getAllRegionsOrdered();
        assertTrue(regions.size() == 9);

    }

}
