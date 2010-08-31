/**
 *
 */
package org.jrecruiter.dao;

import java.util.List;

import junit.framework.Assert;

import org.jrecruiter.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.jrecruiter.scala.Region;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
public class RegionDaoTest extends BaseTest {

    private @Autowired RegionDao regionDao;

    @Test
    public void testGetAll() {

        List<Region> regions = regionDao.getAllRegionsOrdered();
        Assert.assertTrue(regions.size() == 10);

    }

}
