/**
 *
 */
package org.jrecruiter.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jrecruiter.model.Configuration;
import org.jrecruiter.test.BaseTest;
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
	public static final Logger LOGGER = Logger
			.getLogger(ConfigurationDaoTest.class);

	public void testGetAll() {

		Configuration configuration = new Configuration("test.test", "Just Testing", new Date());
		configurationDao.save(configuration);

		List<org.jrecruiter.model.Configuration> conf = configurationDao.getAll();
		assertTrue(conf.size() >= 1);

	}

}
