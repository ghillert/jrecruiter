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
    public void testSaveIndustryWithCustomId() {

        final Industry industry = new Industry();
        industry.setId(888888888L);
        industry.setName("Space");

        industryDao.replicate(industry);

        super.entityManager.flush();

        final Industry savedIndustry = industryDao.get(888888888L);

        Assert.assertNotNull(savedIndustry);

    }


    @Test
    public void testGetAll() {

        List<Industry> industries = industryDao.getAllIndustriesOrdered();
        Assert.assertTrue(industries.size() == 16);

    }


    /**
     * Test to verify that the seed data is correctly populated. Typically there
     * should be 10 industries in the system:
     *
     */
    @Test
    public void testVerifyExistenceOfRoleSeedData() {

        final List<Industry> industries = industryDao.getAll();

        Assert.assertTrue(industries.size() == 16);

        boolean otherIndustryFound     = false;
        boolean perimeterIndustryFound = false;

        for (final Industry industry : industries) {

            if (industry.getName().equals("Transportation")) {
                perimeterIndustryFound = true;
            } else if (industry.getName().equals("Other")) {
                otherIndustryFound = true;
            }

        }

        Assert.assertTrue("Expected the industry named 'Transportation'. Is seed data populated?", perimeterIndustryFound);
        Assert.assertTrue("Expected the industry named 'Other'. Is seed data populated?",     otherIndustryFound);

    }

}
