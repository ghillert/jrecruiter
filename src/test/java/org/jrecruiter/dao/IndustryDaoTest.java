/**
 *
 */
package org.jrecruiter.dao;

import java.util.List;

import org.jrecruiter.model.Industry;
import org.jrecruiter.test.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
public class IndustryDaoTest extends BaseTest {

    private @Autowired IndustryDao industryDao;

    /**
     * Initialize Logging.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(IndustryDaoTest.class);

    public void testGetAll() {

        List<Industry> industries = industryDao.getAllIndustriesOrdered();
        assertTrue(industries.size() == 16);

    }
}
