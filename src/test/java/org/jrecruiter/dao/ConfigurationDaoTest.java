/**
 *
 */
package org.jrecruiter.dao;
 
import java.util.List;

import org.apache.log4j.Logger;
import org.jrecruiter.test.BaseTest;

/**
 * @author Gunnar Hillert
 * 
 */
public class ConfigurationDaoTest extends BaseTest {

	private SettingsDAO settingDao;

	/**
	 * Initialize Logging.
	 */
	public static final Logger LOGGER = Logger
			.getLogger(ConfigurationDaoTest.class);

	public void testGetAllConfigurations() {

		List<org.jrecruiter.model.Configuration> conf = settingDao
				.getAllConfigurations();

		for (org.jrecruiter.model.Configuration setting : conf) {

			LOGGER.info(setting);

		}

	}

	public void setSettingDao(SettingsDAO settingDao) {
		this.settingDao = settingDao;
	}

}
