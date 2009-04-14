/**
 *
 */
package org.jrecruiter.dao;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.jrecruiter.model.Configuration;
import org.jrecruiter.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
public class ConfigurationDaoTest extends BaseTest {

    private @Autowired ConfigurationDao configurationDao;

    @Test
    public void testGetAll() {

        Configuration configuration = new Configuration("test.test", "Just Testing", new Date());
        configurationDao.save(configuration);

        List<org.jrecruiter.model.Configuration> conf = configurationDao.getAll();
        Assert.assertTrue(conf.size() >= 1);

    }

}
