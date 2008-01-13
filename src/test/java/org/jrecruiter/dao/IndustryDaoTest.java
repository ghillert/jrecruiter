/**
 *
 */
package org.jrecruiter.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jrecruiter.model.Configuration;
import org.jrecruiter.model.Industry;
import org.jrecruiter.test.BaseTest;

/**
 * @author Gunnar Hillert
 *
 */
public class IndustryDaoTest extends BaseTest {

    private IndustryDao industryDao;

    /**
     * Initialize Logging.
     */
    public static final Logger LOGGER = Logger
            .getLogger(IndustryDaoTest.class);

    public void testGetAll() {

        List<Industry> industries = industryDao.getAllIndustriesOrdered();
        assertTrue(industries.size() == 5);

    }

    public void setIndustryDao(IndustryDao industryDao) {
        this.industryDao = industryDao;
    }

}
