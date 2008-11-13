/**
 *
 */
package org.jrecruiter.dao;

import java.util.List;

import org.jrecruiter.model.Industry;
import org.jrecruiter.test.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
public class IndustryDaoTest extends BaseTest {

    private @Autowired IndustryDao industryDao;

    public void testGetAll() {

        List<Industry> industries = industryDao.getAllIndustriesOrdered();
        assertTrue(industries.size() == 16);

    }
}
