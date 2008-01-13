/**
 *
 */
package org.jrecruiter.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jrecruiter.model.Configuration;
import org.jrecruiter.model.Industry;
import org.jrecruiter.model.Region;
import org.jrecruiter.test.BaseTest;

/**
 * @author Gunnar Hillert
 *
 */
public class RegionDaoTest extends BaseTest {

    private RegionDao regionDao;

    /**
     * Initialize Logging.
     */
    public static final Logger LOGGER = Logger
            .getLogger(RegionDaoTest.class);

    public void testGetAll() {

        List<Region> regions = regionDao.getAllRegionsOrdered();
        assertTrue(regions.size() == 10);

    }

    public void setRegionDao(RegionDao regionDao) {
        this.regionDao = regionDao;
    }
}
