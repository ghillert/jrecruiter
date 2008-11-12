/**
 *
 */
package org.jrecruiter.dao;

import java.util.Date;
import java.util.List;
import org.jrecruiter.model.Configuration;
import org.jrecruiter.test.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Gunnar Hillert
 * @version $Id$
 */
public class ConfigurationDaoTest extends BaseTest {

    private @Autowired ConfigurationDao configurationDao;

    /**
     * Initialize Logging.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(ConfigurationDaoTest.class);

    public void testGetAll() {

        Configuration configuration = new Configuration("test.test", "Just Testing", new Date());
        configurationDao.save(configuration);

        List<org.jrecruiter.model.Configuration> conf = configurationDao.getAll();
        assertTrue(conf.size() >= 1);

    }

}
