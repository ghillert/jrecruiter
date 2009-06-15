/**
 *
 */
package org.jrecruiter.dao;

import java.util.List;

import junit.framework.Assert;

import org.jrecruiter.model.Industry;
import org.jrecruiter.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
public class IndustryDaoTest extends BaseTest {

    private @Autowired IndustryDao industryDao;

    @Test
    public void testGetAll() {

        List<Industry> industries = industryDao.getAllIndustriesOrdered();
        Assert.assertTrue(industries.size() == 16);

    }
}
